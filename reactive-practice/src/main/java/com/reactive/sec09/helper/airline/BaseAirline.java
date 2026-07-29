package com.reactive.sec09.helper.airline;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class BaseAirline {

    private final String AIRLINE;
    private final Logger logger;

    public BaseAirline(String AIRLINE) {
        this.AIRLINE = AIRLINE;
        this.logger = LoggerFactory.getLogger(AIRLINE);
    }

    public Flux<Flight> getFlight() {
        return Flux.range(1, Utils.faker().random().nextInt(2,10))
                .delayElements(Duration.ofMillis(Utils.faker().random().nextInt(200, 1000)))
                .map(i -> new Flight(
                    AIRLINE, 
                    Utils.faker().random().nextInt(20, 100)))
                .transform(Utils.fluxLogger(AIRLINE, logger));
    }
}
