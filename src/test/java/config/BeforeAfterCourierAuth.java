package config;

import courier.CreatingAndDeletingCourierHelpers;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BeforeAfterCourierAuth {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @BeforeEach
    public void createCourierBeforeAuth() {
        CreatingAndDeletingCourierHelpers courierHelper = new CreatingAndDeletingCourierHelpers();
        courierHelper.createNewCourier();
    }

    @AfterEach
    public void deleteNewCourier() {
        CreatingAndDeletingCourierHelpers createCourier = new CreatingAndDeletingCourierHelpers();
        createCourier.deleteCourier();
    }
}
