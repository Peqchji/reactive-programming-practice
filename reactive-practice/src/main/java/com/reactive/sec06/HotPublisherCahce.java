package com.reactive.sec06;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class HotPublisherCahce {
    private static final Logger logger = LoggerFactory.getLogger(HotPublisherCahce.class);
    
    public static void main(String[] args) {
        // continue while no subscribe left
        demoAutoConnect();
    }

    private static void demoAutoConnect() {
        var latch = new CountDownLatch(2);
        var movieFlux = stockStream().replay().autoConnect(0);

        Utils.sleepSecond(2);
        logger.info("Peach joining");
        movieFlux
            .doFinally(sig -> latch.countDown())
            .subscribe(Utils.subscriber("Peach"));


        Utils.sleepSecond(2);
        logger.info("Another me on mobile joining");

        movieFlux
            .doFinally(sig -> latch.countDown())
            .subscribe(Utils.subscriber("Another me on mobile"));

        try {
            latch.await();
        } catch (Exception e) {

        }
    }

    private static Flux<Integer> stockStream() {
        return Flux.<Integer>generate(
            sink -> sink.next(Utils.faker().random().nextInt(170, 220))
        ).take(10)
        .delayElements(Duration.ofMillis(500))
        .doOnNext(prices -> logger.info("emitting stock price: {}", prices));
    }
}
