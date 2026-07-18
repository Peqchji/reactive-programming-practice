package com.reactive.sec03;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class FluxRange {
    public static void main(String[] args) {
        Flux.range(3, 10).subscribe(Utils.subscriber());

        Flux.range(1, 10)
            .map(i -> Utils.faker().funnyName().name())
            .subscribe(Utils.subscriber());

    }
}
