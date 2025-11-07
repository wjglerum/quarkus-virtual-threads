package nl.wjglerum._03_virtual;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class VirtualBeverageRepository implements PanacheRepository<VirtualBeverage> {

    void save(VirtualBeverage beverage) {
        Log.info("Persisting virtual beverage");
        this.persist(beverage);
    }

    void save(List<VirtualBeverage> beverages) {
        Log.info("Persisting virtual beverages");
        this.persist(beverages);
    }
}
