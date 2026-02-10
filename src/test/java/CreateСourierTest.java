import io.qameta.allure.Description;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class CreateСourierTest {
    CreatingAndDeletingCourierHelpers createCourier = new CreatingAndDeletingCourierHelpers();

    @Description("Создание курьера и удаление курьера. Позитивная проверка")
    @Test
    public void createNewСourier() {
        createCourier.createNewCourier()
                .then()
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));
        createCourier.deleteCourier();
    }
}