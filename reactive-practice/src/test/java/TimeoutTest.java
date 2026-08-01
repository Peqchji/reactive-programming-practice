import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class TimeoutTest {
    private Flux<Integer> getItems() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(1));
    }

    @Test
    public void rangeTestWithoutVirtualTime() {
        StepVerifier.create(getItems())
                .expectNextCount(5)
                .expectComplete()
                .verify(Duration.ofMillis(6500));
    }
}
