package site.nomoreparties.stellarburgers.data;

import static io.restassured.RestAssured.given;

public class UserApi extends BaseApiSpec {

    private final String REGISTER_URL = "/api/auth/register";
    private final String LOGIN_URL = "/api/auth/login";
    private final String UPD_DEL_URL = "/api/auth/user";

    public String createUser(User user) {
        return given()
                .spec(getInitSpec())
                .body(user)
                .when()
                .post(REGISTER_URL)
                .then()
                .assertThat().statusCode(200)
                .extract().path("accessToken");

    }

    public String loginUser(UserCredebtials userCredebtials) {
        return given()
                .spec(getInitSpec())
                .body(userCredebtials)
                .when()
                .post(LOGIN_URL)
                .then()
                .extract().path("accessToken");
    }

    public boolean deleteUser(String accessToken) {
        return given()
                .spec(getInitSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(UPD_DEL_URL)
                .then()
                .assertThat().statusCode(202)
                .extract().path("success");
    }
}
