package services.progressit;

import io.quarkus.test.junit.QuarkusTest;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.BAD_REQUEST;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.OK;

@QuarkusTest
class SomeControllerTest {

    @ParameterizedTest
    @ValueSource(strings = {"foo", "12345678901234567890"})
    void areYourReady_validActivity_returns200(final String givenActivity) {
        given()
                .contentType(APPLICATION_JSON)
                .body("{\"activity\": \"" + givenActivity + "\"}")

                .when()
                .post("/are-you-ready")

                .then()
                .statusCode(OK)
                .body(is("Let's get started with " + givenActivity + "!"));
    }

    @ParameterizedTest
    @MethodSource("invalidRequestBodies")
    void areYouReady_invalidRequestBody_returns400(final String givenInvalidBody) {
        given()
                .contentType(APPLICATION_JSON)
                .body(givenInvalidBody)

                .when()
                .post("/are-you-ready")

                .then()
                .statusCode(BAD_REQUEST);
    }

    static Stream<String> invalidRequestBodies() {
        return Stream.of(
                "",
                "{}",
                "{\"activity\": null}",
                "{\"activity\": \"\"}",
                "{\"activity\": \"12\"}",
                "{\"activity\": \"X12345678901234567890\"}"
        );
    }
}