package nl.wjglerum._04_structured;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "beverage")
public class StructuredBeverage extends PanacheEntity {

    public String name;

    public StructuredBeverage() {
    }

    public StructuredBeverage(String name) {
        this.name = name;
    }
}
