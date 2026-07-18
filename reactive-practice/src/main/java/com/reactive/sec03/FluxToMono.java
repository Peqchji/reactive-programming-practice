package com.reactive.sec03;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxToMono {
    public static void main(String[] args) {
        var namer = getNameWithId(2);
        save(Flux.from(namer));

        var flux = Flux.range(1, 10);
        Mono
            .from(flux)
            .subscribe(Utils.subscriber("mock"));
    }

    private static Mono<String> getNameWithId(int id) {
        return switch (id) {
            case 1 -> Mono.just("im mono");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("WRONGGGGGGGGGG ID"));
        };
    }

    private static void save(Flux<String> flux) {
        flux.subscribe(Utils.subscriber("saver"));
    }
}
