package Order;

import Config.BaseTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateOrderHelpers {

    @Step("Создание нового заказа")
    public Response createNewOrder(List<String> color) {

        OrderParameter orderData = new OrderParameter(OrderData.FIRST_NAME, OrderData.LAST_NAME, OrderData.ADDRESS, OrderData.METRO_STATION, OrderData.PHONE, OrderData.RENT_TIME, OrderData.DELIVERY_DATE, OrderData.COMMENT, color);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(orderData)
                        .when()
                        .post("/api/v1/orders");
        return response;
    }
}
