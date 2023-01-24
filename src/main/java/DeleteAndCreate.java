import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class DeleteAndCreate {
    public void deleteAccount() {
        Login login = new Login("alla", "1222");
        Response response = given().header("Content-type", "application/json").and().body(login).when().post("/api/v1/courier/login");
        String id = response.jsonPath().getString("id");
        given().when().delete("/api/v1/courier/login" + id);
    }
    public void createAccount(){
        Courier courier = new Courier("alla", "1222", "alla");
        given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }
}
