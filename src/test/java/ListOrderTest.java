import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ListOrderTest {
    @Description("Создание курьера и удаление курьера. Позитивная проверка")
    @Test
    public void ListOrderTest() {
        RestAssured.baseURI = BaseURI.URL;
        String nearestStation = "[\"1\", \"2\"]";


        Response getListOrders =
                given()
                        .queryParam("nearestStation", nearestStation)
                        .header("Content-type", "application/json")
                        .and()
                        .when()
                        .get("/api/v1/orders");
        getListOrders.then()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}

