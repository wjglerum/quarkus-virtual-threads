package nl.wjglerum.blocking;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import nl.wjglerum.Beer;

@Path("/beer/blocking")
@ApplicationScoped
public class BlockingBeerResource {

    @Inject
    BlockingBeerService blockingBeerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Beer getBeer() {
        return blockingBeerService.getFromDraft();
    }
}
