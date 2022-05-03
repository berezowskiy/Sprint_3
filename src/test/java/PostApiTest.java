import model.*;

import org.junit.Before;
import org.junit.Test;
// импортируем библиотеку генерации строк
import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.junit4.DisplayName;
import java.util.Arrays;
import java.util.List;

public class PostApiTest {

    private final PostSteps steps = new PostSteps();
    // с помощью библиотеки RandomStringUtils генерируем логин
    // метод randomAlphabetic генерирует строку, состоящую только из букв, в качестве параметра передаём длину строки
    String courierLogin = RandomStringUtils.randomAlphabetic(10);
    // с помощью библиотеки RandomStringUtils генерируем пароль
    String courierPassword = RandomStringUtils.randomAlphabetic(10);
    // с помощью библиотеки RandomStringUtils генерируем имя курьера
    String courierFirstName = RandomStringUtils.randomAlphabetic(10);

    List<String> colors = Arrays.asList("BLACK", "GREY");

    @Before
    public void setUp() {

    }

    @Test
    @DisplayName("Check courier creation api/v1/courier")
    public void testCourierCreation() throws JsonProcessingException {
        Courier courier = new Courier(courierLogin, courierPassword, courierFirstName);
        steps.checkCourierCreate(courier);
        steps.deleteCourierById(steps.getCourierId(courierLogin, courierPassword));
    }

    @Test
    @DisplayName("Check courier with existing login creation api/v1/courier")
    public void testSameLoginCourierCreation() throws JsonProcessingException {
        Courier courier = new Courier(courierLogin, courierPassword, courierFirstName);
        steps.createCourier(courier);
        steps.checkCreateCourierWithSameLogin(courier);
        steps.deleteCourierById(steps.getCourierId(courierLogin, courierPassword));
    }

    @Test
    @DisplayName("Check courier without login creation api/v1/courier")
    public void testCourierWithoutLoginCreation() throws JsonProcessingException {
        Courier courier = new Courier();
        courier.setPassword(courierPassword);
        courier.setName(courierFirstName);
        steps.checkCreateCourierWithoutParameter(courier);
    }

    @Test
    @DisplayName("Check courier without password creation api/v1/courier")
    public void testCourierWithoutPasswordCreation() throws JsonProcessingException {
        Courier courier = new Courier();
        courier.setLogin(courierLogin);
        courier.setName(courierFirstName);
        steps.checkCreateCourierWithoutParameter(courier);
    }

    @Test
    @DisplayName("Check courier login api/v1/courier/login")
    public void testCourierLogin() throws JsonProcessingException {
        Courier courier = new Courier(courierLogin, courierPassword, courierFirstName);
        steps.createCourier(courier);
        steps.checkCourierExist(steps.getCourierId(courier.getLogin(), courier.getPassword()));
        steps.deleteCourierById(steps.getCourierId(courierLogin, courierPassword));
    }

    @Test
    @DisplayName("Check courier login without login api/v1/courier/login")
    public void testCourierLoginWithoutParameter() throws JsonProcessingException {
        Courier courier = new Courier(courierLogin, courierPassword, null);
        steps.createCourier(courier);
        courier.setLogin(null);

        ObjectMapper objectMapper = new ObjectMapper();
        String courierJson = objectMapper.writeValueAsString(courier);

        steps.checkCourierLoginWithoutParameter(courierJson);
        steps.deleteCourierById(steps.getCourierId(courierLogin, courierPassword));
    }

    @Test
    @DisplayName("Check courier login with non existing login api/v1/courier/login")
    public void testNonExistCourierLogin() throws JsonProcessingException {
        Courier courier = new Courier(courierLogin, courierPassword, courierFirstName);
        steps.checkNonExistCourierLogin(courier);
    }

    @Test
    @DisplayName("Check order created api/v1/orders")
    public void testCreateOrder() {

        Order order = new Order("Dmitriy", "Autotesterman",
                "Freedom bvd, 17 apt.",7,
                "+78889219192",
                5,
                "2022-06-06",
                "some comment for courier",
                colors
        );
        steps.checkGetOrderTrackNumber(order);
    }

    @Test
    @DisplayName("Check courier's orders api/v1/orders?courierId=Id")
    public void testGetCourierOrders() throws JsonProcessingException {
        Courier courier = new Courier(courierLogin, courierPassword, courierFirstName);
        steps.createCourier(courier);
    }
}
