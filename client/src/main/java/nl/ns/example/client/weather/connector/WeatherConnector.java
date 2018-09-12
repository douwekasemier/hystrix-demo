package nl.ns.example.client.weather.connector;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import nl.ns.example.client.domain.WeatherReport;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WeatherConnector {

    public WeatherConnector() { }

    public WeatherReport getWeather(String location) {
        final GetWeatherCommand command = new GetWeatherCommand(location, LocalDateTime.now());

        return command.execute();
    }
}