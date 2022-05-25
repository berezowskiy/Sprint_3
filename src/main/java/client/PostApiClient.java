package client;

import io.restassured.response.Response;
import model.Courier;
import model.Order;

public class PostApiClient extends BaseHttpClient {

    public Courier createCourier(Courier courier) {
        return doPostRequest(super.baseUrl + "/api/v1/courier", courier).body().as(Courier.class);
    }

    public Response createCourierResponse(Courier courier) {
        return doPostRequest(super.baseUrl + "/api/v1/courier", courier);
    }

    public Response loginCourier(Courier courier) {
        return doPostRequest(super.baseUrl + "/api/v1/courier/login", courier);
    }

    public Response createOrder(Order order) {
        return doPostRequest(super.baseUrl + "/api/v1/orders", order);
    }


}
