package client;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetApiClient extends BaseHttpClient{

    public Response getCourierOrders(String paramName, String paramValue) {
        return given().queryParam(paramName, paramValue).when().get(super.baseUrl + "/v1/orders");
    }
}
