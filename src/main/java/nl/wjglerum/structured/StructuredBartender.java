package nl.wjglerum.structured;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import nl.wjglerum.Beverage;

@ApplicationScoped
public class StructuredBartender {

    public Beverage getFromDraft(String name) {
        Log.info("Going to pour a structured guinness");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Beverage("Structured Guinness for " + name);
    }
}
