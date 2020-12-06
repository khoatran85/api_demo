package Testcase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Test13_RequestSpecBuilder {

    RequestSpecification requestSpec;

    @BeforeClass
    public void BeforeClass (){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addParam("parameter1","parameterValue");
        builder.addHeader("Content-Type", "application/json; charset=utf-8");
        requestSpec = builder.build();
    }

    @Test
    public void testRequest1(){
        given().
                spec(requestSpec).
        when().
                get("https://jsonplaceholder.typicode.com/posts").
                then().
                statusCode(200).
                log().all();
    }
}
