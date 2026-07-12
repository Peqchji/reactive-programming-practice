package com.reactive.common;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSubscriber<T> implements Subscriber<T> {
    private static final Logger logger = LoggerFactory.getLogger(DefaultSubscriber.class);
    
    private final String name;

    public DefaultSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override 
    public void onNext(T item) {
        logger.info("{}: recieved: {}", this.name, item);
    }

    @Override
    public void onError(Throwable throwable) {
        logger.error("{}: Errors",  this.name, throwable);
    }

    @Override
    public void onComplete() {
        logger.info("{}: Completed", this.name);
    }
}
