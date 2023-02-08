import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class LoginCourierTest extends DeleteAndCreate {
    private CourierClient courierClient;
    private int id;
    private ResponseBody body;
    @Before
    public void setUp(){
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        courierClient = new CourierClient();
        createAccount();
    }

    @DisplayName("Check status code 200 and id value in successful login ")
    @Test
    public void testRequestSuccessfulLogin(){
        ValidatableResponse dataCourier = courierClient.getLoginCourierResponse(
                new Login(EXPECTED_LOGIN, EXPECTED_PASSWORD));
        int s = dataCourier
                .extract().statusCode();
        assertEquals(200, s);
        Response response = dataCourier.extract().response();
        body = response.body();
    }

    @DisplayName("Check status code 400 and error message in request without login field")
    @Test
    public void testRequestWithoutLogin(){
        ValidatableResponse dataCourierWithoutLogin =  courierClient.getLoginCourierResponse(
                new Login(null, EXPECTED_PASSWORD));
        dataCourierWithoutLogin
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @DisplayName("Check status code 400 and error message in request without password field")
    @Test
    public void testRequestWithoutPassword(){
        ValidatableResponse dataCourierWithoutLogin =  courierClient.getLoginCourierResponse(
                new Login(EXPECTED_LOGIN, ""));
        dataCourierWithoutLogin
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @DisplayName("Check status code 404 and error message in non-existent data")
    @Test
    public void testRequestNonExistentData(){
        ValidatableResponse nonExistentData  =  courierClient.getLoginCourierResponse(
                Login.getRandomLoginData());
        nonExistentData
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public void tearDown(){
        if (body != null) {
            try{
                id = body.path("id");
                deleteAccount(id);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}