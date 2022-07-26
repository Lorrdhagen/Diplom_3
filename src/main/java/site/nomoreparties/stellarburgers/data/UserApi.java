package site.nomoreparties.stellarburgers.data;

import site.nomoreparties.stellarburgers.data.UserCredebtials;

import static io.restassured.RestAssured.given;

public class UserApi extends BaseApiSpec {

    public String loginUser(UserCredebtials userCredebtials) {
        return given()
                .spec(getInitSpec())
                .body(userCredebtials)
                .when()
                .post("/api/auth/login")
                .then()
                .assertThat().statusCode(200)
                .extract().path("accessToken");
    }

    public boolean deleteUser(String accessToken) {
        return given()
                .spec(getInitSpecWithoutContentType())
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then()
                .assertThat().statusCode(202)
                .extract().path("success");
    }
}
