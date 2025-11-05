package nl.wjglerum.virtual;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/beverage/virtual")
public class VirtualBeverageResource {

    @Inject
    VirtualBartender bartender;

    @Inject
    VirtualBeverageRepository repository;

    @GET
    @Transactional
    @RunOnVirtualThread
    @Produces(MediaType.APPLICATION_JSON)
    public VirtualBeverage getBeverage() {
        var beverage =  bartender.getFromDraft();
        repository.save(beverage);
        return beverage;
    }
}
