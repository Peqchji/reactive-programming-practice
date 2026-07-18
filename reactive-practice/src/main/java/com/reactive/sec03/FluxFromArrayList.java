package com.reactive.sec03;

import java.util.List;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class FluxFromArrayList {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 1};
        var list = List.of(1, 2, 3, 4, 5, "HI");

        Flux.<Integer>fromArray(array)
            .subscribe(Utils.subscriber("from_array"));

        Flux.fromIterable(list)
            .subscribe(Utils.subscriber("from_list"));
    }
}
