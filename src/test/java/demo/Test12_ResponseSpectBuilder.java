package demo;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Test12_ResponseSpectBuilder {
    ResponseSpecification responseSpect;

    @BeforeClass
    public void BeforeClass(){
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);
        builder.expectHeader("Content-Type", "application/json; charset=utf-8");
        builder.expectHeader("Cache-Control", "max-age=43200");
        responseSpect = builder.build();
    }

    @Test
    public void testResponse1(){
        given().get("https://jsonplaceholder.typicode.com/photos").then().
                spec(responseSpect).time(lessThan(4000L));
    }
    @Test
    public void testResponse2(){
        given().get("https://jsonplaceholder.typicode.com/photos").then().
                spec(responseSpect);
    }
}
