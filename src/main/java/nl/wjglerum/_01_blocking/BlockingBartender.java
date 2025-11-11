package nl.wjglerum._01_blocking;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BlockingBartender {

    public BlockingBeverage get() {
        Log.info("Warming up the blocking coffee machine");
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new BlockingBeverage("Blocking coffee");
    }
}
