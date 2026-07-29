package com.reactive.sec09;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class ConcatWith {

    private static final Logger logger = LoggerFactory.getLogger(ConcatWith.class);

    public static void main(String[] args) {
        CountDownLatch latcher = new CountDownLatch(1);
        Flux.concatDelayError(
                producer1(),
                producer2(),
                producer3()
        ).doFinally(i -> latcher.countDown())
            .subscribe(Utils.subscriber());

        try {
            latcher.await();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static Flux<Integer> producer1() {
        return Flux.<Integer>just(1, 2, 3)
            .doOnSubscribe(s -> logger.info("subscribing to producer 1"))
            .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.<Integer>just(51, 52, 53)
            .doOnSubscribe(s -> logger.info("subscribing to producer 2"))
            .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer3() {
        return Flux.error(new RuntimeException("err"));
    }

}
