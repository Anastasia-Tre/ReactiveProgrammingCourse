import java.util.function.BiConsumer;
import java.util.function.Function;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

public class Task {

	public static Publisher<Integer> provideHandmadeContinuation(Flux<Integer> values,
			Function<Integer,
			Integer> mapping) {

		return values
				.handle((value, sink) -> {
					if (value != 0) sink.next(mapping.apply(value));
				});
	}
}