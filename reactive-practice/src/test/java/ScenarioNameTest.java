import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class ScenarioNameTest {
    private Flux<Integer> getItems() {
        return Flux.range(1, 3);
    }

    @Test
    public void fluxTest1() {
        var options = StepVerifierOptions.create().scenarioName("1 to 3 items test");

        StepVerifier.create(getItems(), options)
                .expectNext(11)
                .as("first")
                .expectNext(2, 3)
                .as("second and third")
                .expectComplete()
                .verify();
    }

}
