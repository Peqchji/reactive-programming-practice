package com.reactive.sec08;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FluxCreateWithDropStrat {
     private static final Logger logger = LoggerFactory.getLogger(FluxCreateWithDropStrat.class);

    public static void main(String[] args) {

        var producer = Flux.<Integer>create(
            (sink) -> {
                for (int i = 0; i < 500 && !sink.isCancelled(); i += 1) {
                    logger.info("generating: {}", i);
                    sink.next(i);
                    Utils.sleep(Duration.ofMillis(50));
                }

                sink.complete();
            }
        ).subscribeOn(Schedulers.parallel());

        var latcher = new CountDownLatch(1);
        producer
            .onBackpressureDrop()
            .log()
            .limitRate(1)
            .publishOn(Schedulers.boundedElastic())
            .map(FluxCreateWithDropStrat::timeConsumingTask)
            .doFinally(signalType -> {
                latcher.countDown();
            })
            .subscribe();
        
        try {
            latcher.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static int timeConsumingTask(int i) {
        logger.info("recieve: {}", i);
        Utils.sleepSecond(1);
        return i;
    }
}
