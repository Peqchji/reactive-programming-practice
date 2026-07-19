package com.reactive.sec04.helper;

import java.util.function.Consumer;

import reactor.core.publisher.FluxSink;

public class SinkEmitter<T> implements Consumer<FluxSink<T>> {
    private FluxSink<T> sink;

    @Override
    public void accept(FluxSink<T> sink) {
        this.sink = sink;
    }

    public void emit(T i) {
        sink.next(i);
    }
}
