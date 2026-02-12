import io.qameta.allure.Description;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.notNullValue;

public class CreateOrderTest {
    CreateOrderHelpers createOrder = new CreateOrderHelpers();

    @Description("Создание заказа. Цвет черный")
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
                Arguments.of(List.of("BLACK")),
                Arguments.of(List.of("GREY")),
                Arguments.of(List.of("BLACK", "GREY")),
                Arguments.of((List<String>) null)
        );
    }
    }

