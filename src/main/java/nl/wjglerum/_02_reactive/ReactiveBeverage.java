package nl.wjglerum._02_reactive;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "beverage")
public class ReactiveBeverage extends PanacheEntity {

    public String name;

    public ReactiveBeverage() {
    }

    public ReactiveBeverage(String name) {
        this.name = name;
    }
}
