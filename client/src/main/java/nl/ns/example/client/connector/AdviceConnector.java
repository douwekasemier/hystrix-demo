package nl.ns.example.client.connector;

import nl.ns.example.client.domain.AdviceException;
import nl.ns.example.client.domain.TravelAdvice;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
public class AdviceConnector {
    static final String URL = "http://localhost:8099/advice?from={from}&to={to}";

    private static final int READ_TIMEOUT = 4000;
    private static final int CONNECT_TIMEOUT = 4000;

    RestTemplate restTemplate;

    public AdviceConnector() {
        final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(CONNECT_TIMEOUT);
        requestFactory.setReadTimeout(READ_TIMEOUT);

        restTemplate = new RestTemplate();
    }

    public TravelAdvice getAdvice(String from, String to) {
        try {
            final TravelAdvice travelAdvice = restTemplate.getForObject(URL, TravelAdvice.class, from, to);
            travelAdvice.setTime(LocalDateTime.now());

            return travelAdvice;
        } catch (RestClientException e) {
            throw new AdviceException("REST_CLIENT_EXCEPTION", e.getMessage(), e);
        }
    }
}