package com.reactive.sec09;

import java.util.concurrent.CountDownLatch;

import com.reactive.common.Utils;
import com.reactive.sec09.application.OrderService;
import com.reactive.sec09.application.UserService;

public class MonoFlatMapMany
 {
    public static void main(String[] args) {
        var latcher = new CountDownLatch(1);

        UserService.getUserId("sam")
            .flatMapMany(OrderService::getUserOrders)
            .doFinally(s -> latcher.countDown())
            .subscribe(Utils.subscriber());

        try {
            latcher.await();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
