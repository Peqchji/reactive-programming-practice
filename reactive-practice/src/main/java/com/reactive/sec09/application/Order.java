package com.reactive.sec09.application;

// just for demo
// we have user id in the order to show that it belongs to the user
public record Order(Integer userId,
                    String productName,
                    Integer price) {
}
