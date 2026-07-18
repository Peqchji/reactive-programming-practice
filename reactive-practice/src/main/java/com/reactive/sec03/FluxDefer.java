package com.reactive.sec03;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class FluxDefer {
    private static final Logger logger = LoggerFactory.getLogger(FluxDefer.class);

    public static void main(String[] args) {
        // Flux
        //     .fromIterable(FluxDefer.getListofRandomName());
            // .subscribe(Utils.subscriber());

        // lazy declarative code. no excecution before subscribe
        Flux
            .defer(() -> Flux.fromIterable(FluxDefer.getListofRandomName()));
            // .subscribe(Utils.subscriber());
    }

    public static List<String> getListofRandomName() {
        var list = List.of(
            Utils.faker().funnyName().name(),
            Utils.faker().funnyName().name(),
            Utils.faker().funnyName().name()
        );
        logger.info("create random name list");

        return list;
    }
}
