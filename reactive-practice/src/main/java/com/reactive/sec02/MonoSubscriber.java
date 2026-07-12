package com.reactive.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;

public class MonoSubscriber {
    private static final Logger logger = LoggerFactory.getLogger(MonoJust.class);

    public static void main(String[] args) {
        var mono = Mono.just(1)
                    .map(i -> i / 0);

        mono.subscribe(
            i -> logger.info("recieved: {}", i),
            err -> logger.error("error", err),
            () -> logger.info("Completed!"),
            subscription -> subscription.request(1)
        );
    }
    
}
