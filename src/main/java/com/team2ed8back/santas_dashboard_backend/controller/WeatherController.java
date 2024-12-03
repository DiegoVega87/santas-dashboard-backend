package com.team2ed8back.santas_dashboard_backend.controller;

import com.team2ed8back.santas_dashboard_backend.entity.reindeer.Reindeer;
import com.team2ed8back.santas_dashboard_backend.entity.reindeerAlignment.ReindeerAlignment;
import com.team2ed8back.santas_dashboard_backend.service.reindeer.ReindeerService;
import com.team2ed8back.santas_dashboard_backend.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @Autowired
    ReindeerService reindeerService;

    @GetMapping("/north-pole")
    public String getWeatherCondition() {
        String weatherResponse = weatherService.getWeatherAtNorthPole();
        JSONObject weatherJson = new JSONObject(weatherResponse);
        String weatherCondition = weatherJson.getJSONArray("weather")
                .getJSONObject(0).getString("main");
        return "Weather: " + weatherCondition;
    }

    @GetMapping("/reindeer-alignment")
    public ReindeerAlignment getReindeerAlignment(@RequestParam String weatherCondition) {
        return generateReindeerAlignment(weatherCondition);
    }

    private ReindeerAlignment generateReindeerAlignment(String weatherCondition) {
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
        }

        middleReindeers = reindeers.stream().filter(r -> !frontReindeers.contains(r) && !backReindeers.contains(r) && !r.equals(lead)).collect(Collectors.toList());

        ReindeerAlignment alignment = new ReindeerAlignment();
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
