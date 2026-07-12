package com.reactive.sec02;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyStream {

    private static final Logger logger = LoggerFactory.getLogger(LazyStream.class);

    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4)
            .peek(i -> logger.info("recieve: {}", i))
            .toList();
    }
    
}
