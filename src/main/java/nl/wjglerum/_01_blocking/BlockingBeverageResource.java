package nl.wjglerum._01_blocking;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Path("/beverage/blocking")
@Transactional
public class BlockingBeverageResource {

    @Inject
    BlockingBartender bartender;

    @Inject
    BlockingBeverageRepository repository;

    @GET
    public BlockingBeverage getBeverage() {
        Log.info("Going to get blocking beverage");
        var beverage = bartender.get();
        repository.save(beverage);
        return beverage;
    }

    @GET
    @Path("/sequential")
    public List<BlockingBeverage> getBeveragesSequential() {
        Log.info("Going to get blocking beverages sequential");
        var beverage1 = bartender.get();
        var beverage2 = bartender.get();
        var beverage3 = bartender.get();
        var beverages = List.of(beverage1, beverage2, beverage3);
        repository.save(beverages);
        return beverages;
    }

    @GET
    @Path("/parallel")
    public List<BlockingBeverage> getBeveragesParallel() {
        Log.info("Going to get blocking beverages parallel");
        try (ExecutorService executor = Executors.newFixedThreadPool(3)) {
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
