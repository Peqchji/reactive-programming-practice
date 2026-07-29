package com.reactive.sec09;

import com.reactive.common.Utils;
import com.reactive.sec09.application.PaymentService;
import com.reactive.sec09.application.UserService;

public class MonoFlatMap {
    public static void main(String[] args) {
        UserService.getUserId("sam")
            .flatMap(PaymentService::getUserBalance)
            .subscribe(Utils.subscriber());
    }
}
