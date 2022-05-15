package client;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BaseHttpClient {

    private final String JSON = "application/json";
    protected final String baseUrl = "https://qa-scooter.praktikum-services.ru";

    protected Response doPostRequest(String uri, Object body) {
        return given().header("Content-type", JSON).body(body).post(uri);
    }

    protected Response doGetRequest(String uri, String JSON) {
        return given().header("Content-type", JSON).get(uri);
    }

    protected Response doDeleteRequest(String uri) { return given().delete(uri); }

}
