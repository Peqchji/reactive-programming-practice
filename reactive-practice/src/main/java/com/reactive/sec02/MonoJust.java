package com.reactive.sec02;

import org.reactivestreams.Publisher;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import com.reactive.sec01.subscriber.SubscriberImpl;

import reactor.core.publisher.Mono;

public class MonoJust {
    // private static final Logger logger = LoggerFactory.getLogger(MonoJust.class);

    
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
