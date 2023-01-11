import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Task {

	public static Mono<Long> firstFromFlux(Flux<Long> flux) {
		return Mono.from(flux.take(1));
	}
}