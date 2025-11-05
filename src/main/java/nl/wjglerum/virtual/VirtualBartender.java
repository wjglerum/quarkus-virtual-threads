package nl.wjglerum.virtual;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VirtualBartender {

    public VirtualBeverage getFromDraft() {
        Log.info("Going to pour a virtual guinness");
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new VirtualBeverage("Virtual Guinness");
    }
}
