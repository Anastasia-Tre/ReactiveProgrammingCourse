import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserActivityUtils {

	public static Mono<Product> findMostExpansivePurchase(Flux<Order> ordersHistory,
			ProductsCatalog productsCatalog) {

		return ordersHistory
				.flatMapIterable(order -> order.getProductsIds())
				.map(id -> productsCatalog.findById(id))
				.reduce((a, b) -> {
					if (a.getPrice() > b.getPrice()) return a;
					else return b;
				});
	}
}
