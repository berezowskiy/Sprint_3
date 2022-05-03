package client;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseHttpClient {

    private final String JSON = "application/json";


    protected Response doGetRequest(String uri, String JSON) {
        return given().header("Content-type", JSON).get(uri);
    }

    protected Response doPostRequest(String uri, Object body) {
        return given().header("Content-type", JSON).body(body).post(uri);
    }

    protected Response doPostRequest(String uri, String requestBody) {
        return given().header("Content-type", JSON).body(requestBody).post(uri);
    }

    protected Response doGetRequest(String uri) { return given().get(uri); }

    protected Response doDeleteRequest(String uri) { return given().delete(uri); }

}
