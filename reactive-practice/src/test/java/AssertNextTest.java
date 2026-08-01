import java.util.Objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.reactive.common.Utils;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class AssertNextTest {
    record Book(int id, String author, String title) {
    }

    private Flux<Book> getBooks() {
        return Flux.range(1, 3)
                .map(i -> {
                    var book = Utils.faker().book();

                    return new Book(i, book.author(), book.title());
                });
    }

    @Test
    public void assertNextTest() {
        StepVerifier.create(getBooks())
                .assertNext(b -> Assertions.assertEquals(1, b.id()))
                .thenConsumeWhile(b -> Objects.nonNull(b))
                .expectComplete()
                .verify();
    }

    @Test
    public void collectAllAndTest() {
        StepVerifier.create(getBooks().collectList())
                .assertNext(list -> Assertions.assertEquals(3, list.size()))
                .expectComplete()
                .verify();
    }
}
