package nl.wjglerum.reactive;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReactiveBeverageRepository implements PanacheRepository<ReactiveBeverage> {
}
