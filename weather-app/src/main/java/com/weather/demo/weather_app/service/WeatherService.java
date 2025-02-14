package com.weather.demo.weather_app.service;

import com.weather.demo.weather_app.domain.CityCoordinates;
import com.weather.demo.weather_app.domain.CityWeather;
import com.weather.demo.weather_app.domain.WeatherRequestDetails;
import com.weather.demo.weather_app.entity.GeocodingCoordinatesEntity;
import com.weather.demo.weather_app.entity.WeatherResponse;
import com.weather.demo.weather_app.provider.GeocodingProvider;
import com.weather.demo.weather_app.provider.WeatherProvider;
import com.weather.demo.weather_app.transformer.GeocodingCoordinatesTransformer;
import com.weather.demo.weather_app.transformer.OpenWeatherTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private  GeocodingProvider geocodingProvider;
    private GeocodingCoordinatesTransformer geocodingCoordinatesTransformer;
    private WeatherProvider weatherprovider;
    private OpenWeatherTransformer openWeatherTransformer;

    @Autowired
    public WeatherService(final GeocodingProvider geocodingProvider,
                          final GeocodingCoordinatesTransformer geocodingCoordinatesTransformer,
                          final WeatherProvider weatherprovider,
                          final OpenWeatherTransformer openWeatherTransformer){
        this.geocodingProvider=geocodingProvider;
        this.geocodingCoordinatesTransformer=geocodingCoordinatesTransformer;
        this.weatherprovider=weatherprovider;
        this.openWeatherTransformer=openWeatherTransformer;
    }

    public WeatherResponse getWeather(final WeatherRequestDetails weatherRequestDetails ) throws Exception {

        final CityCoordinates cityCoordinates = geocodingCoordinatesTransformer.
                transformToDomain(geocodingProvider.getCoordinates(weatherRequestDetails));

        //get the weather for geo coordinates
        final CityWeather cityWeather=openWeatherTransformer
                .transformToDomain(weatherprovider.getweather(cityCoordinates));

        return openWeatherTransformer.transformToEntity(cityWeather);
    }
}
