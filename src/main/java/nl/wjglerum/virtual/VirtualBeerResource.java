package nl.wjglerum.virtual;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import nl.wjglerum.Beer;

@ApplicationScoped
@Path("/beer/virtual")
public class VirtualBeerResource {

    @Inject
    VirtualBartender virtualBarTender;

    @GET
    @RunOnVirtualThread
    @Produces(MediaType.APPLICATION_JSON)
    public Beer getBeer() {
        return virtualBarTender.getFromDraft();
    }
}
