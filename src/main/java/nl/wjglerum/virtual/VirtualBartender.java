package nl.wjglerum.virtual;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import nl.wjglerum.Beer;

@ApplicationScoped
public class VirtualBartender {

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
