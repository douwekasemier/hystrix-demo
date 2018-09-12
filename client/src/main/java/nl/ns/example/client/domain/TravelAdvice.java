package nl.ns.example.client.domain;

import java.time.LocalDateTime;

@SuppressWarnings("ALL")
public class TravelAdvice {
    private int id;
    private String route;
    private LocalDateTime time;
    private WeatherReport weather;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public WeatherReport getWeather() {
        return weather;
    }

    public void setWeather(WeatherReport weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "TravelAdvice{" +
                "id=" + id +
                ", route='" + route + '\'' +
                ", time=" + time +
                ", weather=" + weather +
                '}';
    }
}
