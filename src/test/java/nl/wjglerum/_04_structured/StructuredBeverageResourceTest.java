package nl.wjglerum._04_structured;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit5.virtual.ShouldNotPin;
import io.quarkus.test.junit5.virtual.VirtualThreadUnit;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@VirtualThreadUnit
@TestHTTPEndpoint(StructuredBeverageResource.class)
class StructuredBeverageResourceTest {

    @Test
    void testSimpleEndpoint() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/simple")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", equalTo(3))
                .body(containsString("Structured coffee"));
    }

    @Test
    @ShouldNotPin
    void testCustomEndpoint() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/custom")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", equalTo(3))
                .body(containsString("Structured coffee"));
    }
}
