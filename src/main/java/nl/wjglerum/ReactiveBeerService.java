package nl.wjglerum;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@ApplicationScoped
public class ReactiveBeerService {

    public Uni<Beer> getFromDraft() {
        return Uni.createFrom().item(new Beer("Reactive Guinness"))
                .onItem().delayIt().by(Duration.ofSeconds(5));
    }
}
