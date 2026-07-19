package com.reactive.sec04;

import reactor.core.publisher.Flux;

import com.reactive.common.Utils;

public class Take {
    public static void main(String[] args) {
        Flux.range(1, 100)
            .log("range")
            .take(10)
            .log("take")
            .subscribe(Utils.subscriber());

        Flux.range(1, 100)
            .log("range")
            .takeWhile(i -> i < 5)
            .log("take")
            .subscribe(Utils.subscriber());
    }
}
