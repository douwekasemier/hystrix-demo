package nl.ns.example.client.connector;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import nl.ns.example.client.domain.TravelAdvice;
import org.springframework.web.client.RestTemplate;

class GetAdviceCommand extends HystrixCommand<TravelAdvice> {
    private final String to;
    private final String from;

    private RestTemplate restTemplate;
    private String url;

    GetAdviceCommand(RestTemplate restTemplate, String url, String from, String to) {
        super(HystrixCommandGroupKey.Factory.asKey("TRAVEL_ADVICE_SERVICE"));

        this.restTemplate = restTemplate;
        this.url = url;
        this.from = from;
        this.to = to;
    }

    @Override
    protected TravelAdvice run() {
        return restTemplate.getForObject(url, TravelAdvice.class, from, to);
    }
}