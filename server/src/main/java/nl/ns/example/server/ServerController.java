package nl.ns.example.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ServerController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/advice")
    public Advice advice(@RequestParam String from, @RequestParam String to) {

        try {
            // Either fast
            if (Math.random() < 0.5) {
                Thread.sleep(500);
            } // Or slow
            else {
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            throw new ServerOnFireException("Route calculation timeout", e);
        }

        // And sometimwes things fail for no apparent reason..
        if (Math.random() > 0.75) {
            throw new ServerOnFireException("Unknown error of randomness");
        }

        return new Advice(counter.incrementAndGet(), from + to);
    }
}
