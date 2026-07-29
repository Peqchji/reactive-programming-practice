package com.reactive.sec09;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class CollectList {
    public static void main(String[] args) {
        Flux.range(1, 10)
            .collectList()
            .subscribe(Utils.subscriber());
    }
}
