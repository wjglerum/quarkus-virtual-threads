package nl.wjglerum;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/beer/reactive")
@ApplicationScoped
public class ReactiveBeerResource {

    @Inject
    ReactiveBeerService reactiveBeerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Beer> getBeer() {
        return reactiveBeerService.getFromDraft();
    }
}
