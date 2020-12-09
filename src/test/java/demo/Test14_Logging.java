package demo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Test14_Logging {
//    @Test
    public void testLogging1(){
        given().
                get("https://jsonplaceholder.typicode.com/posts").
                then().
//                log().body();
//                log().headers();
//                log().cookies().
//                log().all().
                log().status();
    }

//    @Test
    public void testLogging2(){
        given().
                get("http://api.fonts.com/rest/json/Domains/").
                then().
                log().ifError();
    }
    @Test
    public void testLogging3(){
        given().
                get("http://api.fonts.com/rest/json/Domains/").
                then().
                log().ifStatusCodeIsEqualTo(400);
    }


}
