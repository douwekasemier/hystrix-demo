package nl.ns.example.client.domain;

import java.time.LocalDateTime;

public class TravelAdvice {
    public String route;
    public LocalDateTime time;
    public int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
