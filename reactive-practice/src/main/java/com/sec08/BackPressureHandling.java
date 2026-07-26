package com.sec08;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * BackPressureHandling
 */
public class BackPressureHandling {
    
    private static final Logger logger = LoggerFactory.getLogger(BackPressureHandling.class);

    public static void main(String[] args) {

        System.setProperty(
            "reactor.bufferSize.small", 
            "8"
        );

        var producer = Flux.<Integer, Integer>generate(
            () -> 1,
            (state, sink) -> {
                logger.info("generated: {}", state);
                sink.next(state);

                return ++state;
            }
        ).subscribeOn(Schedulers.parallel());

        var latcher = new CountDownLatch(1);
        producer
            .publishOn(Schedulers.boundedElastic())
            .map(BackPressureHandling::timeConsumingTask)
            .doFinally(signalType -> {
                logger.info("signal type: {}", signalType);

                latcher.countDown();
            })
            .subscribe(Utils.subscriber());
        
        try {
            latcher.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static int timeConsumingTask(int i) {
        Utils.sleepSecond(1);
        return i;
    }
}