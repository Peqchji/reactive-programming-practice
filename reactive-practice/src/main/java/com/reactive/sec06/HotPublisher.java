package com.reactive.sec06;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class HotPublisher {
    private static final Logger logger = LoggerFactory.getLogger(HotPublisher.class);
    
    public static void main(String[] args) {
        // stop publish when no subscriber left
        demo();

        // continue while no subscribe left
        demoAutoConnect();
    }

    private static void demo() {
        var movieFlux = movieStreama().publish().refCount(1);

        Utils.sleepSecond(2);

        movieFlux
            .take(5)
            .subscribe(Utils.subscriber("Peach"));


        Utils.sleepSecond(2);
        movieFlux
            .take(3)
            .subscribe(Utils.subscriber("Another me on mobile"));


        Utils.sleepSecond(15);
    }

    private static void demoAutoConnect() {
        var movieFlux = movieStreama().publish().autoConnect(0);

        Utils.sleepSecond(2);

        movieFlux
            .take(5)
            .subscribe(Utils.subscriber("Peach"));


        Utils.sleepSecond(2);
        movieFlux
            .take(3)
            .subscribe(Utils.subscriber("Another me on mobile"));


        Utils.sleepSecond(15); 
    }

    private static Flux<String> movieStreama() {
        return Flux.<String, Integer>generate(
            () -> {
                logger.info("start streaming");

                return 1;
            },
            (state, sink) -> {
                var scene = "movie scene: " + state;
                logger.info("playing {}", scene);

                sink.next(scene);
                state += 1;

                return state;
            }
        ).take(10)
        .delayElements(Duration.ofMillis(500));
    }
}
