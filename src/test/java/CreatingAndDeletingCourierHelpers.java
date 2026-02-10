import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreatingAndDeletingCourierHelpers {

    public Response createNewCourier() {
        return createNewCourier(CourierData.LOGIN, CourierData.PASSWORD, CourierData.FIRST_NAME);

    }

    public Response createNewCourier(String login, String password, String firstName) {
        RestAssured.baseURI = BaseURI.URL;
        CourierParameter courierData = new CourierParameter(login, password, firstName);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courierData)
                        .when()
                        .post("/api/v1/courier");
        return response;
    }

    public void deleteCourier() {
        RestAssured.baseURI = BaseURI.URL;
        CourierParameter courierData = new CourierParameter(CourierData.LOGIN, CourierData.PASSWORD);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courierData)
                        .when()
                        .post("/api/v1/courier/login");
        int id = response.then()
                .statusCode(200)
                .extract()
                .path("id");
        String deleteJson = "{\"id\":\"" + id + "\"}";
        Response deleteResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(deleteJson)
                        .when()
                        .delete("/api/v1/courier/" + id);
        deleteResponse.then()
                .statusCode(200)
                .body("ok", equalTo(true));
    }
}
