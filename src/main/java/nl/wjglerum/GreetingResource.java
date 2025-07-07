package nl.wjglerum;

import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.common.annotation.RunOnVirtualThread;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Path("/blocking")
    @Produces(MediaType.TEXT_PLAIN)
    public String blocking() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/non-blocking")
    @NonBlocking
    @Produces(MediaType.TEXT_PLAIN)
    public String nonBlocking() {
        return "Hello from Quarkus REST non blocking";
    }

    @GET
    @Path("/reactive")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> reactive() {
        return Uni.createFrom().item("Hello from Quarkus REST reactive");
    }

    @GET
    @Path("/virtual-thread")
    @Produces(MediaType.TEXT_PLAIN)
    @RunOnVirtualThread
    public String virtualThread() {
        return "Hello from Quarkus REST on a virtual thread";
    }
}
