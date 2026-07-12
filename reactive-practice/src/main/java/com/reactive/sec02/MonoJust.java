package com.reactive.sec02;

import org.reactivestreams.Publisher;


import com.reactive.sec01.subscriber.SubscriberImpl;

import reactor.core.publisher.Mono;

public class MonoJust {

    
    public static void main(String[] args) {
        Publisher<String> mono = Mono.just("demo");

        var subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);

        var subscription = subscriber.geSubscription();
        subscription.request(3);
        subscription.request(3);

        subscription.cancel();
        subscription.request(3);
    }
    
}
