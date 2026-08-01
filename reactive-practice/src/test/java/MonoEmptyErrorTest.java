import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoEmptyErrorTest {
    private static Mono<String> getUsername(int userID) {
        return switch (userID) {
            case 1 -> Mono.just("peach");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Invalid input"));
        };
    }

    @Test
    public void emptyTest() {
        StepVerifier.create(getUsername(2))
                .expectComplete()
                .verify();
    }

    @Test
    public void errorTest1() {
        StepVerifier.create(getUsername(3))
                .expectError()
                .verify();
    }

    @Test
    public void errorTest2() {
        StepVerifier.create(getUsername(3))
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void errorTest3() {
        StepVerifier.create(getUsername(3))
                .expectErrorMessage("Invalid input")
                .verify();
    }

    @Test
    public void errorTest4() {
        StepVerifier.create(getUsername(3))
                .consumeErrorWith(ex -> {
                    Assertions.assertEquals(RuntimeException.class, ex.getClass());
                    Assertions.assertEquals("Invalid input", ex.getMessage());
                })
                .verify();
    }
}
