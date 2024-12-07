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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        if (weatherCondition.getCondition().contains("snow")) {
            return reindeerAlignmentRepository.findByName("Snowy alignment");
        } else {
            return reindeerAlignmentRepository.findByName("Default alignment");
        }
    }

    public boolean reindeerAlignmentIsEmpty() {
        return reindeerAlignmentRepository.findAll().isEmpty();
    }

//    public void insertReinderToAlignment(ReindeerAlignment alignment, Reindeer reindeer, String position) {
//        switch (position) {
//            case "leader":
//                alignment.setLeader(reindeer);
//                break;
//            case "front1":
//                alignment.setFront1(reindeer);
//                break;
//            case "front2":
//                alignment.setFront2(reindeer);
//                break;
//            case "middle1":
//                alignment.setMiddle1(reindeer);
//                break;
//            case "middle2":
//                alignment.setMiddle2(reindeer);
//                break;
//            case "middle3":
//                alignment.setMiddle3(reindeer);
//                break;
//            case "back1":
//                alignment.setBack1(reindeer);
//                break;
//            case "back2":
//                alignment.setBack2(reindeer);
//                break;
//            case "back3":
//                alignment.setBack3(reindeer);
//                break;
//            default:
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid position");
//        }
//        reindeerAlignmentRepository.save(alignment);
//    }

    public void insertDefaultAlignments() {
        if (reindeerAlignmentIsEmpty()) {
            List<Reindeer> reindeers = reindeerService.findAllReindeers();

            // Snowy alignment
            ReindeerAlignment snowyAlignment = new ReindeerAlignment();
            snowyAlignment.setName("Snowy alignment");

            Reindeer rudolph = reindeers.stream()
                    .filter(r -> "Rudolph".equalsIgnoreCase(r.getName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Rudolph not found ACA ESTA EL ERROR"));
            snowyAlignment.setLeader(rudolph);

            List<Reindeer> strongReindeers = reindeers.stream()
                    .filter(r -> "Strongest".equalsIgnoreCase(r.getType()) || "Strong".equalsIgnoreCase(r.getType()))
                    .collect(Collectors.toList());

            List<Reindeer> fastReindeers = reindeers.stream()
                    .filter(r -> "Fastest".equalsIgnoreCase(r.getType()) || "Fast".equalsIgnoreCase(r.getType()))
                    .collect(Collectors.toList());

            snowyAlignment.setFront1(strongReindeers.get(0));
            snowyAlignment.setFront2(strongReindeers.get(1));
            snowyAlignment.setMiddle1(strongReindeers.get(2));
            snowyAlignment.setMiddle2(strongReindeers.get(3));
            snowyAlignment.setMiddle3(strongReindeers.get(4));
            snowyAlignment.setBack1(fastReindeers.get(0));
            snowyAlignment.setBack2(fastReindeers.get(1));
            snowyAlignment.setBack3(fastReindeers.get(2));

            reindeerAlignmentRepository.save(snowyAlignment);

            // Default alignment
            ReindeerAlignment defaultAlignment = new ReindeerAlignment();
            defaultAlignment.setName("Default alignment");

            Reindeer fastest = reindeers.stream()
                    .filter(r -> "Fastest".equalsIgnoreCase(r.getType()) || "Fast".equalsIgnoreCase(r.getType()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Fastest reindeer not found"));
            defaultAlignment.setLeader(fastest);

            defaultAlignment.setFront1(rudolph);
            defaultAlignment.setFront2(fastReindeers.get(1));
            defaultAlignment.setMiddle1(fastReindeers.get(2));
            defaultAlignment.setMiddle2(strongReindeers.get(0));
            defaultAlignment.setMiddle3(strongReindeers.get(1));
            defaultAlignment.setBack1(strongReindeers.get(2));
            defaultAlignment.setBack2(strongReindeers.get(3));
            defaultAlignment.setBack3(strongReindeers.get(4));

            reindeerAlignmentRepository.save(defaultAlignment);
        }
    }
}
