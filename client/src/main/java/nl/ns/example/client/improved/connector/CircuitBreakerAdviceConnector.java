package nl.ns.example.client.improved.connector;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import nl.ns.example.client.connector.AdviceConnector;
import nl.ns.example.client.domain.AdviceException;
import nl.ns.example.client.domain.TravelAdvice;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class CircuitBreakerAdviceConnector {

    private static final String URL = "http://localhost:8099/advice?from={from}&to={to}";

    private static final int READ_TIMEOUT = 1000;
    private static final int CONNECT_TIMEOUT = 1000;

    private RestTemplate restTemplate;

    public CircuitBreakerAdviceConnector() {
        final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(CONNECT_TIMEOUT);
        requestFactory.setReadTimeout(READ_TIMEOUT);

        restTemplate = new RestTemplate(requestFactory);
    }

    public TravelAdvice getAdvice(String from, String to) {
        try {
            final GetAdviceCommand command = new GetAdviceCommand(restTemplate, URL, from, to);
            final TravelAdvice travelAdvice = command.execute();
            travelAdvice.setTime(LocalDateTime.now());

            return travelAdvice;
        } catch (HystrixRuntimeException e) {
            throw new AdviceException(e.getFailureType().toString(), e.getMessage(), e);
        }
    }
}