package com.reactive.sec10;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import com.reactive.common.Utils;

import reactor.core.publisher.Mono;

/**
 * Repeat
 */
public class Repeat {

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
    }

    private static void demo1() {
        var latcher = new CountDownLatch(1);
        Mono<String> generator = getCountryName();

        generator
            .repeat(3)
            .doFinally(sig -> latcher.countDown())
            .subscribe(Utils.subscriber());
        
        

        try {
            latcher.await();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private static void demo2() {
        var latcher = new CountDownLatch(1);
        Mono<String> generator = getCountryName();

        generator
            .repeat()
            .takeUntil(name -> name.equalsIgnoreCase("thailand"))
            .doFinally(sig -> latcher.countDown())
            .subscribe(Utils.subscriber());
        
        

        try {
            latcher.await();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private static void demo3() {
        var latcher = new CountDownLatch(1);
        AtomicInteger counter = new AtomicInteger(0);
        Mono<String> generator = getCountryName();

        generator
            .repeat(() -> counter.incrementAndGet() < 3)
            .doFinally(sig -> latcher.countDown())
            .subscribe(Utils.subscriber());
        
        

        try {
            latcher.await();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private static void demo4() {
        var latcher = new CountDownLatch(1);
        Mono<String> generator = getCountryName();

        generator
            .repeatWhen(flux -> flux.delayElements(Duration.ofMillis(500)).take(5))
            .doFinally(sig -> latcher.countDown())
            .subscribe(Utils.subscriber());
        
        

        try {
            latcher.await();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private static Mono<String> getCountryName() {
        return Mono.fromSupplier(() -> Utils.faker().country().name());
    }
}