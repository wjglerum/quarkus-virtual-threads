package nl.wjglerum.reactive;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReactiveBeverageRepository implements PanacheRepository<ReactiveBeverage> {

    Uni<ReactiveBeverage> save(ReactiveBeverage beverage) {
        Log.info("Persisting reactive beverage");
        return persist(beverage);
    }
}
