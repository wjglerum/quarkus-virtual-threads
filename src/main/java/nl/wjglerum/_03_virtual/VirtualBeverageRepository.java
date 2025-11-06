package nl.wjglerum._03_virtual;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VirtualBeverageRepository implements PanacheRepository<VirtualBeverage> {

    void save(VirtualBeverage beverage) {
        Log.info("Persisting virtual beverage");
        this.persist(beverage);
    }
}
