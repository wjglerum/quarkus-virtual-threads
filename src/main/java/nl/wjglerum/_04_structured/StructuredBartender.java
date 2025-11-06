package nl.wjglerum._04_structured;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StructuredBartender {

    public StructuredBeverage getFromDraft(String name) {
        Log.info("Going to pour a structured Guinness for " + name);
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new StructuredBeverage("Structured Guinness for " + name);
    }
}
