package nl.wjglerum._02_reactive;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(ReactiveBeverageResource.class)
class ReactiveBeverageResourceTest {

    @Test
    void testReactiveEndpoint() {
        given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body(containsString("Reactive coffee"));
    }

    @Test
    void testReactiveSequentialEndpoint() {
        given()
                .when()
                .get("/sequential")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("size()", is(3))
                .body(containsString("Reactive coffee"));
    }

    @Test
    void testReactiveParallelEndpoint() {
        given()
                .when()
                .get("/parallel")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("size()", is(3))
                .body(containsString("Reactive coffee"));
    }
}
