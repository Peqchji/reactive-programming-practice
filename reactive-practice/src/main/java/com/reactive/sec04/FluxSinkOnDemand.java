package com.reactive.sec04;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class FluxSinkOnDemand {
    public static void main(String[] args) {
        // item in flux pipeline yet
        var flux = Flux.create(sink -> {
            sink.onRequest(req -> {
                for (int i = 0; i < 10; i += 1) {
                    var name = Utils.faker().funnyName().name();

                    sink.next(name);
                }
            });

            sink.complete();
        });

        // subscribe to drain
        flux.subscribe(
            Utils.subscriber()
        );
    }
}
