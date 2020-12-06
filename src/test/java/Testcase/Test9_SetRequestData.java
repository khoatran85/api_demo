package Testcase;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test9_SetRequestData {

    @Test
    public void testQueryParameters(){
        given().
                queryParam("postId", 1).
                queryParam("id", 2).
                contentType(ContentType.JSON).accept(ContentType.JSON).
        when().get("https://jsonplaceholder.typicode.com/comments").
        then().statusCode(200).
                body("email", equalTo("Jayne_Kuhic@sydney.com"));
    }
}
