package com.reactive.sec03;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class FluxLog {
    public static void main(String[] args) {
        Flux.range(1, 10)
            .log("range")
            .map(i -> Utils.faker().funnyName().name())
            .log("random")
            .subscribe(Utils.subscriber());

    }
}
