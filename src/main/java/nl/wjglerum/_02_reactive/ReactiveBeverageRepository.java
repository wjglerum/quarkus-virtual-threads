package nl.wjglerum._02_reactive;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ReactiveBeverageRepository implements PanacheRepository<ReactiveBeverage> {

    Uni<Void> save(ReactiveBeverage beverage) {
        Log.info("Persisting reactive beverage");
        return persist(beverage).replaceWithVoid();
    }

    Uni<Void> save(List<ReactiveBeverage> beverages) {
        Log.info("Persisting reactive beverages");
        return persist(beverages);
    }
}
