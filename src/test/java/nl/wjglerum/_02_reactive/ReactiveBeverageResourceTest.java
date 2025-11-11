package nl.wjglerum._02_reactive;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(ReactiveBeverageResource.class)
class ReactiveBeverageResourceTest {

    @Test
    void testReactiveSimpleEndpoint() {
        given()
                .when()
                .get("/simple")
                .then()
                .statusCode(200)
                .body(containsString("Reactive coffee"));
    }

    @Test
    void testReactiveMultipleEndpoint() {
        given()
                .when()
                .get("/multiple")
                .then()
                .statusCode(200)
                .body("size()", is(3))
                .body(containsString("Reactive coffee"));
    }
}
