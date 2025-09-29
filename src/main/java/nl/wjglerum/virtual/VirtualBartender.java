package nl.wjglerum.virtual;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import nl.wjglerum.Beverage;

@ApplicationScoped
public class VirtualBartender {

    public Beverage getFromDraft() {
        Log.info("Going to pour a virtual guinness");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Beverage("Virtual Guinness");
    }
}
