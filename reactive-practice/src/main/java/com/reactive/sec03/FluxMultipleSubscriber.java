package com.reactive.sec03;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class FluxMultipleSubscriber {
    public static void main(String[] args) {
        var flux = Flux.just(1, 2, 3, 4, 5);

        flux.subscribe(Utils.subscriber("sub1"));
        flux.subscribe(Utils.subscriber("sub2"));

        flux
            .filter(number -> number%2 == 0)
            .subscribe(Utils.subscriber("sub3"));

        flux
            .reduce((prev ,number) -> prev + number )
            .subscribe(Utils.subscriber("sub4"));
    }
}
