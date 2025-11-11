package nl.wjglerum._03_virtual;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(VirtualBeverageResource.class)
class VirtualBeverageResourceTest {

    @Test
    void testVirtualSimpleEndpoint() {
        given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(containsString("Virtual coffee"));
    }

    @Test
    void testVirtualSequenetialEndpoint() {
        given()
                .when()
                .get("/sequential")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("size()", is(3))
                .body(containsString("Virtual coffee"));
    }

    @Test
    void testVirtualParallelEndpoint() {
        given()
                .when()
                .get("/parallel")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("size()", is(3))
                .body(containsString("Virtual coffee"));
    }
}
