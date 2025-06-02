import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class LoginTests {
/*
    https://reqres.in/api/register
    {
        "email":"eve.holt@reqres.in",
            "password":"pistol"
    }

    Response
200
    {
        "id": 4,
            "token": "QpwL5tke4Pnpja7X4"
    }
*/

    @Test
    void successfulLoginTest() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        given()
                .header("x-api-key", "reqres-free-v1")
                .body(authData)
                .contentType(JSON)
                .log().uri()

            .when()
                .post("https://reqres.in/api/login")

            .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void unsuccessfulLogin415Test() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }
}
