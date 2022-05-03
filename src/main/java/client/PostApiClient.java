package client;

import io.restassured.response.Response;
import model.Courier;
import model.Order;

public class PostApiClient extends BaseHttpClient {

    private final String baseUrl = "https://qa-scooter.praktikum-services.ru";

    public Courier createCourier(Courier courier) {
        return doPostRequest(baseUrl + "/api/v1/courier", courier).body().as(Courier.class);
    }

    public Response createCourierResponse(Courier courier) {
        return doPostRequest(baseUrl + "/api/v1/courier", courier);
    }

    public Response loginCourier(String loginPasswordBody) {
        return doPostRequest(baseUrl + "/api/v1/courier/login", loginPasswordBody);
    }

    public Response loginCourier(Courier courier) {
        return doPostRequest(baseUrl + "/api/v1/courier/login", courier);
    }

    public Response createOrder(Order order) {
        return doPostRequest(baseUrl + "/api/v1/orders", order);
    }

//    public Response createCourierResponse(Courier courier) throws JsonProcessingException {


//        String requestBody;
//        String courierLogin = courier.getLogin();
//        String courierPassword = courier.getPassword();
//        String courierFirstName = courier.getName();
//
//        if(courierLogin == null) {
//            requestBody = "{\"password\":\"" + courierPassword + "\"," + "\"firstName\":\"" + courierFirstName + "\"}";
//        } else if (courierPassword == null) {
//            requestBody = "{\"login\":\"" + courierLogin + "\"," + "\"firstName\":\"" + courierFirstName + "\"}";
//        } else {
//            requestBody = "{\"login\":\"" + courierLogin + "\","
//                    + "\"password\":\"" + courierPassword + "\","
//                    + "\"firstName\":\"" + courierFirstName + "\"}";
//        }

//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(courier);
//        System.out.println(requestBody);
//        return doPostRequest(baseUrl + "/api/v1/courier", requestBody);
//    }

//    public Integer loginCourier(String loginPasswordBody) {
//        Response response = doPostRequest(baseUrl + "/api/v1/courier/login", loginPasswordBody);
//        return response.then().extract().path("id");
//    }


}
