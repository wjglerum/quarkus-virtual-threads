package nl.wjglerum._03_virtual;

import io.quarkus.logging.Log;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

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
    public VirtualBeverage getBeverage() {
        var beverage =  bartender.get();
        repository.save(beverage);
        return beverage;
    }

    @GET
    @Path("/sequential")
    @Transactional
    @RunOnVirtualThread
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
    @Transactional
    @RunOnVirtualThread
    public List<VirtualBeverage> getBeveragesParallel() {
        Log.info("Going to get virtual beverages parallel");
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
