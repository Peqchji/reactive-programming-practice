package com.reactive.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Mono;

public class MonoRunnable {
    private static final Logger logger = LoggerFactory.getLogger(MonoSupplier.class);

    public static void main(String[] args) {
        getProductName(1)
            .subscribe(Utils.subscriber());
        
        getProductName(2)
            .subscribe(Utils.subscriber());

        getProductName(3)
            .subscribe(Utils.subscriber());
    }

    private static Mono<String> getProductName(int ID) {
        if (ID == 1) {
            return Mono.fromSupplier(() -> Utils.faker().commerce().productName());
        }

        return Mono.fromRunnable(() -> MonoRunnable.notifyBusiness(ID));
    }

    private static void notifyBusiness(int ID) {
        logger.info("notifying business on non-existing product id: {}", ID);
    }
}
