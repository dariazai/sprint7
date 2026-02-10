import io.qameta.allure.Description;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;

public class CreateCourierNegativeTest {
    CreatingAndDeletingCourierHelpers createCourier = new CreatingAndDeletingCourierHelpers();

    @Description("Попытка создания курьера с неполными данными")
    @ParameterizedTest
    @MethodSource("provider")
    public void createCourierNegativeTest(String login, String password, String firstName) {
        createCourier.createNewCourier(login, password, firstName)
                .then()
                .statusCode(400)
                .body("code", equalTo(400),
                        "message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(CourierData.LOGIN, null, CourierData.FIRST_NAME),
                Arguments.of(null, CourierData.PASSWORD, CourierData.FIRST_NAME));
    }

    @Description("Попытка создания курьера с существующим логином")
    @Test
    public void creatingCourierWithExistingLoginTest() {
        createCourier.createNewCourier()
                .then()
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));
        createCourier.createNewCourier()
                .then()
                .statusCode(409)
                .body("code", equalTo(409),
                        "message", equalTo("Этот логин уже используется. Попробуйте другой."));
        createCourier.deleteCourier();
    }
}