import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest extends Constants {
    private final String firstNameValue;
    private final String lastNameValue;
    private final String addressValue;
    private final int metroStationValue;
    private final String phoneValue;
    private final int rentTimeValue;
    private final String deliveryDateValue;
    private final String commentValue;
    private final List<String> colorValue;

    public CreateOrderTest(String firstNameValue, String lastNameValue, String addressValue, int metroStationValue, String phoneValue, int rentTimeValue, String deliveryDateValue, String commentValue, List<String> colorValue) {
        this.firstNameValue = firstNameValue;
        this.lastNameValue = lastNameValue;
        this.addressValue = addressValue;
        this.metroStationValue = metroStationValue;
        this.phoneValue = phoneValue;
        this.rentTimeValue = rentTimeValue;
        this.deliveryDateValue = deliveryDateValue;
        this.commentValue = commentValue;
        this.colorValue = colorValue;
    }

    @Parameterized.Parameters
    public static Object[][] getTestDataCreateOrder() {
        return new Object[][]{
                {"Sophia", "St", "Pushkina, 174 apt.", 2, "+7 920 333 33 33", 3, "2023-01-23", "Go home", null},
                {"Sophia", "St", "Pushkina, 174 apt.", 2, "+7 920 333 33 33", 3, "2023-01-23", "Go home", List.of("BLACK")},
                {"Sophia", "St", "Pushkina, 174 apt.", 2, "+7 920 333 33 33", 3, "2023-01-23", "Go home", List.of("GREY")},
                {"Sophia", "St", "Pushkina, 174 apt.", 2, "+7 920 333 33 33", 3, "2023-01-23", "Go home", List.of("BLACK", "GREY")},
        };
    }

    @Before
    public void setUp(){
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @DisplayName("Check status code 201 and track in create order with parameterized test")
    @Test
    public void testTrackFieldInOrder(){
        OrderClient order = new OrderClient();
        ValidatableResponse emptyPasswordField  = order.getOrdersResponse(new Order(
                firstNameValue, lastNameValue, addressValue,
                        metroStationValue, phoneValue, rentTimeValue, deliveryDateValue, commentValue, colorValue));
        emptyPasswordField
                .statusCode(201);
        MatcherAssert.assertThat("track", notNullValue());
    }
}