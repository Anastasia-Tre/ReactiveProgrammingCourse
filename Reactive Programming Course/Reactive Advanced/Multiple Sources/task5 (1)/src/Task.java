import java.util.stream.Collectors;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

public class Task {

	public static Publisher<Tuple2<Character, Integer>> groupWordsByFirstLatter(Flux<String> words) {
		return words
				.transform(elem -> groupByFirstLetter(elem))
				.transform(elem -> countLettersInWordsInGroup(elem));
	}

	public static Flux<GroupedFlux<Character, String>> groupByFirstLetter(Flux<String> words) {
		return words.groupBy(word -> word.charAt(0));
	}

	public static Flux<Tuple2<Character, Integer>> countLettersInWordsInGroup(Flux<GroupedFlux<Character,
			String>> groupedWords) {
		return groupedWords
				.flatMap(wordsFlux -> wordsFlux
						.map(word -> {
							int number = 0;
							for (char character : word.toCharArray()) {
								if (character == wordsFlux.key()) number++;
							}
							return number;
						})
						.collect(Collectors.summingInt(num -> num.intValue()))
						.map(number -> Tuples.of(wordsFlux.key(), number))
				);
	}
}