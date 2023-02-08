import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

public class DeleteAndCreate extends Constants {
    public void deleteAccount(int id) {
        RestAssured.given()
                .header("Content-Type", "application/json")
                .delete("/api/v1/courier/" + id);
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
