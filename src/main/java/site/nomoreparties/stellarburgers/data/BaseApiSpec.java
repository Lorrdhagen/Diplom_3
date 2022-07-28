package site.nomoreparties.stellarburgers.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseApiSpec {

    private final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    public RequestSpecification getInitSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .addFilter(new ResponseLoggingFilter(LogDetail.STATUS))
                .build();
    }
}
