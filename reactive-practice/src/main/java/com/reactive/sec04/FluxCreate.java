package com.reactive.sec04;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class FluxCreate {
    public static void main(String[] args) {
        Flux.create(sink -> {
            String name;
            do {
                name = Utils.faker().name().firstName();
                sink.next(name);
            } while (!name.startsWith("A"));

            sink.complete();
        })
        .subscribe(Utils.subscriber());
    }
}
