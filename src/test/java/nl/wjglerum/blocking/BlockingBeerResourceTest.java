package nl.wjglerum.blocking;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
@TestHTTPEndpoint(BlockingBeerResource.class)
class BlockingBeerResourceTest {

    @Test
    void testBeerEndpoint() {
        given()
                .when().get()
                .then()
                .statusCode(200)
                .body(containsString("Blocking Guinness"));
    }
}
