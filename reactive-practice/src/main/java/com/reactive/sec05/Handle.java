package com.reactive.sec05;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class Handle {
    public static void main(String[] args) {
        demo2();
    }

    public static void demo2() {
        Flux<String> generator = Flux.<String>generate(syncSink -> {
            syncSink.next(
                Utils.faker().funnyName().name()
            );
        });

        generator
            .handle(
                (item, syncSink) -> {
                    syncSink.next(item);

                    if (item.startsWith("A")) {
                        syncSink.complete();
                    }
                }
            )
            .subscribe(Utils.subscriber());
    }

    public static void demo() {
        Flux<Integer> range = Flux.range(1, 10);

        range
            // .filter(i -> i != 7)
            .handle((item, syncSink) -> {
                switch (item) {
                    case 1 -> syncSink.next(-2); 
                    case 4 -> {}
                    case 7 -> syncSink.error(new RuntimeException("7"));
                    default -> syncSink.next(item);
                }
            })
            .subscribe(Utils.subscriber());
    }


}
