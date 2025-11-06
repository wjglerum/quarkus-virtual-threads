package nl.wjglerum._02_reactive;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.smallrye.mutiny.vertx.core.ContextAwareScheduler;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@ApplicationScoped
public class ReactiveBartender {

    public Uni<ReactiveBeverage> getFromDraft() {
        Log.info("Going to pour a reactive Guinness");
        return Uni.createFrom()
                .item(new ReactiveBeverage("Reactive Guinness"))
                .onItem().delayIt()
                .onExecutor(ContextAwareScheduler.delegatingTo(Infrastructure.getDefaultWorkerPool()).withCurrentContext())
                .by(Duration.ofSeconds(3));
    }
}
