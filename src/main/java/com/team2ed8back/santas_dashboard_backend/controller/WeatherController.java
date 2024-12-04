package com.team2ed8back.santas_dashboard_backend.controller;

import com.team2ed8back.santas_dashboard_backend.entity.Weather;
import com.team2ed8back.santas_dashboard_backend.service.reindeer.ReindeerAlignmentService;
import com.team2ed8back.santas_dashboard_backend.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/v1/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @Autowired
    ReindeerAlignmentService reindeerAlignmentService;

    @GetMapping
    public Weather getWeatherCondition(){
        return reindeerAlignmentService.getWeatherCondition();
    }

}
