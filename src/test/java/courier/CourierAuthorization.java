package courier;

import config.BeforeAfterCourierAuth;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.apache.http.HttpStatus.*;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierAuthorization extends BeforeAfterCourierAuth {
    CreatingAndDeletingCourierHelpers userAuthorization;

    @BeforeEach
    public void setUp() {
        userAuthorization = new CreatingAndDeletingCourierHelpers();
    }

    @Description("Авторизация курьера. Позитивная проверка")
    @Test
    public void courierAuthorizationSuccessTest() {
        CreatingAndDeletingCourierHelpers createCourier = new CreatingAndDeletingCourierHelpers();
        createCourier.userAuthorization()
                .then()
                .statusCode(SC_OK)
                .body("id", notNullValue());
    }

    @Description("Авторизация курьера. Передется только пароль")
    @Test
    public void courierAuthorizationWithoutLogin() {
        userAuthorization.userAuthorization(null, CourierData.PASSWORD)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Description("Авторизация курьера. Передется только логин")
    @Test
    public void courierAuthorizationWithoutPassword() {
        userAuthorization.userAuthorization(CourierData.LOGIN, null)
                .then()
                .statusCode(SC_GATEWAY_TIMEOUT)
                .body(equalTo("Service unavailable"));
    }

    @Description("Авторизация кульера с несуществующими данными ")
    @ParameterizedTest
    @MethodSource("provider")
    public void courierAuthorizationNoValidData(String login, String password) {
        userAuthorization.userAuthorization(login, password)
                .then()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of("Eda", CourierData.PASSWORD),
                Arguments.of(CourierData.LOGIN, "325468"),
                Arguments.of("Eda", "325468"));
    }
}