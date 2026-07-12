package com.reactive.sec02;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Mono;

public class MonoDefer {
        private static final Logger logger = LoggerFactory.getLogger(MonoDefer.class);

        public static void main(String[] args) {
            Mono.defer(MonoDefer::createPublisher).subscribe(Utils.subscriber());
        }

        private static Mono<Integer> createPublisher() {
            logger.info("create publisher");
            var list = List.of(1, 2, 3, 4, 5);

            Utils.sleepSecond(3);

            return Mono.fromSupplier(() -> sum(list));
        };

        private static int sum(List<Integer> list) {
            logger.info("finding sum of {}", list);
            Utils.sleepSecond(3);

            return list.stream().mapToInt(a -> a).sum();
        }
}
