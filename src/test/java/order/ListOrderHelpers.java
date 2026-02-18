package order;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ListOrderHelpers {

    @Step("Получение списка заказов по данным станций метро")
    public Response getListOrdersOnStation() {
        String nearestStation = "[\"1\", \"2\"]";
        Response response =
                given()
                        .queryParam("nearestStation", nearestStation)
                        .header("Content-type", "application/json")
                        .and()
                        .when()
                        .get("/api/v1/orders");
        return response;
    }
}