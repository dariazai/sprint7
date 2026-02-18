package order;

import config.BaseTest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class ListOrderTest extends BaseTest {
    @Description("Получение списка заказов")
    @Test
    public void ListOrderTest() {
        ListOrderHelpers getList = new ListOrderHelpers();
        getList.getListOrdersOnStation()
                .then()
                .statusCode(SC_OK)
                .body("orders", notNullValue());
    }
}