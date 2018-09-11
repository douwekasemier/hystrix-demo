package nl.ns.example.client.connector;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import nl.ns.example.client.domain.AdviceException;
import nl.ns.example.client.domain.TravelAdvice;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CircuitBreakerAdviceConnector extends AdviceConnector {

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