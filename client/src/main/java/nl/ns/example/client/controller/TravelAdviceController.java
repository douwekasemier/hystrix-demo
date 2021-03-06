package nl.ns.example.client.controller;

import nl.ns.example.client.connector.AdviceConnector;
import nl.ns.example.client.domain.AdviceError;
import nl.ns.example.client.domain.AdviceException;
import nl.ns.example.client.domain.TravelAdvice;
import nl.ns.example.client.improved.connector.CircuitBreakerAdviceConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.String.format;

@SuppressWarnings("Duplicates")
@RestController
public class TravelAdviceController {

    private final AdviceConnector adviceConnector;
    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong openConnections = new AtomicLong();

    @Autowired
    public TravelAdviceController(AdviceConnector adviceConnector) {
        this.adviceConnector = adviceConnector;
    }

    @RequestMapping("/plan")
    public TravelAdvice plan(@RequestParam String from, @RequestParam String to) {
        final long requestId = counter.incrementAndGet();

        System.out.println(format("<%d> [Start request]", requestId));

        final TravelAdvice advice = getTravelAdvice(from, to);

        System.out.println(format("<%d> [Advice] %s", requestId, advice.toString()));
        System.out.println(format("<%d> [End request]", requestId));

        return advice;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AdviceException.class)
    public AdviceError exceptionHandler(AdviceException exception) {
        final long requestId = counter.get();

        System.err.println(format("<%d> [%s] [%s]", requestId, exception.getErrorType(), exception.getMessage()));
        System.err.println(format("<%d> [End request]", requestId));

        return exception.toError();
    }

    private TravelAdvice getTravelAdvice(@RequestParam String from, @RequestParam String to) {
        final long requestId = counter.get();
        final LocalDateTime start = LocalDateTime.now();

        System.out.println(format("<%d> [Before getAdvice] Open connections: %d", requestId, openConnections.incrementAndGet()));

        try {
            return adviceConnector.getAdvice(from, to);
        } finally {
            final LocalDateTime finish = LocalDateTime.now();
            final long duration = ChronoUnit.MILLIS.between(start, finish);

            System.out.println(format("<%d> [After getAdvice] Duration: %d ms", requestId, duration));
            System.out.println(format("<%d> [After getAdvice] Open connections: %d", requestId, openConnections.decrementAndGet()));
        }
    }
}
