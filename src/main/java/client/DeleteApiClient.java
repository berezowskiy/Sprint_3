package client;

import io.restassured.response.Response;


public class DeleteApiClient extends BaseHttpClient {

    private final String baseUrl = "https://qa-scooter.praktikum-services.ru";

    public Response deleteCourierById(Integer courierId) {
        return doDeleteRequest(baseUrl + "/api/v1/courier/" + courierId);
    }
}
