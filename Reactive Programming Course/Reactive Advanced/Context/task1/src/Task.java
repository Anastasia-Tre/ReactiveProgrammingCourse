import reactor.core.publisher.Mono;

public class Task {

	public static Mono<String> grabDataFromTheGivenContext(Object key) {
		return Mono.deferContextual(
				context -> context.hasKey(key)
						? Mono.just(context.get(key))
						: Mono.empty()
				);
	}
}