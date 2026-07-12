package com.reactive;

import java.time.Duration;

import com.reactive.publisher.PublisherImpl;
import com.reactive.subscriber.SubscriberImpl;

public class Main {
    public static void main(String[] args) {
        demo4();
    }

    public static void demo1() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();

        publisher.subscribe(subscriber);
    }

    public static void demo2() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();

        publisher.subscribe(subscriber);

        try {
            for (int i = 0; i < 3; i += 1) {
                subscriber.geSubscription().request(3);
                Thread.sleep(Duration.ofSeconds(2));
            }

            subscriber.geSubscription().request(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void demo3() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();

        publisher.subscribe(subscriber);

        try {
            subscriber.geSubscription().request(3);
            Thread.sleep(Duration.ofSeconds(2));
   
            subscriber.geSubscription().cancel();
            subscriber.geSubscription().request(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void demo4() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();

        publisher.subscribe(subscriber);

        try {
            subscriber.geSubscription().request(3);
            Thread.sleep(Duration.ofSeconds(2));
   
            subscriber.geSubscription().request(11);
            Thread.sleep(Duration.ofSeconds(2));

            subscriber.geSubscription().request(3);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}