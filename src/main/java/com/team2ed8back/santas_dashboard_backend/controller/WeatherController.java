package com.team2ed8back.santas_dashboard_backend.controller;

import com.team2ed8back.santas_dashboard_backend.entity.Weather;
import com.team2ed8back.santas_dashboard_backend.entity.reindeer.Reindeer;
import com.team2ed8back.santas_dashboard_backend.entity.reindeerAlignment.ReindeerAlignment;
import com.team2ed8back.santas_dashboard_backend.service.reindeer.ReindeerAlignmentService;
import com.team2ed8back.santas_dashboard_backend.service.reindeer.ReindeerService;
import com.team2ed8back.santas_dashboard_backend.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @Autowired
    ReindeerService reindeerService;

    @Autowired
    ReindeerAlignmentService reinderAlignmentService;

    @GetMapping("/north-pole")
    public String getWeatherCondition() {
        try {
            String weatherResponse = weatherService.getWeatherAtNorthPole();
            JSONObject weatherJson = new JSONObject(weatherResponse);
            String weatherCondition = weatherJson.getJSONArray("data")
                    .getJSONObject(0).getJSONObject("weather").getString("description");

            Weather weather = new Weather();
            weather.setCondition(weatherCondition);

            return weather.getCondition();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fetch weather condition", e);
        }
    }

    @GetMapping("/reindeer-alignment")
    public ReindeerAlignment getReindeerAlignment(@RequestParam String weatherCondition) {
        ReindeerAlignment alignment = generateReindeerAlignment(getWeatherCondition());
        return reinderAlignmentService.createAlignment(alignment);
    }

    private ReindeerAlignment generateReindeerAlignment(String weatherCondition) {
        ReindeerAlignment alignment = new ReindeerAlignment();

        List<Reindeer> reindeers = reindeerService.findAllReindeers();
        Reindeer lead;
        List<Reindeer> frontReindeers;
        List<Reindeer> middleReindeers;
        List<Reindeer> backReindeers;

        if ("Snow".equalsIgnoreCase(weatherCondition)) {
            lead = reindeers.stream()
                    .filter(r -> "Rudolph".equalsIgnoreCase(r.getName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Rudolph not found"));
            frontReindeers = reindeers.stream()
                    .filter(r -> "Strongest".equalsIgnoreCase(r.getType()) || "Strong".equalsIgnoreCase(r.getType()))
                    .collect(Collectors.toList());
            backReindeers = reindeers.stream().
                    filter(r -> "Fastest".equalsIgnoreCase(r.getType()) || "Fast".equalsIgnoreCase(r.getType()))
                    .collect(Collectors.toList());
            alignment.setName("Snow Alignment");

        } else {
            lead = reindeers.stream()
                    .filter(r -> "Fastest".equalsIgnoreCase(r.getType()) || "Fast".equalsIgnoreCase(r.getType()))
                    .findFirst().orElseThrow(() -> new RuntimeException("Fastest reindeer not found"));
            frontReindeers = reindeers.stream()
                    .filter(r -> "Fastest".equalsIgnoreCase(r.getType()) || "Fast".equalsIgnoreCase(r.getType()))
                    .collect(Collectors.toList());
            backReindeers = reindeers.stream()
                    .filter(r -> "Strongest".equalsIgnoreCase(r.getType()) || "Strong".equalsIgnoreCase(r.getType()))
                    .collect(Collectors.toList());
            alignment.setName("Default Alignment");
        }

        middleReindeers = reindeers.stream().filter(r -> !frontReindeers.contains(r) && !backReindeers.contains(r) && !r.equals(lead)).collect(Collectors.toList());


        alignment.setLead(lead);
        alignment.setFront1(frontReindeers.get(0));
        alignment.setFront2(frontReindeers.get(1));
        alignment.setMiddle1(middleReindeers.get(0));
        alignment.setMiddle2(middleReindeers.get(1));
        alignment.setMiddle3(middleReindeers.get(2));
        alignment.setBack1(backReindeers.get(0));
        alignment.setBack2(backReindeers.get(1));
        alignment.setBack3(backReindeers.get(2));
        alignment.setWeather(weatherCondition);

        return alignment;
    }
}
