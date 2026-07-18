package com.reactive.sec03;

import java.util.List;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class FluxFromStream {

    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4, 5, "SOMETHING", "123", "2", true);
        var stream = list.stream();

        var fluxStream = Flux.fromStream(stream);

        fluxStream
            .filter(element -> element instanceof String)
            .subscribe(Utils.subscriber("sub1"));
        // will error cause we can't consume stream twice
        fluxStream
            .onErrorResume(throwable -> Flux.just("ERROR HERE BRO"))
            .subscribe(Utils.subscriber("sub2"));
        
        // this appoach create stream for each subscriber
        var fluxStream2 = Flux.fromStream(list::stream);
        fluxStream2
            .filter(element -> element instanceof String)
            .subscribe(Utils.subscriber("sub3"));
        fluxStream2
            .subscribe(Utils.subscriber("sub4"));
    }
}
