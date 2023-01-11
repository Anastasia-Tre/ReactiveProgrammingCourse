import reactor.core.publisher.Flux;

import java.util.Arrays;

public class Task {

	public static Flux<Character> createSequence(Flux<String> stringFlux) {
		return stringFlux
				.flatMapIterable(elem -> Arrays.asList(elem.split("")))
				.map(elem -> elem.charAt(0));
	}
}