
import model.*;

import org.junit.Before;
import org.junit.Test;
// импортируем библиотеку генерации строк
import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Arrays;
import java.util.List;

public class GetApiTest {

    private final PostSteps postSteps = new PostSteps();
    private final GetSteps getSteps = new GetSteps();
    // с помощью библиотеки RandomStringUtils генерируем логин
    // метод randomAlphabetic генерирует строку, состоящую только из букв, в качестве параметра передаём длину строки
    String courierLogin = RandomStringUtils.randomAlphabetic(10);
    // с помощью библиотеки RandomStringUtils генерируем пароль
    String courierPassword = RandomStringUtils.randomAlphabetic(10);
    // с помощью библиотеки RandomStringUtils генерируем имя курьера
    String courierFirstName = RandomStringUtils.randomAlphabetic(10);

    List<String> colorsBoth = Arrays.asList("BLACK", "GREY");

    Order order = new Order("Dmitriy", "Autotesterman",
            "Freedom bvd, 17 apt.",7,"+78889219192",5,
            "2022-06-06","some comment for courier",colorsBoth
    );

    @Before
    public void setUp() {

    }

    @Test
    public void testGetCourierOrders() throws JsonProcessingException {
        Courier courier = new Courier(courierLogin, courierPassword, courierFirstName);
        postSteps.createCourier(courier);
        int courierId = postSteps.getCourierId(courierLogin, courierPassword);

        getSteps.checkCourierHasOrder(courierId);
    }
}
