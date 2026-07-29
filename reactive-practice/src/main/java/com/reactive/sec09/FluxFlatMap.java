package com.reactive.sec09;

import java.util.concurrent.CountDownLatch;

import com.reactive.common.Utils;
import com.reactive.sec09.application.PaymentService;
import com.reactive.sec09.application.User;
import com.reactive.sec09.application.UserService;

public class FluxFlatMap {
    public static void main(String[] args) {
        var latcher = new CountDownLatch(1);

        UserService.getAllUsers()
                .map(User::id)
                .flatMap(PaymentService::getUserBalance)
                .doFinally(s -> latcher.countDown())
                .subscribe(Utils.subscriber()); 

        try {
            latcher.await();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
