package nl.ns.example.client.betterconnector;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import nl.ns.example.client.domain.TravelAdvice;
import org.springframework.web.client.RestTemplate;

public class GetAdviceCommand extends HystrixCommand<TravelAdvice> {
    private static final String HYSTRIX_GROUP_KEY = "TRAVEL_ADVICE_SERVICE";
    private static final String HYSTRIX_COMMAND_KEY = "GET_TRAVEL_ADVICE";
    private final String to;
    private final String from;

    private RestTemplate restTemplate;
    private String url;

    public GetAdviceCommand(RestTemplate restTemplate, String url, String from, String to) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(HYSTRIX_GROUP_KEY))
                .andCommandKey(HystrixCommandKey.Factory.asKey(HYSTRIX_COMMAND_KEY))
        );

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