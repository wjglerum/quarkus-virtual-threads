package nl.wjglerum.structured;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import nl.wjglerum.Beverage;

import java.util.List;
import java.util.concurrent.StructuredTaskScope;

@ApplicationScoped
@Path("/beverage/structured")
public class StructuredBeverageResource {

    @Inject
    StructuredBartender structuredBarTender;

    @GET
    @Path("/simple")
    @Produces(MediaType.APPLICATION_JSON)
    @RunOnVirtualThread
    @SuppressWarnings("preview")
    public List<Beverage> getBeveragesSimple() throws InterruptedException {
        try (var scope = StructuredTaskScope.open()) {
            var beer1 = scope.fork(() -> structuredBarTender.getFromDraft("alice"));
            var beer2 = scope.fork(() -> structuredBarTender.getFromDraft("bob"));
            var beer3 = scope.fork(() -> structuredBarTender.getFromDraft("chuck"));
            scope.join();
            return List.of(beer1.get(), beer2.get(), beer3.get());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RunOnVirtualThread
    @SuppressWarnings("preview")
    public List<Beverage> getBeverages() throws InterruptedException {
        var joiner = StructuredTaskScope.Joiner.<Beverage>allSuccessfulOrThrow();
        var tf = Thread.ofVirtual().name(Thread.currentThread().getName() + "-beverage-", 0).factory();
        var friends = List.of("alice", "bob", "chuck");

        try (var scope = StructuredTaskScope.open(joiner, cf -> cf.withThreadFactory(tf))) {
            friends.forEach(name -> scope.fork(() -> structuredBarTender.getFromDraft(name)));
            return scope.join().map(StructuredTaskScope.Subtask::get).toList();
        }
    }
}
