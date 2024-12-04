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



}
