package nl.wjglerum._03_virtual;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@ApplicationScoped
@Path("/beverage/virtual")
public class VirtualBeverageResource {

    @Inject
    VirtualBartender bartender;

    @Inject
    VirtualBeverageRepository repository;

    @GET
    @Path("/simple")
    @Transactional
    @RunOnVirtualThread
    @Produces(MediaType.APPLICATION_JSON)
    public VirtualBeverage getBeverage() {
        var beverage =  bartender.get();
        repository.save(beverage);
        return beverage;
    }

    @GET
    @Path("/multiple")
    @Transactional
    @RunOnVirtualThread
    @Produces(MediaType.APPLICATION_JSON)
    public List<VirtualBeverage> getBeverages() {
        var beverage1 =  bartender.get();
        var beverage2 =  bartender.get();
        var beverage3 =  bartender.get();
        var beverages = List.of(beverage1, beverage2, beverage3);
        repository.save(beverages);
        return beverages;
    }
}
