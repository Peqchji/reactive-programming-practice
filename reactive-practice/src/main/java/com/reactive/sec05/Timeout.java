package com.reactive.sec05;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import com.reactive.common.Utils;

import reactor.core.publisher.Mono;

public class Timeout {
    public static void main(String[] args) {
        var latch = new CountDownLatch(1);

        getProductName()
            .timeout(Duration.ofSeconds(1))
            .onErrorReturn("time-out")
            .doFinally(sig -> latch.countDown())
            .subscribe(Utils.subscriber()); 
        
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Mono<String> getProductName() {
        return Mono.<String>fromSupplier(
            () -> Utils.faker().commerce().productName()
        ).delayElement(Duration.ofSeconds(3));
    }
}
