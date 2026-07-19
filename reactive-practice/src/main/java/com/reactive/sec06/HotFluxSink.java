package com.reactive.sec06;

import com.reactive.common.Utils;
import com.reactive.sec04.helper.SinkEmitter;

import reactor.core.publisher.Flux;

public class HotFluxSink {

    public static void main(String[] args) {
        var emitter = new SinkEmitter<String>();
        var flux = Flux.create(emitter).share();

        flux.subscribe(Utils.subscriber("sub1"));
        flux.subscribe(Utils.subscriber("sub2"));

        for (int i = 0; i < 10; i += 1) {
            emitter.emit(
                Utils.faker().funnyName().name()
            );
        }
    }
}
