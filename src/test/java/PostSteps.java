import client.DeleteApiClient;
import client.PostApiClient;
import io.restassured.response.Response;
import model.Courier;
import model.Order;
import io.qameta.allure.Step;
public class PostSteps {

    private final PostApiClient postApiClient = new PostApiClient();
    private final DeleteApiClient deleteApiClient = new DeleteApiClient();

    @Step("Send POST request to /api/v1/courier")
    public Response createCourier(Courier courier) {
        return postApiClient.createCourierResponse(courier);
    }

    @Step("Send POST request to /api/v1/courier/login")
    public Response loginCourier(Courier courier) {
        return postApiClient.loginCourier(courier);
    }

    @Step("Send POST request to /api/v1/orders and get status code")
    public Integer getOrderCreationStatusCode(Order order) {
        return postApiClient.createOrder(order).statusCode();
    }

    @Step("Send POST request to /api/v1/orders")
    public Response createOrder(Order order) {
        return postApiClient.createOrder(order);
    }

    @Step("Send DELETE request to /api/v1/courier")
    public void deleteCourierById(Integer courierId) {
        deleteApiClient.deleteCourierById(courierId);
    }

    @Step("Send POST request to /api/v1/courier/login and extract value of \"id\" from response")
    public Integer getCourierId(Courier courier) {
        return postApiClient.loginCourier(courier).then().extract().path("id");
    }

}


