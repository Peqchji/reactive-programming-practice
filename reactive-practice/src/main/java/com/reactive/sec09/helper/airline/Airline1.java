package com.reactive.sec09.helper.airline;

import reactor.core.publisher.Flux;

public class Airline1 {
    private BaseAirline airline = new BaseAirline("Airline1");

    public Flux<Flight> getFlight() {
        return airline.getFlight();
    }
}
