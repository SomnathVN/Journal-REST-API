package com.example.jounralrestapi.service;

import com.example.jounralrestapi.api.response.WeatherResponse;
import com.example.jounralrestapi.cache.AppCache;
import com.example.jounralrestapi.constants.placeholders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_"+city, WeatherResponse.class);
        if(weatherResponse != null){
            return weatherResponse;
        }else{
            String finalApi = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(placeholders.CITY,city).replace(placeholders.API_KEY,apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
            log.info("Api Response body: {}`",response.getBody().toString());
            WeatherResponse body = response.getBody();
            if(body != null){
                redisService.set("weather_of_"+city,body,300l);
            }
            return body;
        }
    }
}
