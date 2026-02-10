import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateСourierTest {

    CreatingAndDeletingCourierHelpers createCourier = new CreatingAndDeletingCourierHelpers();
    @Description("Создание курьера. Позитивная проверка")
    @Test
    public void createNewСourierPoz() {
                createCourier.createNewСourier();
    }

    @Description("Удаление курьера. Позитивная проверка")
    @Test
    public void deleteСourier() {
        createCourier.deleteСourier();
    }

}