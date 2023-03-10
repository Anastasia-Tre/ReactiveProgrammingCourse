import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;

import java.util.logging.Level;

public class Task {

	public static Flux<Long> loggerTask(Flux<Long> flux) {
		return flux
				.log("Before", Level.INFO, SignalType.REQUEST)
				.subscribeOn(Schedulers.parallel())
				.publishOn(Schedulers.single())
				.log("After");
	}
}