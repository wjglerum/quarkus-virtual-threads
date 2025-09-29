package nl.wjglerum.blocking;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import nl.wjglerum.Beverage;

@Path("/beverage/blocking")
@ApplicationScoped
public class BlockingBeverageResource {

    @Inject
    BlockingBartender blockingBarTender;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Beverage getBeverage() {
        return blockingBarTender.getFromDraft();
    }
}
