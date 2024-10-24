package com.weather.demo.weather_app.domain;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class WeatherRequestDetails {
    private String city;
}
