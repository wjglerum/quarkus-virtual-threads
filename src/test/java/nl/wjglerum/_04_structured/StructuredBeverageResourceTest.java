package nl.wjglerum._04_structured;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit5.virtual.ShouldNotPin;
import io.quarkus.test.junit5.virtual.VirtualThreadUnit;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@ShouldNotPin(atMost = 5)
@VirtualThreadUnit
@TestHTTPEndpoint(StructuredBeverageResource.class)
class StructuredBeverageResourceTest {

    @Test
    void testStructuredSimpleEndpoint() {
        given()
                .when()
                .get("/simple")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("size()", equalTo(3))
                .body(containsString("Structured coffee"));
    }

    @Test
    void testStructuredCustomEndpoint() {
        given()
                .when()
                .get("/custom")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("size()", equalTo(3))
                .body(containsString("Structured coffee"));
    }
}
