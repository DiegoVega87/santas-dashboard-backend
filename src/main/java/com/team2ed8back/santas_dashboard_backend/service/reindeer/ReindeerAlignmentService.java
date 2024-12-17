package com.team2ed8back.santas_dashboard_backend.service.reindeer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team2ed8back.santas_dashboard_backend.entity.Weather;
import com.team2ed8back.santas_dashboard_backend.entity.reindeer.Reindeer;
import com.team2ed8back.santas_dashboard_backend.entity.reindeerAlignment.ReindeerAlignment;
import com.team2ed8back.santas_dashboard_backend.entity.reindeerAlignment.ReindeerAlignmentRepository;
import com.team2ed8back.santas_dashboard_backend.service.WeatherService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReindeerAlignmentService {

    @Autowired
    private ReindeerAlignmentRepository reindeerAlignmentRepository;

    @Autowired
    ReindeerService reindeerService;

    @Autowired
    WeatherService weatherService;


    public List<ReindeerAlignment> getAllAlignments() {
        return reindeerAlignmentRepository.findAll();
    }

    public ReindeerAlignment getAlignmentById(Long id) {
        return reindeerAlignmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Alignment not found"));
    }

    public ReindeerAlignment createAlignment(ReindeerAlignment alignment) {
        return reindeerAlignmentRepository.save(alignment);
    }

    public ReindeerAlignment updateAlignment(Long id, ReindeerAlignment alignment) {
        ReindeerAlignment existingAlignment = getAlignmentById(id);
        // Update fields of existingAlignment with values from alignment
        existingAlignment.setName(alignment.getName());
        existingAlignment.setLeft1(alignment.getLeft1());
        existingAlignment.setRight1(alignment.getRight1());
        existingAlignment.setLeft2(alignment.getLeft2());
        existingAlignment.setRight2(alignment.getRight2());
        existingAlignment.setLeft3(alignment.getLeft3());
        existingAlignment.setRight3(alignment.getRight3());
        existingAlignment.setLeft4(alignment.getLeft4());
        existingAlignment.setRight4(alignment.getRight4());
        return reindeerAlignmentRepository.save(existingAlignment);
    }

    public void deleteAlignment(Long id) {
        reindeerAlignmentRepository.deleteById(id);
    }

    public Weather getWeatherCondition() {
        try {
            String weatherResponse = weatherService.getWeatherAtNorthPole();
            JSONObject weatherJson = new JSONObject(weatherResponse);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(weatherJson.toString());

            // Navegar a "data" (es un array)
            String temp = "";
            JsonNode dataArray = rootNode.get("data");
            if (dataArray != null && dataArray.isArray()) {
                JsonNode firstItem = dataArray.get(0); //
                if (firstItem != null) {
                    JsonNode tempNode = firstItem.get("temp"); // Obtener el valor de "temp"
                    if (tempNode != null) {
                        temp = tempNode.asInt() + "°C";
                    } else {
                        System.out.println("No se encontró 'temp' en el objeto.");
                    }
                }
            }
            String weatherCondition = weatherJson.getJSONArray("data")
                    .getJSONObject(0).getJSONObject("weather").getString("description");

            System.out.println(weatherJson.toString(2)); // Pretty print the J
            Weather weather = new Weather();
            weather.setCondition(weatherCondition);
            weather.setTemperature(temp);
            return weather;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fetch weather condition", e);
        }
    }

    public ReindeerAlignment getAlignmentByWeather() {
        Weather weatherCondition = getWeatherCondition();
        if (weatherCondition.getCondition().toLowerCase().contains("snow") ||
        weatherCondition.getCondition().toLowerCase().contains("fog")||
        weatherCondition.getCondition().toLowerCase().contains("mist")) {
            return reindeerAlignmentRepository.findByName("Snowy/Foggy/Misty alignment");
        } else {
            return reindeerAlignmentRepository.findByName("Default alignment");
        }
    }

    public boolean reindeerAlignmentIsEmpty() {
        return reindeerAlignmentRepository.findAll().isEmpty();
    }

    public void insertDefaultAlignments() {
        if (reindeerAlignmentIsEmpty()) {
            List<Reindeer> reindeers = reindeerService.findAllReindeers();

            // Snowy alignment
            ReindeerAlignment snowyAlignment = new ReindeerAlignment();
            snowyAlignment.setName("Snowy/Foggy/Misty alignment");

            Reindeer rudolph = reindeers.stream()
                    .filter(r -> "Rudolph".equalsIgnoreCase(r.getName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Rudolph not found"));
            snowyAlignment.setLeft1(rudolph);

            List<Reindeer> strongReindeers = reindeers.stream()
                    .filter(r -> "Strongest".equalsIgnoreCase(r.getType()) || "Strong".equalsIgnoreCase(r.getType()))
                    .collect(Collectors.toList());
            List<Reindeer> fastReindeers = reindeers.stream()
                    .filter(r -> "Fastest".equalsIgnoreCase(r.getType()) || "Fast".equalsIgnoreCase(r.getType()))
                    .collect(Collectors.toList());

            snowyAlignment.setRight1(strongReindeers.get(0));
            snowyAlignment.setLeft2(strongReindeers.get(1));
            snowyAlignment.setRight2(strongReindeers.get(2));
            snowyAlignment.setRight3(fastReindeers.get(0));
            snowyAlignment.setLeft4(fastReindeers.get(1));
            snowyAlignment.setRight4(fastReindeers.get(2));
            snowyAlignment.setLeft3(fastReindeers.get(3));

        reindeerAlignmentRepository.save(snowyAlignment);

        // Default alignment
        ReindeerAlignment defaultAlignment = new ReindeerAlignment();
        defaultAlignment.setName("Default alignment");

            Reindeer fastest = reindeers.stream()
                    .filter(r -> "Fastest".equalsIgnoreCase(r.getType()) || "Fast".equalsIgnoreCase(r.getType()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Fastest reindeer not found"));
            defaultAlignment.setLeft1(fastest);

            defaultAlignment.setRight1(fastReindeers.get(1));
            defaultAlignment.setLeft2(fastReindeers.get(2));
            defaultAlignment.setRight2(fastReindeers.get(3));
            defaultAlignment.setLeft3(rudolph);
            defaultAlignment.setRight3(strongReindeers.get(0));
            defaultAlignment.setLeft4(strongReindeers.get(1));
            defaultAlignment.setRight4(strongReindeers.get(2));

            reindeerAlignmentRepository.save(defaultAlignment);
        }
    }
}
