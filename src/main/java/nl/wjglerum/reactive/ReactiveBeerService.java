package nl.wjglerum.reactive;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import nl.wjglerum.Beer;

import java.time.Duration;

@ApplicationScoped
public class ReactiveBeerService {

    public Uni<Beer> getFromDraft() {
        Log.info("Going to pour a reactive guinness");
        return Uni.createFrom().item(new Beer("Reactive Guinness"))
                .onItem().delayIt().by(Duration.ofSeconds(5));
    }
}
