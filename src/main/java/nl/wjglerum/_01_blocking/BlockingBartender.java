package nl.wjglerum._01_blocking;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BlockingBartender {

    public BlockingBeverage getFromDraft() {
        Log.info("Going to pour a blocking guinness");
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new BlockingBeverage("Blocking Guinness");
    }
}
