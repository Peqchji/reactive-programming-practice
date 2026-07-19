package com.reactive.sec04;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;
import com.reactive.sec04.helper.SinkEmitter;

import reactor.core.publisher.Flux;

public class FluxSinkThreadSafe {
    private static final Logger logger = LoggerFactory.getLogger(FluxSinkThreadSafe.class);

    public static void main(String[] args) {
        logger.info("Not Thread safe adding");
        FluxSinkThreadSafe.nonThreadSafeDemo();

        logger.info("Thread safe adding");
        FluxSinkThreadSafe.threadSafeDemo();
    }

    private static void threadSafeDemo() {
        var list = new ArrayList<Integer>();
        var emitter = new SinkEmitter<Integer>();
        var flux = Flux.<Integer>create(emitter);
        flux.subscribe(list::add);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i += 1) {
                emitter.emit(i);
            }
        };

        for (int i = 0; i < 10; i += 1) {
            Thread.ofVirtual().start(task);
        }

        Utils.sleepSecond(2);
        logger.info("list size: {}", list.size());
    }

    private static void nonThreadSafeDemo() {
        var list = new ArrayList<Integer>();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i += 1) {
                list.add(i);
            }
        };

        for (int i = 0; i < 10; i += 1) {
            Thread.ofVirtual().start(task);
        }

        Utils.sleepSecond(2);
        logger.info("list size: {}", list.size());
    }
}
