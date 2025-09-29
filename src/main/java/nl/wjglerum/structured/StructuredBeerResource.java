package nl.wjglerum.structured;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import nl.wjglerum.Beer;

import java.util.List;
import java.util.concurrent.StructuredTaskScope;
import java.util.stream.Stream;

@ApplicationScoped
@Path("/beer/structured")
public class StructuredBeerResource {

    @Inject
    StructuredBartender structuredBarTender;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RunOnVirtualThread
    @SuppressWarnings("preview")
    public List<Beer> getMultipleBeers() throws InterruptedException {
        var joiner = StructuredTaskScope.Joiner.<Beer>allSuccessfulOrThrow();
        var tf = Thread.ofVirtual().name(Thread.currentThread().getName() + "-beers-", 0).factory();
        try (var scope = StructuredTaskScope.open(joiner, cf -> cf.withThreadFactory(tf))) {
            Stream.of("alice", "bob", "chuck").forEach(name -> scope.fork(() -> structuredBarTender.getFromDraft(name)));
            return scope.join().map(StructuredTaskScope.Subtask::get).toList();
        }
    }
}
