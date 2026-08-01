import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class ContextTest {
    private static Mono<String> getWelcomeMsg() {
        return Mono
                .deferContextual(ctx -> {
                    if (ctx.hasKey("Hello")) {
                        return Mono.just(ctx.get("Hello"));
                    }

                    return Mono.error(new RuntimeException("Not Hello World"));
                });
    }

    @Test
    public void welcomeMsgTest1() {
        var options = StepVerifierOptions.create()
                .withInitialContext(Context.of("Hello", "World"));

        StepVerifier.create(getWelcomeMsg(), options)
                .expectNext("World")
                .verifyComplete();

    }

    @Test
    public void welcomeMsgTest2() {
        var options = StepVerifierOptions.create()
                .withInitialContext(Context.of("1", "World"));

        StepVerifier.create(getWelcomeMsg(), options)
                .expectErrorMessage("Not Hello World")
                .verify();

    }
}
