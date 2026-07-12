package com.reactive.publisher;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;

public class SubscriptionImpl implements Subscription {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionImpl.class);
    private static final int MAX_ITEMS = 10;
    private final Faker faker;
    private final Subscriber<? super String> subscriber;

    private boolean isCancelled;
    private int count = 0;
    
    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long requestNumber) {
        if (requestNumber > MAX_ITEMS || requestNumber <= 0){
            this.subscriber.onError(new RuntimeException("validation failed"));
            this.isCancelled = true;
            return;
        }
        if (!isCancelled) {
            logger.info("subscriber has requested {} items", requestNumber);

            for (int i = 0; i < requestNumber && count < MAX_ITEMS; i++) {
                count += 1;

                this.subscriber.onNext(
                    this.faker.internet().emailAddress()
                );
            }

            if (count == MAX_ITEMS) {
                logger.info("NO MORE DATA");

                this.subscriber.onComplete();
                this.cancel();
            }
        }


    }

    @Override
    public void cancel() {
        logger.info("subscriber has cancelled");

        this.isCancelled = true;
    }
    
}
