import java.util.function.UnaryOperator;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class PublisherTest {
    private static UnaryOperator<Flux<String>> processor() {
        return flux -> flux.filter(s -> s.length() > 1)
                .map(String::toUpperCase)
                .map(s -> s + ", size: " + s.length());
    }

    @Test
    public void publisherTest() {
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();

        StepVerifier.create(
                flux.transform(
                        processor()))
                .then(
                        () -> publisher.emit("a", "b", "bruh"))
                .expectNext("BRUH, size: 4")
                .verifyComplete();
    }
}
