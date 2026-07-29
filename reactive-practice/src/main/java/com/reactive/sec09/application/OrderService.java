package com.reactive.sec09.application;


import com.reactive.common.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
    Just for demo.
    Imagine order-service, as an application, has an endpoint.
    This is a client class to make a call to the endpoint (IO request).
 */
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private static final Map<Integer, List<Order>> orderTable = Map.of(
            1, List.of(
                    new Order(1, Utils.faker().commerce().productName(), Utils.faker().random().nextInt(10, 100)),
                    new Order(1, Utils.faker().commerce().productName(), Utils.faker().random().nextInt(10, 100))
            ),
            2, List.of(
                    new Order(2, Utils.faker().commerce().productName(), Utils.faker().random().nextInt(10, 100)),
                    new Order(2, Utils.faker().commerce().productName(), Utils.faker().random().nextInt(10, 100)),
                    new Order(2, Utils.faker().commerce().productName(), Utils.faker().random().nextInt(10, 100))
            ),
            3, List.of()
    );

    public static Flux<Order> getUserOrders(Integer userId) {
        return Flux.fromIterable(orderTable.get(userId))
                   .delayElements(Duration.ofMillis(500))
                   .transform(Utils.fluxLogger("order-for-user" + userId, logger));
    }

}
