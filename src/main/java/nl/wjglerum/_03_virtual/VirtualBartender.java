package nl.wjglerum._03_virtual;

import java.time.Duration;

import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.logging.Log;

@ApplicationScoped
public class VirtualBartender {

    @ConfigProperty(name = "bartender.delay")
    Duration delay;

    public VirtualBeverage get() {
        Log.info("Warming up the virtual coffee machine");
        try {
            Thread.sleep(delay.toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new VirtualBeverage("Virtual coffee");
    }
}
