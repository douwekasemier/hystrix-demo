package nl.ns.example.client.weather.connector;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import nl.ns.example.client.domain.WeatherReport;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class GetWeatherCommand extends HystrixCommand<WeatherReport> {
    private final String location;
    private final String timequery;

    GetWeatherCommand(String location, LocalDateTime time) {
        super(HystrixCommandGroupKey.Factory.asKey("WEATHER_SERVICE"));

        this.location = location;
        // Retrieve the weather for the current hour
        this.timequery = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH"));
    }

    @Override
    protected WeatherReport run() {
        throw new RuntimeException("Cannot retrieve weather");
    }

    @Override
    protected WeatherReport getFallback() {
        return new WeatherReport();
    }
}