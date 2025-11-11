package nl.wjglerum._01_blocking;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(BlockingBeverageResource.class)
class BlockingBeverageResourceTest {

    @Test
    void testBlockingSimpleEndpoint() {
        given()
                .when()
                .get("/simple")
                .then()
                .statusCode(200)
                .body(containsString("Blocking coffee"));
    }

    @Test
    void testBlockingMultipleEndpoint() {
        given()
                .when()
                .get("/multiple")
                .then()
                .statusCode(200)
                .body("size()", is(3))
                .body(containsString("Blocking coffee"));
    }
}
