import java.time.Duration;

import reactor.core.publisher.Mono;

public class ValidationTask {

	public static Mono validate(Duration duration) {
		if (!duration.isNegative() && !duration.isZero()) {
			return Mono.empty();
		}
		else return Mono.error(new Error());
	}
}