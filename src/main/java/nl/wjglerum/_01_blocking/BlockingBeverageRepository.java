package nl.wjglerum._01_blocking;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class BlockingBeverageRepository implements PanacheRepository<BlockingBeverage> {

    void save(BlockingBeverage beverage) {
        Log.info("Persisting blocking beverage " + beverage);
        persist(beverage);
    }

    void save(List<BlockingBeverage> beverages) {
        Log.info("Persisting blocking beverages " + beverages);
        persist(beverages);
    }
}
