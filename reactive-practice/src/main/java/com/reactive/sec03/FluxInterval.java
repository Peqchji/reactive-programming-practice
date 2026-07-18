package com.reactive.sec03;

import java.time.Duration;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class FluxInterval {
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(200))
            .subscribe(Utils.subscriber());
        
        Utils.sleepSecond(3);
    }
}
