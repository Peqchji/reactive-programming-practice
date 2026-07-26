package com.sec08;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class MultipleSubscriberLimit {
    private static final Logger logger = LoggerFactory.getLogger(MultipleSubscriberLimit.class);

    public static void main(String[] args) {

        var producer = Flux.<Integer, Integer>generate(
            () -> 1,
            (state, sink) -> {
                logger.info("generated: {}", state);
                sink.next(state);

                return ++state;
            }
        ).subscribeOn(Schedulers.parallel());

        var latcher = new CountDownLatch(2);
        producer
            .limitRate(5)
            .publishOn(Schedulers.boundedElastic())
            .map(MultipleSubscriberLimit::timeConsumingTask)
            .doFinally(signalType -> {
                latcher.countDown();
            })
            .subscribe(Utils.subscriber("sub1"));
        
        producer
            .take(100)
            .publishOn(Schedulers.boundedElastic())
            .doFinally(signalType -> {
                latcher.countDown();
            })
            .subscribe(Utils.subscriber("sub2"));
        

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
