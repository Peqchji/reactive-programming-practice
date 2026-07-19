package com.reactive.sec06;

import com.reactive.common.Utils;
import com.reactive.sec04.helper.SinkEmitter;

import reactor.core.publisher.Flux;

public class ColdPublisher {

    public static void main(String[] args) {
        var emitter = new SinkEmitter<String>();
        var flux = Flux.create(emitter);

        flux.subscribe(Utils.subscriber("sub1"));
        // only sub2 who can get sink value.
        // as you see sink object can only be held only one instance at the time.
        flux.subscribe(Utils.subscriber("sub2"));

        for (int i = 0; i < 10; i += 1) {
            emitter.emit(
                Utils.faker().funnyName().name()
            );
        }
    }
}
