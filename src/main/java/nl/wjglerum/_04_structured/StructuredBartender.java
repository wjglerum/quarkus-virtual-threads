package nl.wjglerum._04_structured;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StructuredBartender {

    public StructuredBeverage get() {
        Log.info("Warming up the structured coffee machine");
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new StructuredBeverage("Structured coffee");
    }
}
