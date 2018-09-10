package nl.ns.example.ov3838;

public class Advice {
    private final long id;
    private final String route;

    public Advice(long id, String route) {
        this.id = id;
        this.route = route;
    }

    public long getId() {
        return id;
    }

    public String getRoute() {
        return route;
    }
}
