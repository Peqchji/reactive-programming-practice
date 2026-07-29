package com.reactive.sec09;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Then {
    private static final Logger logger = LoggerFactory.getLogger(Then.class);


    public static void main(String[] args) {
        var records = List.of("a", "b", "c");
        var latcher = new CountDownLatch(1);

        saveRecords(records)
            .then(sendNotri(records))
            .doFinally(s -> latcher.countDown())
            .subscribe(Utils.subscriber());
        
        try {
            latcher.await();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private static Flux<String> saveRecords(List<String> records) {
        return Flux.fromIterable(records)
                .map(r -> "saved " + r) 
                .log()
                .delayElements(Duration.ofMillis(500));
    }

    private static Mono<Void> sendNotri(List<String> records) {
        return Mono.fromRunnable(() -> logger.info("all these {} records saved", records));
    }
}
