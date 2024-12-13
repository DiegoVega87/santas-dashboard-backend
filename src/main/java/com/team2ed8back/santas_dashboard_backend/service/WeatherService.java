package com.team2ed8back.santas_dashboard_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Autowired
    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // This method is used to get the weather at the North Pole using the OpenWeatherMap API and the RestTemplate
    // returns the weather at the North Pole as a String in JSON format
    public String getWeatherAtNorthPole() {
        String apiKey = "251e065bf9bd4d2581a7f1db416312a2";
        String url = "https://api.weatherbit.io/v2.0/current?lat=90.0&lon=135.0&key=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }
}
