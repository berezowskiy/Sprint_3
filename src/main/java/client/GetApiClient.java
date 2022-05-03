package client;

import io.restassured.response.Response;

public class GetApiClient extends BaseHttpClient{

    private final String baseUrl = "https://qa-scooter.praktikum-services.ru";

    public Response getOrdersByCourierId(Integer id) {
        return doGetRequest(baseUrl + "/v1/orders?courierId=" + id);
    }
}
