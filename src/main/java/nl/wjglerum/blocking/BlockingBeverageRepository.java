package nl.wjglerum.blocking;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BlockingBeverageRepository implements PanacheRepository<BlockingBeverage> {
}
