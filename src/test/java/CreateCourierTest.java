import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest extends DeleteAndCreate {
    private int id;
    private CourierClient courierClient;
    @Before
    public void setUp(){
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        courierClient = new CourierClient();
        createAccount();
    }

    @Test
    @DisplayName("Check status code 409 and error message in request with duplicate login field")
    public void testErrorMessageForRequestWithDuplicateLogin(){
       // CourierClient courierClient = new CourierClient();
        ValidatableResponse duplicateLogin  = courierClient.getCourierResponse(
                new Courier(EXPECTED_LOGIN, EXPECTED_PASSWORD, EXPECTED_NAME));
        duplicateLogin
                .statusCode(409)
                .assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Check status code 400 and error message in request without login field")
    public void testErrorMessageForRequestWithoutLogin(){
       // CourierClient courierClient = new CourierClient();
        ValidatableResponse emptyLoginField  = courierClient.getCourierResponse(
                new Courier(null, EXPECTED_PASSWORD, EXPECTED_NAME));
        emptyLoginField
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Check status code 400 and error message in request without password field")
    public void testErrorMessageForRequestWithoutPassword(){
       // CourierClient courierClient = new CourierClient();
        ValidatableResponse emptyPasswordField  = courierClient.getCourierResponse(
                new Courier(EXPECTED_LOGIN, null, EXPECTED_NAME));
        emptyPasswordField
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @DisplayName("Check status code 201 and body in create random account")
   @Test
   public void testBodyInRandomAccountCreation(){
     //  CourierClient courierClient = new CourierClient();
       ValidatableResponse duplicateLogin  = courierClient.getCourierResponse(
               Courier.getRandomCourierLogin());
       duplicateLogin
               .statusCode(201)
               .assertThat()
               .body("ok", equalTo(true));
   }
    @After
    public void tearDown(){
        deleteAccount(id);
    }
}
