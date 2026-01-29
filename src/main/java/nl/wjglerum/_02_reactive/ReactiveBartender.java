package nl.wjglerum._02_reactive;

import java.time.Duration;

import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class ReactiveBartender {

    @ConfigProperty(name = "bartender.delay")
    Duration delay;

    public Uni<ReactiveBeverage> get() {
        Log.info("Warming up the reactive coffee machine");
        return Uni.createFrom().item(new ReactiveBeverage("Reactive coffee")).onItem().delayIt().by(delay);
    }
}
