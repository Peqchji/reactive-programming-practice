package com.reactive.sec11;

import com.reactive.common.Utils;

import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Contexts {

    public static void main(String[] args) {
        getWelcomeMsg()
            .contextWrite(ctx -> ctx.delete("Hello"))
            .contextWrite(Context.of("Another", "one").put("Hi", "Earth"))
            .contextWrite(Context.of("Hello", "World"))
            .subscribe(Utils.subscriber());
    }

    private static Mono<String> getWelcomeMsg() {
        return Mono
            .deferContextual(ctx -> {
                if (ctx.hasKey("Hello")) {
                    return Mono.just(ctx.get("Hello"));
                }

                return Mono.error(new RuntimeException("Not Hello World"));
            });
    }
}
