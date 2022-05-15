import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class PostParameterizedApiTest {
    private final Order order;
    private final int expected;

    private final PostSteps steps = new PostSteps();
    static List<String> bothColors = Arrays.asList("BLACK", "GREY");
    static List<String> blackColors = Arrays.asList("BLACK");

    static Faker faker = new Faker();
    private final static String firstName = faker.name().fullName();
    private final static String lastName = faker.name().lastName();
    private final static String address = faker.address().streetAddress();
    private final static String phone = faker.phoneNumber().phoneNumber();
    private final static int metroStationId = 7;
    private final static int rentTime = 5;
    private final static String deliveryDate = "2022-07-07";
    private final static String comment = "some comment for courier";

    public PostParameterizedApiTest(Order order, int expected) {
        this.order = order;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getResponseData() {
        return new Object[][] {
                {new Order(firstName,lastName, address, metroStationId, phone, rentTime, deliveryDate, comment,bothColors), 201},
                {new Order(firstName,lastName, address, metroStationId, phone, rentTime, deliveryDate, comment,blackColors), 201},
                {new Order(firstName,lastName, address, metroStationId, phone, rentTime, deliveryDate, comment,null), 201},
        };
    }

    @Test
    @DisplayName("Check status code of /api/v1/orders")
    @Description("Parameterized test for /api/v1/orders endpoint")
    public void shouldBeOrderCreated() {
        int actual = steps.getOrderCreationStatusCode(order);
        assertEquals(expected, actual);
    }

}
