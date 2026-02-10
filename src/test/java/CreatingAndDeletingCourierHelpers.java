import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreatingAndDeletingCourierHelpers {

    public Response createNewСourier() {
        RestAssured.baseURI = BaseURI.URL;
                    CourierParameter courierData = new CourierParameter(CourierData.LOGIN,CourierData.PASSWORD,CourierData.FIRST_NAME);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courierData)
                        .when()
                        .post("/api/v1/courier");
        response.then()
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));
    }
    public void deleteСourier() {
        RestAssured.baseURI = BaseURI.URL;
        CourierParameter courierData = new CourierParameter("toropishka","32145");
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
