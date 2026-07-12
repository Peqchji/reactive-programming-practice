package com.reactive.sec02;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Mono;

public class MonoFromFuture {
    private static final Logger logger = LoggerFactory.getLogger(MonoFromFuture.class);

    public static void main(String[] args) {
        Mono.fromFuture(MonoFromFuture::getName)
                .subscribe(Utils.subscriber());

        Utils.sleepSecond(1);
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
            logger.info("generate new name");

            return Utils.faker().name().firstName();
        });
    }
}
