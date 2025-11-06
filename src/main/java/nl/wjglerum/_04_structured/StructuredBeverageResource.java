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
            var alice = scope.fork(() -> bartender.getFromDraft("Alice"));
            var bob = scope.fork(() -> bartender.getFromDraft("Bob"));
            var chuck = scope.fork(() -> bartender.getFromDraft("Chuck"));
            scope.join();
            var beverages = List.of(alice.get(), bob.get(), chuck.get());
            repository.save(beverages);
            return beverages;
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
        var friends = List.of("Alice", "Bob", "Chuck");

        try (var scope = StructuredTaskScope.open(joiner, cf -> cf.withThreadFactory(tf))) {
            friends.forEach(name -> scope.fork(() -> bartender.getFromDraft(name)));
            var beverages = scope.join().map(StructuredTaskScope.Subtask::get).toList();
            repository.save(beverages);
            return beverages;
        }
    }
}
