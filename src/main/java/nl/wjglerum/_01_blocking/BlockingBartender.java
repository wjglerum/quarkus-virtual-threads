package nl.wjglerum._01_blocking;

import java.time.Duration;

import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.logging.Log;

@ApplicationScoped
public class BlockingBartender {

    @ConfigProperty(name = "bartender.delay")
    Duration delay;

    public BlockingBeverage get() {
        Log.info("Warming up the blocking coffee machine");
        try {
            Thread.sleep(delay.toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new BlockingBeverage("Blocking coffee");
    }
}
