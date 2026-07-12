package com.reactive.sec02;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Mono;

public class MonoSupplier {
    private static final Logger logger = LoggerFactory.getLogger(MonoSupplier.class);

    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4);
        Mono.fromSupplier(() -> sum(list))
                .subscribe(Utils.subscriber());
    }

    

    private static int sum(List<Integer> list) {
        logger.info("finding sum of {}", list);

        return list.stream().mapToInt(a -> a).sum();
    }
}
