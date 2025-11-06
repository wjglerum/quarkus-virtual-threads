package nl.wjglerum._02_reactive;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReactiveBartender {

    public Uni<ReactiveBeverage> getFromDraft() {
        Log.info("Going to pour a reactive Guinness");
        return Uni.createFrom().item(new ReactiveBeverage("Reactive Guinness"));
    }
}
