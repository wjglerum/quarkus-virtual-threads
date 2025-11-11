package nl.wjglerum._01_blocking;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        var beverage = bartender.get();
        repository.save(beverage);
        return beverage;
    }

    @GET
    @Path("/sequential")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<BlockingBeverage> getSequentialBeverages() {
        var beverage1 = bartender.get();
        var beverage2 = bartender.get();
        var beverage3 = bartender.get();
        var beverages = List.of(beverage1, beverage2, beverage3);
        repository.save(beverages);
        return beverages;
    }

    @GET
    @Path("/parallel")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<BlockingBeverage> getParallelBeverages() {
        try (ExecutorService executor = Executors.newWorkStealingPool()) {
            var beverage1 = executor.submit(bartender::get);
            var beverage2 = executor.submit(bartender::get);
            var beverage3 = executor.submit(bartender::get);
            var beverages = List.of(beverage1.get(), beverage2.get(), beverage3.get());
            repository.save(beverages);
            return beverages;
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
