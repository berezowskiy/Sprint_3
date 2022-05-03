import client.GetApiClient;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class GetSteps {

    private final GetApiClient getApiClient = new GetApiClient();

    public Response getCourierOder(Integer id) {
        return getApiClient.getOrdersByCourierId(id);
    }

    public void checkCourierHasOrder(Integer id) {
        getApiClient.getOrdersByCourierId(id)
                .then().assertThat().body("orders", notNullValue())
                .and().statusCode(200);
    }
}
