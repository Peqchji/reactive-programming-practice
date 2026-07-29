package com.reactive.common;

import java.time.Duration;
import java.util.function.UnaryOperator;

import org.reactivestreams.Subscriber;
import org.slf4j.Logger;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;

public class Utils {
    private static final Faker faker = Faker.instance();

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

    public static Faker faker() {
        return faker;
    }

    public static void sleepSecond(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> UnaryOperator<Flux<T>> fluxLogger(String name, Logger logger) {
        return flux -> flux
            .doOnSubscribe(s -> logger.info("subscribing to {}", name))
            .doOnCancel(() -> logger.info("cancelling {}", name))
            .doOnComplete(() -> logger.info("{} completed", name));
    }
}
