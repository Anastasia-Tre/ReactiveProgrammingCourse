import java.util.concurrent.CountDownLatch;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

public class Task {

	public static void dynamicDemand(Flux<String> source, CountDownLatch countDownOnComplete) {

		source.subscribe(new BaseSubscriber<String>() {
			long number = 1;
			long counter = 0;

			@Override
			protected void hookOnSubscribe(Subscription subscription) {
				request(number);
			}

			@Override
			protected void hookOnNext(String value) {
				counter++;

				if (counter == number) {
					counter = 0;
					number *= 2;
					request(number);
				}
			}

			@Override
			protected void hookFinally(SignalType type) {
				countDownOnComplete.countDown();
			}
		});
	}
}