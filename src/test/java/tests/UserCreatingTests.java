package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class UserCreatingTests {


    @Test
    void successfulUserCreatingTest() {
        String authData = "{\"name\": \"Ramzil\", \"job\": \"The Lord\"}";

        given()
                .header("x-api-key", "reqres-free-v1")
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Ramzil"));
    }

    @Test
    void userCreatingWithoutBodyTest() {
        String authData = "{}";

        given()
                .header("x-api-key", "reqres-free-v1")
                .body(authData)
                .contentType(JSON)
                .log().uri()

            .when()
                .post("https://reqres.in/api/users")

            .then()
                .log().status()
                .log().body()
                .statusCode(201);
    }
}
