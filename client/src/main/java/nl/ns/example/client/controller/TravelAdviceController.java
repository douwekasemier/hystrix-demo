package nl.ns.example.client.controller;

import nl.ns.example.client.connector.AdviceConnector;
import nl.ns.example.client.domain.AdviceError;
import nl.ns.example.client.domain.AdviceException;
import nl.ns.example.client.domain.TravelAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TravelAdviceController {

    private final AdviceConnector adviceConnector;
    private int openConnections;

    @Autowired
    public TravelAdviceController(AdviceConnector adviceConnector) {
        this.adviceConnector = adviceConnector;
    }

    @RequestMapping("/plan")
    public TravelAdvice plan(@RequestParam String from, @RequestParam String to) {
        final TravelAdvice advice = getTravelAdvice(from, to);

        // Get the price from some other service
        advice.setPrice(9600);

        return advice;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AdviceException.class)
    public AdviceError exceptionHandler(AdviceException exception) {
        System.err.println("[" + exception.getErrorType() + "] " + exception.getMessage());

        return exception.toError();
    }

    private TravelAdvice getTravelAdvice(@RequestParam String from, @RequestParam String to) {
        try {
            openConnections++;
            System.out.println(String.format("[BEFORE GET ADVICE] Open connections: %d", openConnections));
            return adviceConnector.getAdvice(from, to);
        } finally {
            openConnections--;
            System.out.println(String.format("[AFTER GET ADVICE] Open connections: %d", openConnections));
        }
    }
}
