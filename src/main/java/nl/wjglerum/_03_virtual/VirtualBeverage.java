package nl.wjglerum._03_virtual;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class VirtualBeverage extends PanacheEntity {

    public String name;

    public VirtualBeverage() {
    }

    public VirtualBeverage(String name) {
        this.name = name;
    }
}
