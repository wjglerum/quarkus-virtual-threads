package nl.wjglerum;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(GreetingResource.class)
class GreetingResourceTest {

    @Test
    void testHelloEndpoint() {
        given()
                .when().get("/blocking")
                .then()
                .statusCode(200)
                .body(is("Hello from Quarkus REST"));
    }
}
