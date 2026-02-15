package Order;

import Config.BaseTest;
import io.qameta.allure.Description;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.notNullValue;

public class CreateOrderTest extends BaseTest {
    CreateOrderHelpers createOrder = new CreateOrderHelpers();

    @Description("Создание заказа с выбором различных цветов")
    @ParameterizedTest
    @MethodSource("provider")
    public void createNewOrder(List<String> color) {
        createOrder.createNewOrder(color)
                .then()
                .statusCode(201)
                .body("track", notNullValue());
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(List.of(OrderData.COLOR_BLACK)),
                Arguments.of(List.of(OrderData.COLOR_GREY)),
                Arguments.of(List.of(OrderData.TWO_COLOR)),
                Arguments.of((List<String>) null)
        );
    }
}

