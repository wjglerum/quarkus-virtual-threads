package nl.wjglerum;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BlockingBeerService {

    public Beer getFromDraft() {
        Log.info("Going to pour a blocking guinness");
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Beer("Blocking Guinness");
    }
}
