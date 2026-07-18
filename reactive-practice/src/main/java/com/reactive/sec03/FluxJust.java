package com.reactive.sec03;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class FluxJust {
    public static void main(String[] args) {
        Flux.just(1, 2, 3, 4, 5)
            .subscribe(Utils.subscriber());
    }
}
