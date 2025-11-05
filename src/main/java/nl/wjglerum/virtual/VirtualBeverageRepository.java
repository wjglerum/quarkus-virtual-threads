package nl.wjglerum.virtual;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VirtualBeverageRepository implements PanacheRepository<VirtualBeverage> {
}
