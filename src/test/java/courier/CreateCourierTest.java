package courier;

import config.BaseTest;
import io.qameta.allure.Description;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.apache.http.HttpStatus.*;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateCourierTest extends BaseTest {
    static CreatingAndDeletingCourierHelpers createCourier;
    private boolean courierCreated;

    @BeforeAll
    public static void setUp() {
        createCourier = new CreatingAndDeletingCourierHelpers();
    }

    @Description("Создание курьера и удаление курьера. Позитивная проверка")
    @Test
    public void createNewCourier() {

        createCourier.createNewCourier()
                .then()
                .statusCode(SC_CREATED)
                .body("ok", Matchers.equalTo(true));
        courierCreated = true;
    }

    @Description("Попытка создания курьера с неполными данными")
    @ParameterizedTest
    @MethodSource("provider")
    public void createCourierNotAllFieldsTransmittedTest(String login, String password, String firstName) {
        createCourier.createNewCourier(login, password, firstName)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("code", equalTo(400),
                        "message", equalTo("Недостаточно данных для создания учетной записи"));
        courierCreated = false;
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
                .statusCode(SC_CREATED)
                .body("ok", Matchers.equalTo(true));

        createCourier.createNewCourier()
                .then()
                .statusCode(SC_CONFLICT)
                .body("code", equalTo(409),
                        "message", equalTo("Этот логин уже используется. Попробуйте другой."));
        courierCreated = true;
    }

    @AfterEach
    public void afterEach() {
        if (courierCreated) {
            createCourier.deleteCourier();
        }
    }
}