package com.reactive.sec05;

import java.util.function.UnaryOperator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class ErrorHandeling {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandeling.class);
    public static void main(String[] args) {
        Flux.range(1, 10)
            .map(i -> i == 5 ? i/0 : i)
            // .onErrorContinue((error, item) -> logger.info("Error: {}, Item: {}", error, item))
            .transform(ErrorHandeling.onError())
            .subscribe(Utils.subscriber());
    }

    private static UnaryOperator<Flux<Integer>> onError() {
        return flux -> flux
            .onErrorResume(IllegalArgumentException.class, e -> fallback())
            .onErrorResume(ArithmeticException.class, e -> fallback())
            .onErrorReturn(-5);
    }

    private static Flux<Integer> fallback() {
        logger.info("fallback");

        var random = Utils.faker().random().nextInt(1, Integer.MAX_VALUE);

        return Flux.just(random);
    }
}
