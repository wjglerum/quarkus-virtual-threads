package nl.wjglerum._04_structured;

import io.quarkus.logging.Log;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;
import java.util.concurrent.StructuredTaskScope;

@Path("/beverage/structured")
@Transactional
@RunOnVirtualThread
@SuppressWarnings("preview")
public class StructuredBeverageResource {

    @Inject
    StructuredBartender bartender;

    @Inject
    StructuredBeverageRepository repository;

    @GET
    @Path("/simple")
    public List<StructuredBeverage> getBeveragesSimple() throws InterruptedException {
        Log.info("Going to get structured beverages simple");
        try (var scope = StructuredTaskScope.open()) {
            var beverage1 = scope.fork(() -> bartender.get());
            var beverage2 = scope.fork(() -> bartender.get());
            var beverage3 = scope.fork(() -> bartender.get());
            scope.join();
            var beverages = List.of(beverage1.get(), beverage2.get(), beverage3.get());
            repository.save(beverages);
            return beverages;
        }
    }

    @GET
    @Path("/custom")
    public List<StructuredBeverage> getBeveragesCustom() throws InterruptedException {
        Log.info("Going to get structured beverages custom");
        var joiner = StructuredTaskScope.Joiner.<StructuredBeverage>allSuccessfulOrThrow();
        var currentThread = Thread.currentThread().getName();
        var threadFactory = Thread.ofVirtual().name(currentThread + "-structured-beverage-", 0).factory();
        try (var scope = StructuredTaskScope.open(joiner, cf -> cf.withThreadFactory(threadFactory))) {
            scope.fork(bartender::get);
            scope.fork(bartender::get);
            scope.fork(bartender::get);
            var beverages = scope.join();
            repository.save(beverages);
            return beverages;
        }
    }
}
