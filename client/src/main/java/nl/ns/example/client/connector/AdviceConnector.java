package nl.ns.example.client.connector;

import nl.ns.example.client.domain.TravelAdvice;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
public class AdviceConnector {

    public static final String URL = "http://localhost:8099/advice?from={from}&to={to}";
    public static final int READ_TIMEOUT = 4000;
    public static final int CONNECT_TIMEOUT = 4000;

    private RestTemplate restTemplate;

    public AdviceConnector() {
        final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(CONNECT_TIMEOUT);
        requestFactory.setReadTimeout(READ_TIMEOUT);

        restTemplate = new RestTemplate();
    }

    public TravelAdvice getAdvice(String from, String to) {
        final TravelAdvice travelAdvice = restTemplate.getForObject(URL, TravelAdvice.class, from, to);
        travelAdvice.setTime(LocalDateTime.now());

        return travelAdvice;
    }
}