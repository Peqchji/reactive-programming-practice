import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {

    private Mono<String> getProduct(int id) {
        return Mono.fromSupplier(() -> "product-" + id);
    }

    @Test
    public void productTest() {
        StepVerifier
            .create(getProduct(1))
            .expectNext("product-1")
            .expectComplete()
            .verify();
    }
    
}
