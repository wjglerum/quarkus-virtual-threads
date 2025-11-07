package nl.wjglerum._01_blocking;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/beverage/blocking")
@ApplicationScoped
public class BlockingBeverageResource {

    @Inject
    BlockingBartender bartender;

    @Inject
    BlockingBeverageRepository repository;

    @GET
    @Path("/simple")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public BlockingBeverage getBeverage() {
        var beverage = bartender.getFromDraft();
        repository.save(beverage);
        return beverage;
    }

    @GET
    @Path("/multiple")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<BlockingBeverage> getBeverages() {
        var beverage1 = bartender.getFromDraft();
        var beverage2 = bartender.getFromDraft();
        var beverage3 = bartender.getFromDraft();
        List<BlockingBeverage> beverages = List.of(beverage1, beverage2, beverage3);
        repository.save(beverages);
        return beverages;
    }
}
