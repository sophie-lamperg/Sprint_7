import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class DeleteAndCreate extends Constants {
    public void deleteAccount(int id) {
        given().when().delete("/api/v1/courier/login" + id);
    }
    public ValidatableResponse createAccount(){
        Courier courier = new Courier(EXPECTED_LOGIN, EXPECTED_PASSWORD, EXPECTED_NAME);
       return  given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier").then();
    }
}