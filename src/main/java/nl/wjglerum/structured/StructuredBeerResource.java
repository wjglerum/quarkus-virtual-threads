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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

@ApplicationScoped
@Path("/beer/structured")
public class StructuredBeerResource {

    @Inject
    StructuredBeerService structuredBeerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RunOnVirtualThread
    @SuppressWarnings("preview")
    public List<Beer> getBeers() throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var beer1 = scope.fork(() -> structuredBeerService.getFromDraft());
            var beer2 = scope.fork(() -> structuredBeerService.getFromDraft());
            var beer3 = scope.fork(() -> structuredBeerService.getFromDraft());

            scope.join().throwIfFailed();

            return List.of(beer1.get(), beer2.get(), beer3.get());
        }
    }
}
