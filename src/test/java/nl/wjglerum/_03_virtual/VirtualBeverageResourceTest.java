package nl.wjglerum._03_virtual;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
@TestHTTPEndpoint(VirtualBeverageResource.class)
class VirtualBeverageResourceTest {

    @Test
    void testVirtualEndpoint() {
        given()
                .when().get()
                .then()
                .statusCode(200)
                .body(containsString("Virtual Guinness"));
    }
}
