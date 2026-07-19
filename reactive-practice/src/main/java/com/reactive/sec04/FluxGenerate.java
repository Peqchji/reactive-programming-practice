package com.reactive.sec04;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class FluxGenerate {
   public static void main(String[] args) {
        var generator = Flux.<Integer, Integer>generate(
            () -> {
                Integer counter = 0;

                return counter;
            },
            (counter, syncSink) -> {
                counter += 1;

                syncSink.next(counter);
                return counter;
            }
        );

        generator.take(100)
            .subscribe(Utils.subscriber());
        
        generator.takeUntil(i -> i == 20)
            .subscribe(Utils.subscriber());
   } 
}
