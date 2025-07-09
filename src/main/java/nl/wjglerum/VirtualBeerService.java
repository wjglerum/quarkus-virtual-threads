package nl.wjglerum;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VirtualBeerService {

    public Beer getFromDraft() {
        Log.info("Going to pour a virtual guinness");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Beer("Virtual Guinness");
    }
}
