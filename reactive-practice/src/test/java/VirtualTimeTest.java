import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class VirtualTimeTest {
    private Flux<Integer> getItems() {
        return Flux.range(1, 5)
            .delayElements(Duration.ofSeconds(1));
    }

    @Test
    public void rangeTestWithoutVirtualTime() {
        StepVerifier.create(getItems())
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    public void rangeTestWithVirtualTime() {
        StepVerifier.withVirtualTime(this::getItems)
                .expectSubscription()
                .expectNoEvent(Duration.ofMillis(900))
                .thenAwait(Duration.ofSeconds(6))
                .expectNextCount(5)
                .verifyComplete();
    }
}
