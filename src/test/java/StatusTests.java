import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class StatusTests {
    /*
    1. Make request to https://selenoid.autotests.cloud/status
    2. Get response
    3. Check total is 5
     */

    @Test
    void checkTotal5() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(5));
    }

    @Test
    void checkTotalWithResponceLogs() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total", is(5));
    }

    @Test
    void checkTotalWithLogs() {
        given()
                .log().all()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total", is(5));
    }
}
