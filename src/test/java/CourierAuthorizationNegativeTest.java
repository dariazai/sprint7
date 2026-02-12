import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;


public class CourierAuthorizationNegativeTest {
    CreatingAndDeletingCourierHelpers userAuthorization;

    @BeforeEach
    public void setUp() {
        userAuthorization = new CreatingAndDeletingCourierHelpers();
    }

    @Description("Авторизация курьера с неверным логином / паролем")
    @Test

    public void courierAuthorizationNoFullData() {

        userAuthorization.userAuthorization(null, CourierData.PASSWORD)
                .then()
                .statusCode(400)
                .body("massage", equalTo("Недостаточно данных для входа"));
    }

    @Description("Авторизация с невалидными данными ")
    @ParameterizedTest
    @MethodSource("provider")
    public void courierAuthorizationNoValidData(String login, String password) {

        userAuthorization.userAuthorization(login, password)
                .then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of("Eda", CourierData.PASSWORD),
                Arguments.of(CourierData.LOGIN, "325468"),
                Arguments.of("Eda", "325468"));
    }
}


