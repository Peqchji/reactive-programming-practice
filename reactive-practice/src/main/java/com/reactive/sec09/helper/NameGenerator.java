package com.reactive.sec09.helper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;

public class NameGenerator {

    private static final Logger logger = LoggerFactory.getLogger(NameGenerator.class);
    private static final List<String> cache = new ArrayList<String>();

    public Flux<String> generateName() {
        return Flux.<String>generate(sink -> {
            logger.info("generate new name");
            Utils.sleepSecond(1);

            var newName = Utils.faker().name().name();
            cache.add(newName);

            sink.next(newName);
        })
        .startWith(cache);
    }
}
