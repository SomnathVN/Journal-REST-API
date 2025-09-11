package com.example.jounralrestapi.service;

import com.example.jounralrestapi.api.response.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class WeatherService {
    private static final String apiKey = "9c06f19f893bc84ecd48d20cc3c364ad";
    private static final String API = "https://api.openweathermap.org/data/2.5/weather?q=CITY&appid=API_KEY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalApi = API.replace("CITY",city).replace("API_KEY",apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        log.info("Api Response body: {}`",response.getBody().toString());
        WeatherResponse body = response.getBody();
        return body;
    }
}
