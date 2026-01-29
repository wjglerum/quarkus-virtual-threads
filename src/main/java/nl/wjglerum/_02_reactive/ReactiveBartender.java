package nl.wjglerum._02_reactive;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@ApplicationScoped
public class ReactiveBartender {

    public Uni<ReactiveBeverage> get() {
        Log.info("Warming up the reactive coffee machine");
        return Uni.createFrom()
                .item(new ReactiveBeverage("Reactive coffee"))
                .onItem().delayIt().by(Duration.ofSeconds(3));
    }
}
