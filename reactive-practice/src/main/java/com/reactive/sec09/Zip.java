package com.reactive.sec09;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class Zip {

    public record Car(String engine, String body, String tire){}

    public static void main(String[] args) {
        var latcher = new CountDownLatch(1);

        Flux.zip(
            getEngine(), 
            getBody(), 
            getTire()
        )
        .map(t -> new Car(t.getT1(), t.getT2(), t.getT3()))
        .doFinally(s -> latcher.countDown())
        .subscribe(Utils.subscriber());

        try {
            latcher.await();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static Flux<String> getBody() {
        return Flux.range(1, 10)
                .map(i -> "body-" + i)
                .delayElements(Duration.ofMillis(50));
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 3)
                .map(i -> "engine-" + i)
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getTire() {
        return Flux.range(1, 100)
            .map(i -> "tire-" + i)
            .delayElements(Duration.ofMillis(200));    
    }
}
