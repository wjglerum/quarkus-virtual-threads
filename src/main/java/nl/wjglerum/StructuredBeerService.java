package nl.wjglerum;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StructuredBeerService {

    public Beer getFromDraft() {
        Log.info("Going to pour a structured guinness");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Beer("Structured Guinness");
    }
}
