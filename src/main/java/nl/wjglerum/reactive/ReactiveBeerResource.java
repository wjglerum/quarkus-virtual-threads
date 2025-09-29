package nl.wjglerum.reactive;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import nl.wjglerum.Beer;

@Path("/beer/reactive")
@ApplicationScoped
public class ReactiveBeerResource {

    @Inject
    ReactiveBartender reactiveBarTender;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Beer> getBeer() {
        return reactiveBarTender.getFromDraft();
    }
}
