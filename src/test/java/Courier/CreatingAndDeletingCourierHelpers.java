package Courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreatingAndDeletingCourierHelpers {

    @Step("Создание нового курьера")
    public Response createNewCourier() {
        return createNewCourier(CourierData.LOGIN, CourierData.PASSWORD, CourierData.FIRST_NAME);
    }

    @Step("Создание нового курьера")
    public Response createNewCourier(String login, String password, String firstName) {
        CourierParameter courierData = new CourierParameter(login, password, firstName);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courierData)
                        .when()
                        .post("/api/v1/courier");
        return response;
    }

    @Step("Удаление курьера")
    public void deleteCourier() {
        CourierParameter courierData = new CourierParameter(CourierData.LOGIN, CourierData.PASSWORD);
        int id = userAuthorization()

                .jsonPath()
                .getInt("id");
        String deleteJson = "{\"id\":\"" + id + "\"}";
        Response deleteResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(deleteJson)
                        .when()
                        .delete("/api/v1/courier/" + id);
        deleteResponse.then()
                .statusCode(200)
                .body("ok", equalTo(true));
    }

    @Step("Авторизация пользовтеляв системе для получения ID")
    public Response userAuthorization(String login, String password) {
        AuthorizationParameter authorizationData = new AuthorizationParameter(login, password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(authorizationData)
                        .when()
                        .post("/api/v1/courier/login");
        return response;
    }

    @Step("Авторизация пользовтеляв системе для получения ID")
    public Response userAuthorization() {
        return userAuthorization(CourierData.LOGIN, CourierData.PASSWORD);
    }
}
