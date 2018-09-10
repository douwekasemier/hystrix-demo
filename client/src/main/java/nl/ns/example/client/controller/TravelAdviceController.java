package nl.ns.example.client.controller;

import nl.ns.example.client.betterconnector.CircuitBreakerAdviceConnector;
import nl.ns.example.client.domain.TravelAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TravelAdviceController {

    private final CircuitBreakerAdviceConnector travelAdviceConnector;
    private int openConnections;

    @Autowired
    public TravelAdviceController(CircuitBreakerAdviceConnector travelAdviceConnector) {
        this.travelAdviceConnector = travelAdviceConnector;
    }

    @RequestMapping("/plan")
    public TravelAdvice plan(@RequestParam String from, @RequestParam String to) {
        final TravelAdvice advice = getTravelAdvice(from, to);

        // Get the price from some other service
        advice.setPrice(9600);

        return advice;
    }

    private TravelAdvice getTravelAdvice(@RequestParam String from, @RequestParam String to) {
        try {
            openConnections++;
            System.out.println(String.format("[BEFORE GET ADVICE] Open connections: %d", openConnections));
            return travelAdviceConnector.getAdvice(from, to);
        } finally {
            openConnections--;
            System.out.println(String.format("[AFTER GET ADVICE] Open connections: %d", openConnections));
        }
    }
}