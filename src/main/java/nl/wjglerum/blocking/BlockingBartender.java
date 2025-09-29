package nl.wjglerum.blocking;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import nl.wjglerum.Beverage;

@ApplicationScoped
public class BlockingBartender {

    public Beverage getFromDraft() {
        Log.info("Going to pour a blocking guinness");
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Beverage("Blocking Guinness");
    }
}
