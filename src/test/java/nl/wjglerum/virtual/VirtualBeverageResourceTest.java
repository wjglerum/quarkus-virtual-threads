package nl.wjglerum.virtual;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit5.virtual.ShouldNotPin;
import io.quarkus.test.junit5.virtual.VirtualThreadUnit;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
@VirtualThreadUnit
@TestHTTPEndpoint(VirtualBeverageResource.class)
class VirtualBeverageResourceTest {

    @Test
    @ShouldNotPin
    void testBeerEndpoint() {
        given()
                .when().get()
                .then()
                .statusCode(200)
                .body(containsString("Virtual Guinness"));
    }
}
