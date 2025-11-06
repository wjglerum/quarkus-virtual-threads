package nl.wjglerum._01_blocking;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class BlockingBeverage extends PanacheEntity {

    public String name;

    public BlockingBeverage() {
    }

    public BlockingBeverage(String name) {
        this.name = name;
    }
}
