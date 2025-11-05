package nl.wjglerum.reactive;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class ReactiveBeverage extends PanacheEntity {

    public String name;

    public ReactiveBeverage() {
    }

    public ReactiveBeverage(String name) {
        this.name = name;
    }
}
