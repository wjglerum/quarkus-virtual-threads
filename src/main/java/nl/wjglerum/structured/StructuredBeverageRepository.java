package nl.wjglerum.structured;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class StructuredBeverageRepository implements PanacheRepository<StructuredBeverage> {

    void save(List<StructuredBeverage> beverages) {
        Log.info("Persisting structured beverages");
        persist(beverages);
    }
}
