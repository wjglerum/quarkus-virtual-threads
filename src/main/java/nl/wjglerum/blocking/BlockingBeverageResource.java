package nl.wjglerum.blocking;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/beverage/blocking")
@ApplicationScoped
public class BlockingBeverageResource {

    @Inject
    BlockingBartender bartender;

    @Inject
    BlockingBeverageRepository repository;

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public BlockingBeverage getBeverage() {
        var beverage = bartender.getFromDraft();
        repository.save(beverage);
        return beverage;
    }
}
