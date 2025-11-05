package nl.wjglerum.reactive;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@ApplicationScoped
public class ReactiveBartender {

    public Uni<ReactiveBeverage> getFromDraft() {
        Log.info("Going to pour a reactive guinness");
        return Uni.createFrom()
                .item(new ReactiveBeverage("Reactive Guinness"))
                .onItem()
                .delayIt()
                .by(Duration.ofSeconds(5));
    }
}
