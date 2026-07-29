package com.reactive.sec09;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class Merge {
    private static final Logger logger = LoggerFactory.getLogger(Merge.class);

    public static void main(String[] args) {
        CountDownLatch latcher = new CountDownLatch(1);
        Flux.merge(
                producer2(),
                producer1()
        )
            .take(1)
            .doFinally(i -> latcher.countDown())
            .subscribe(Utils.subscriber());

        try {
            latcher.await();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static Flux<Integer> producer1() {
        return Flux.<Integer>just(1, 2, 3)
             .transform(Utils.fluxLogger("producer 1", logger))
            .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.<Integer>just(51, 52, 53)
            .transform(Utils.fluxLogger("producer 2", logger))
            .delayElements(Duration.ofMillis(10));
    }
}
