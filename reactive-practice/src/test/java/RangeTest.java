import org.junit.jupiter.api.Test;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class RangeTest {
    private Flux<Integer> getItems() {
        return Flux.range(1, 50);
    }

    private Flux<Integer> getItems2() {
        return Flux.range(1, 50)
                .map(i -> Utils.faker().random().nextInt(1, 100));
    }

    @Test
    public void fluxTest1() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3)
                .expectNextCount(47)
                .expectComplete()
                .verify();
    }

    @Test
    public void fluxTest2() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3)
                .expectNextCount(22)
                .expectNext(26, 27, 28)
                .expectNextCount(22)
                .expectComplete()
                .verify();
    }

    @Test
    public void fluxTest3() {
        StepVerifier.create(getItems2())
                .expectNextMatches(i -> i > 0 && i < 101)
                .expectNextCount(49)
                .expectComplete()
                .verify();
    }

    @Test
    public void fluxTest4() {
        StepVerifier.create(getItems2())
                .thenConsumeWhile(i -> i > 0 && i < 101)
                .expectComplete()
                .verify();
    }
}
