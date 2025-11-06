package nl.wjglerum._04_structured;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.concurrent.StructuredTaskScope;

@ApplicationScoped
@Path("/beverage/structured")
public class StructuredBeverageResource {

    @Inject
    StructuredBartender bartender;

    @Inject
    StructuredBeverageRepository repository;

    @GET
    @Path("/simple")
    @Transactional
    @RunOnVirtualThread
    @SuppressWarnings("preview")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StructuredBeverage> getBeveragesSimple() throws InterruptedException {
        try (var scope = StructuredTaskScope.open()) {
            var beer1 = scope.fork(() -> bartender.getFromDraft("alice"));
            var beer2 = scope.fork(() -> bartender.getFromDraft("bob"));
            var beer3 = scope.fork(() -> bartender.getFromDraft("chuck"));
            scope.join();
            var beers = List.of(beer1.get(), beer2.get(), beer3.get());
            repository.save(beers);
            return beers;
        }
    }

    @GET
    @Path("/custom")
    @Transactional
    @RunOnVirtualThread
    @SuppressWarnings("preview")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StructuredBeverage> getBeverages() throws InterruptedException {
        var joiner = StructuredTaskScope.Joiner.<StructuredBeverage>allSuccessfulOrThrow();
        var tf = Thread.ofVirtual().name(Thread.currentThread().getName() + "-beverage-", 0).factory();
        var friends = List.of("alice", "bob", "chuck");

        try (var scope = StructuredTaskScope.open(joiner, cf -> cf.withThreadFactory(tf))) {
            friends.forEach(name -> scope.fork(() -> bartender.getFromDraft(name)));
            var beers = scope.join().map(StructuredTaskScope.Subtask::get).toList();
            repository.save(beers);
            return beers;
        }
    }
}
