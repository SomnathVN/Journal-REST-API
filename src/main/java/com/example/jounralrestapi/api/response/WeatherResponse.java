package com.example.jounralrestapi.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class WeatherResponse {

    private Main main;

    @Data
    public class Main{
        public double temp;
        @JsonProperty("feels_like")
        public double feelsLike;
    }
}
