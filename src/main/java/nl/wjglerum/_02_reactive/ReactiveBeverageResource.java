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
    @WithTransaction
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ReactiveBeverage> getBeverage() {
        return bartender.get().onItem().call(beverage -> repository.save(beverage));
    }

    @GET
    @Path("/sequential")
    @WithTransaction
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<ReactiveBeverage>> getBeverageSequential() {
        return bartender.get().onItem().transformToUni(beverage1 ->
                bartender.get().onItem().transformToUni(beverage2 ->
                        bartender.get().onItem().transformToUni(beverage3 -> {
                                    var beverages = List.of(beverage1, beverage2, beverage3);
                                    return repository.save(beverages).replaceWith(beverages);
                                }
                        )
                )
        );
    }

    @GET
    @Path("/parallel")
    @WithTransaction
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<ReactiveBeverage>> getBeveragesParallel() {
        var beverage1 = bartender.get();
        var beverage2 = bartender.get();
        var beverage3 = bartender.get();
        return Uni.join().all(beverage1, beverage2, beverage3).andCollectFailures()
                .onItem().call(beverages -> repository.save(beverages));
    }
}
