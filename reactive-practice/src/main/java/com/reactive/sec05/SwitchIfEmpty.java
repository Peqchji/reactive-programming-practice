package com.reactive.sec05;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SwitchIfEmpty {
    public static void main(String[] args) {
        // Mono.just
        Mono.just(123)
            .switchIfEmpty(fallbackMono())
            .subscribe(Utils.subscriber());

        // empty -> fallback
        Mono.empty()
            .switchIfEmpty(fallbackMono())
            .subscribe(Utils.subscriber());

        // Flux.range
        Flux.range(1, 2)
            .filter(i -> i > 10)
            .switchIfEmpty(fallbackFlux())
            .subscribe(Utils.subscriber());
    }
    
    private static Mono<Integer> fallbackMono() {
        return Mono.just(123);
    }

    private static Flux<Integer> fallbackFlux() {
        return Flux.range(10, 5);
    }
}
