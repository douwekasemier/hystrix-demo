package nl.ns.example.client.domain;

public class WeatherReport {

    private String status = "NOT_RETRIEVED";
    private boolean dry;
    private int temperature;

    @Override
    public String toString() {
        return "WeatherReport{" +
                "status='" + status + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDry() {
        return dry;
    }

    public void setDry(boolean dry) {
        this.dry = dry;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
