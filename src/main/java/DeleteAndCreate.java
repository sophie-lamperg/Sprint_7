import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class DeleteAndCreate extends Constants {
    public void deleteAccount(int id) {
        given().when().delete("/api/v1/courier/login" + id);
    }
    public void createAccount(){
        Courier courier = new Courier(expectedLogin, expectedPassword, expectedName);
        given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }
}