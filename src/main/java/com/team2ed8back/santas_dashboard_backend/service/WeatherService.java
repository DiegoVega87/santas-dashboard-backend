package com.team2ed8back.santas_dashboard_backend.service;

import com.team2ed8back.santas_dashboard_backend.config.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    // This method is used to get the weather at the North Pole using the OpenWeatherMap API and the RestTemplate
    // returns the weather at the North Pole as a String in JSON format
    public String getWeatherAtNorthPole() {
        String apiKey = "871ba1c08afa61752584f64237330fd3";
        String url = "https://api.openweathermap.org/data/3.0/onecall?lat=90.0&lon=135.0&exclude=minutely,hourly,daily,alerts&appid=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }
}
