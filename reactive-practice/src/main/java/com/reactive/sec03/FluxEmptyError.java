package com.reactive.sec03;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class FluxEmptyError {
    public static void main(String[] args) {
        Flux.empty().subscribe(Utils.subscriber());
        Flux.error(new RuntimeException("TEST exception")).subscribe(Utils.subscriber());
    }
}
