import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import model.*;
import org.junit.Before;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.junit4.DisplayName;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostApiTest {

    private final PostSteps steps = new PostSteps();
    Faker faker = new Faker();

    String courierLogin = faker.name().username();
    String courierPassword = faker.internet().password();
    String courierFirstName = faker.name().fullName();
    String firstName = faker.name().fullName();
    String lastName = faker.name().lastName();
    String address = faker.address().streetAddress();
    String phone = faker.phoneNumber().phoneNumber();
    int metroStationId = 7;
    int rentTime = 5;
    String deliveryDate = "2022-07-07";
    String comment = "some comment for courier";
    List<String> colors = Arrays.asList("BLACK", "GREY");

    final String createCourierSameLoginMessage = "Этот логин уже используется. Попробуйте другой.";
    final String createCourierWithoutParameterMessage = "Недостаточно данных для создания учетной записи";
    final String loginCourierWithoutParameterMessage = "Недостаточно данных для входа";
    final String loginNonExistCourierMessage = "Учетная запись не найдена";

    @Before
    public void setUp() {

    }

    @Test
    @DisplayName("Check status code of api/v1/courier")
    @Description("Basic test for api/v1/courier endpoint")
    public void testCourierCreation() throws JsonProcessingException {
        Courier courier = new Courier(courierLogin, courierPassword, courierFirstName);

        steps.createCourier(courier)
                .then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);;

        steps.deleteCourierById(steps.getCourierId(courier));
    }

    @Test
    @DisplayName("Check courier with existing login creation api/v1/courier")
    public void testSameLoginCourierCreation() throws JsonProcessingException {
        Courier courier = new Courier(courierLogin, courierPassword, courierFirstName);
        steps.createCourier(courier);

        steps.createCourier(courier)
                .then().assertThat().body("message", equalTo(createCourierSameLoginMessage))
                .and()
                .statusCode(409);

        steps.deleteCourierById(steps.getCourierId(courier));
    }

    @Test
    @DisplayName("Check courier without login creation api/v1/courier")
    public void testCourierWithoutLoginCreation() throws JsonProcessingException {
        Courier courier = new Courier();
        courier.setPassword(courierPassword);
        courier.setName(courierFirstName);

        steps.createCourier(courier)
                .then().assertThat().body("message", equalTo(createCourierWithoutParameterMessage))
                .and()
                .statusCode(400);
    }

    @Test
    @DisplayName("Check courier without password creation api/v1/courier")
    public void testCourierWithoutPasswordCreation() throws JsonProcessingException {
        Courier courier = new Courier();
        courier.setLogin(courierLogin);
        courier.setName(courierFirstName);

        steps.createCourier(courier)
                .then().assertThat().body("message", equalTo(createCourierWithoutParameterMessage))
                .and()
                .statusCode(400);
    }

    @Test
    @DisplayName("Check courier login api/v1/courier/login")
    public void testCourierLogin() throws JsonProcessingException {
        Courier courier = new Courier(courierLogin, courierPassword, courierFirstName);
        steps.createCourier(courier);

        steps.loginCourier(courier)
                .then().assertThat().body("id", notNullValue())
                .and()
                .statusCode(200);

        steps.deleteCourierById(steps.getCourierId(courier));
    }

    @Test
    @DisplayName("Check login courier without login api/v1/courier/login")
    public void testCourierLoginWithoutLogin() throws JsonProcessingException {
        Courier courier = new Courier(courierLogin, courierPassword, null);
        steps.createCourier(courier);
        courier.setLogin(null);

        steps.loginCourier(courier)
                .then().assertThat().body("message", equalTo(loginCourierWithoutParameterMessage))
                .and()
                .statusCode(400);

        steps.deleteCourierById(steps.getCourierId(courier));
    }

    @Test
    @DisplayName("Check login courier without password api/v1/courier/login") // return time out error, test failed
    public void testCourierLoginWithoutPassword() throws JsonProcessingException {
        Courier courier = new Courier(courierLogin, courierPassword, null);
        steps.createCourier(courier);
        courier.setPassword(null);

        steps.loginCourier(courier)
                .then().assertThat().body("message", equalTo(loginCourierWithoutParameterMessage))
                .and()
                .statusCode(400);

        steps.deleteCourierById(steps.getCourierId(courier));
    }

    @Test
    @DisplayName("Check courier login with non existing login api/v1/courier/login")
    public void testNonExistCourierLogin() throws JsonProcessingException {
        Courier courier = new Courier(courierLogin, courierPassword, courierFirstName);

        steps.loginCourier(courier)
                .then().assertThat().body("message", equalTo(loginNonExistCourierMessage))
                .and()
                .statusCode(404);
    }

    @Test
    @DisplayName("Check order created api/v1/orders")
    public void testCreateOrder() {
        Order order = new Order(firstName, lastName, address, metroStationId, phone, rentTime, deliveryDate, comment, colors);

        steps.createOrder(order)
                .then().assertThat().body("track", notNullValue())
                .and().statusCode(201);
    }

}
