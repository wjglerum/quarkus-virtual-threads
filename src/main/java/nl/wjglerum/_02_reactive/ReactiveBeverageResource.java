package nl.wjglerum._02_reactive;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/beverage/reactive")
@ApplicationScoped
public class ReactiveBeverageResource {

    @Inject
    ReactiveBartender bartender;

    @Inject
    ReactiveBeverageRepository repository;

    @GET
    @Path("/simple")
    @WithTransaction
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ReactiveBeverage> getBeverage() {
        return bartender.getFromDraft().onItem().call(beverage -> repository.save(beverage));
    }

    @GET
    @Path("/multiple")
    @WithTransaction
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<ReactiveBeverage>> getBeverages() {
        var beverage1 = bartender.getFromDraft();
        var beverage2 = bartender.getFromDraft();
        var beverage3 = bartender.getFromDraft();
        return Uni.join().all(beverage1, beverage2, beverage3).andCollectFailures()
                .onItem().call(beverages -> repository.save(beverages));
    }
}
