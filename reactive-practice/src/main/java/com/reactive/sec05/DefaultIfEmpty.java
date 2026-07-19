package com.reactive.sec05;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DefaultIfEmpty {
    public static void main(String[] args) {
        // not -1
        Mono.just(123)
            .defaultIfEmpty(-1)
            .subscribe(Utils.subscriber());

        // empty -> -1
        Mono.empty()
            .defaultIfEmpty(-1)
            .subscribe(Utils.subscriber());

        // empty -> -2
        Flux.range(1, 10)
            .filter(i -> i > 100)
            .defaultIfEmpty(-2)
            .subscribe(Utils.subscriber());
    }
}
