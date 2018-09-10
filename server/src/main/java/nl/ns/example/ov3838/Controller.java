package nl.ns.example.ov3838;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class Controller {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/advice")
    public Advice advice(@RequestParam String from, @RequestParam String to) {

        try {
            if (Math.random() < 0.5) {
                Thread.sleep(1000);
            } else {
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            throw new ServerOnFireException("Route calculation timeout", e);
        }

        if (Math.random() > 0.5) {
            throw new ServerOnFireException("Unknown error of randomness");
        }

        return new Advice(counter.incrementAndGet(), from + to);
    }
}
