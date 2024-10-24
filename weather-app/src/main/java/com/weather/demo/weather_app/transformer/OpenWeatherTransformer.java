package com.weather.demo.weather_app.transformer;

import com.weather.demo.weather_app.domain.CityWeather;
import com.weather.demo.weather_app.entity.OpenWeatherResponseEntity;
import com.weather.demo.weather_app.entity.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherTransformer {

    public CityWeather transformToDomain(final OpenWeatherResponseEntity entity){
        return CityWeather.builder()
                .weather(entity.getWeather()[0].getMain())
                .details(entity.getWeather()[0].getDescription())
                .build();

    }
    public WeatherResponse transformToEntity(final CityWeather cityWeather){
        return WeatherResponse.builder()
                .werather(cityWeather.getWeather())
                .details(cityWeather.getDetails())
                .build();
    }
}
