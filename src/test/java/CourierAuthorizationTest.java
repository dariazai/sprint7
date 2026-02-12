import io.qameta.allure.Description;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class CourierAuthorizationTest {
    @Description("Авторизация курьера. Позитивная проверка")
    @Test
    public void courierAuthorizationSuccessTest() {
        CreatingAndDeletingCourierHelpers createCourier = new CreatingAndDeletingCourierHelpers();
        createCourier.userAuthorization()
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }
}
