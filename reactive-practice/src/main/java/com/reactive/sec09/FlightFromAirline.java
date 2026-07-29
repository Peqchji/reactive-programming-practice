package com.reactive.sec09;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import com.reactive.common.Utils;
import com.reactive.sec09.helper.airline.Airline1;
import com.reactive.sec09.helper.airline.Airline2;

import reactor.core.publisher.Flux;

public class FlightFromAirline {
    public static void main(String[] args) {
        CountDownLatch latcher = new CountDownLatch(1);

        var airline1 = new Airline1();
        var airline2 = new Airline2();

        Flux.merge(
            airline1.getFlight(),
            airline2.getFlight()
        ).take(Duration.ofSeconds(2))
        .doFinally(i -> latcher.countDown())
        .subscribe(Utils.subscriber());

        try {
            latcher.await();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
