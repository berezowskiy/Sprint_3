import client.GetApiClient;
import io.restassured.response.Response;
import io.qameta.allure.Step;

public class GetSteps {

    private final GetApiClient getApiClient = new GetApiClient();
    final String courierIdParamName = "courierId";

    @Step("Send GET request to /v1/orders")
    public Response getCourierOrders(Integer courierId) {
        return getApiClient.getCourierOrders(courierIdParamName, courierId.toString());
    }
}
