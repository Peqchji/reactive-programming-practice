package com.reactive.sec02;

import com.reactive.common.Utils;

import reactor.core.publisher.Mono;

public class MonoEmptyError {
    public static void main(String[] args) {
        var subscriber = Utils.subscriber("bruh");
        getUsername(1)
            .subscribe(subscriber);

        getUsername(2)
            .subscribe(subscriber);

        getUsername(3)
            .subscribe(subscriber);
    }

    private static Mono<String> getUsername(int userID) {
        return switch (userID) {
            case 1 -> Mono.just("peach");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Invalid input"));
        };
    }
}
