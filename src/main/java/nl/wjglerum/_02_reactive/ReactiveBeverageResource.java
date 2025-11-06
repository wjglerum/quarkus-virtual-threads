package nl.wjglerum._02_reactive;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/beverage/reactive")
@ApplicationScoped
public class ReactiveBeverageResource {

    @Inject
    ReactiveBartender bartender;

    @Inject
    ReactiveBeverageRepository repository;

    @GET
    @WithTransaction
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ReactiveBeverage> getBeverage() {
        return bartender.getFromDraft().onItem().call(beverage -> repository.save(beverage));
    }
}
