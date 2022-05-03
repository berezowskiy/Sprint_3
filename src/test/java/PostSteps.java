import client.DeleteApiClient;
import client.PostApiClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import model.Courier;
import model.Order;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class PostSteps {

    private final PostApiClient postApiClient = new PostApiClient();
    private final DeleteApiClient deleteApiClient = new DeleteApiClient();
    private final String createCourierSameLoginMessage = "Этот логин уже используется. Попробуйте другой.";
    private final String createCourierWithoutParameterMessage = "Недостаточно данных для создания учетной записи";
    private final String loginCourierWithoutParameterMessage = "Недостаточно данных для входа";
    private final String loginNonExistCourierMessage = "Учетная запись не найдена";


//    public Courier createCourier(Courier courier) {
//        return postApiClient.createCourier(courier);
//    }

    public Response createCourier(Courier courier) {
        return postApiClient.createCourierResponse(courier);
    }

    public Response loginCourier(String courierJson) {
        return postApiClient.loginCourier(courierJson);
    }

    public Integer getOrderCreationStatusCode(Order order) {
        return postApiClient.createOrder(order).statusCode();
    }

    public Response createOrder(Order order) {
        return postApiClient.createOrder(order);
    }

    public int getOrderTrackNumber(Order order) {
        return postApiClient.createOrder(order).then().extract().path("track");
    }

    public void deleteCourierById(Integer courierId) {
        deleteApiClient.deleteCourierById(courierId);
    }

    public Integer getCourierId(String courierLogin, String courierPassword) {
        String loginRequestBody = "{\"login\":\"" + courierLogin + "\"," + "\"password\":\"" + courierPassword + "\"}";
        return postApiClient.loginCourier(loginRequestBody).then().extract().path("id");
    }

    public void checkCourierExist(Integer courierId) {
        assertThat(courierId).isNotNull();
    }

    public void checkCourierCreate(Courier courier) throws JsonProcessingException {
        postApiClient.createCourierResponse(courier)
                .then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
    }
    public void checkCreateCourierWithSameLogin(Courier courier) throws JsonProcessingException {
        postApiClient.createCourierResponse(courier)
                .then().assertThat().body("message", equalTo(createCourierSameLoginMessage))
                .and()
                .statusCode(409);
    }

    public void checkCreateCourierWithoutParameter(Courier courier) throws JsonProcessingException {
        postApiClient.createCourierResponse(courier)
                .then().assertThat().body("message", equalTo(createCourierWithoutParameterMessage))
                .and()
                .statusCode(400);
    }

    public void checkCourierLoginWithoutParameter(String courierJson) throws JsonProcessingException {
        postApiClient.loginCourier(courierJson)
                .then().assertThat().body("message", equalTo(loginCourierWithoutParameterMessage))
                .and()
                .statusCode(400);
    }

    public void checkNonExistCourierLogin(Courier courier) throws JsonProcessingException {
        postApiClient.loginCourier(courier)
                .then().assertThat().body("message", equalTo(loginNonExistCourierMessage))
                .and()
                .statusCode(404);
    }

    public void checkGetOrderTrackNumber(Order order) {
        postApiClient.createOrder(order)
                .then().assertThat().body("track", notNullValue())
                .and().statusCode(201);
    }

}


