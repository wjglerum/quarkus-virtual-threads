package nl.wjglerum._03_virtual;

import io.quarkus.logging.Log;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

@Path("/beverage/virtual")
@Transactional
@RunOnVirtualThread
public class VirtualBeverageResource {

    @Inject
    VirtualBartender bartender;

    @Inject
    VirtualBeverageRepository repository;

    @GET
    public VirtualBeverage getBeverage() {
        Log.info("Going to get virtual beverage");
        var beverage =  bartender.get();
        repository.save(beverage);
        return beverage;
    }

    @GET
    @Path("/sequential")
    public List<VirtualBeverage> getBeveragesSequential() {
        Log.info("Going to get virtual beverages sequential");
        var beverage1 =  bartender.get();
        var beverage2 =  bartender.get();
        var beverage3 =  bartender.get();
        var beverages = List.of(beverage1, beverage2, beverage3);
        repository.save(beverages);
        return beverages;
    }

    @GET
    @Path("/parallel")
    public List<VirtualBeverage> getBeveragesParallel() {
        Log.info("Going to get virtual beverages parallel");
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
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

    @GET
    @Path("/custom")
    public List<VirtualBeverage> getBeveragesCustom() {
        Log.info("Going to get virtual beverages custom");
        var currentThread = Thread.currentThread();
        var threadFactory = Thread.ofVirtual()
                .name(currentThread.getName() + "-virtual-beverage-", 0)
                .factory();
        try(var executor = Executors.newThreadPerTaskExecutor(threadFactory)) {
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
