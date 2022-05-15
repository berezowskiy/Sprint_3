import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.*;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import static org.hamcrest.Matchers.*;

public class GetApiTest {

    private final PostSteps postSteps = new PostSteps();
    private final GetSteps getSteps = new GetSteps();
    Faker faker = new Faker();
    String courierLogin = faker.name().username();
    String courierPassword = faker.internet().password();
    String courierFirstName = faker.name().fullName();


    @Test
    @DisplayName("Check courier's orders api/v1/orders?courierId=Id")
    @Description("Basic test for api/v1/orders endpoint")
    public void testGetCourierOrders() throws JsonProcessingException {
        Courier courier = new Courier(courierLogin, courierPassword, courierFirstName);
        postSteps.createCourier(courier);
        int courierId = postSteps.getCourierId(courier);

        getSteps.getCourierOrders(courierId)
                .then().assertThat().body("orders", notNullValue())
                .and().statusCode(200);
    }
}
