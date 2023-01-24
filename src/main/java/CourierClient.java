import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient {

    public ValidatableResponse getCourierResponse(Courier courier) {
        return given().header("Content-type", "application/json").and().body(courier).when().post("/api/v1/courier").then();
    }

    public ValidatableResponse getLoginCourierResponse(Login login) {
        return given().header("Content-type", "application/json").and().body(login).when().post("/api/v1/courier/login").then();
    }
}
