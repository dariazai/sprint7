import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateCourierNegativeTest {
    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }


    @Description("Попытка создания курьера с неполными данными")
    @ParameterizedTest
    @MethodSource("provider")
    public void createCourierNegativeTest(String login, String password) {
        CourierParameter courierData = new CourierParameter(login, password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courierData)
                        .when()
                        .post("/api/v1/courier");
        response.then()
                .statusCode(400)
                .body("code", equalTo(400),
                        "message", equalTo("Недостаточно данных для создания учетной записи"));

    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(CourierData.LOGIN, null),
                Arguments.of(null, CourierData.PASSWORD));
    }


    @Description("Попытка создания курьера с существующим логином")
    @Test
    public void creatingCourierWithExistingLoginTest() {

        CreatingAndDeletingCourierHelpers courier = new CreatingAndDeletingCourierHelpers();
        courier.createNewСourier();
        courier.createNewСourier();
    }
}