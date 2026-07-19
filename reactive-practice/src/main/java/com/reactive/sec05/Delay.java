package com.reactive.sec05;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class Delay {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);

        Flux.range(1, 10)
            .delayElements(Duration.ofSeconds(1))
            .doFinally(sig -> latch.countDown())
            .subscribe(Utils.subscriber());

        try {
            latch.await();
        } catch (Exception e) {
            throw new RuntimeException("somthing error {}", e);
        }
    }
}
