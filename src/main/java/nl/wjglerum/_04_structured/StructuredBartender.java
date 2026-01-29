package nl.wjglerum._04_structured;

import java.time.Duration;

import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.logging.Log;

@ApplicationScoped
public class StructuredBartender {

    @ConfigProperty(name = "bartender.delay")
    Duration delay;

    public StructuredBeverage get() {
        Log.info("Warming up the structured coffee machine");
        try {
            Thread.sleep(delay.toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new StructuredBeverage("Structured coffee");
    }
}
