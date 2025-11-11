package nl.wjglerum._03_virtual;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VirtualBartender {

    public VirtualBeverage get() {
        Log.info("Warming up the virtual coffee machine");
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new VirtualBeverage("Virtual coffee");
    }
}
