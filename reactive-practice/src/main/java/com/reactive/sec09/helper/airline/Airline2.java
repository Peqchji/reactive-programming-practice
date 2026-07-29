package com.reactive.sec09.helper.airline;

import reactor.core.publisher.Flux;

public class Airline2 {
    private BaseAirline airline = new BaseAirline("Airline2");

    public Flux<Flight> getFlight() {
        return airline.getFlight();
    }
}
