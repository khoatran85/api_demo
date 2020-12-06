package Testcase;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test11_TimeMeasurement {
    @Test
    public void testResponseTime(){
        long t = given().get("https://jsonplaceholder.typicode.com/photos").time();
        System.out.println("Time(ms): " + t);
    }
    @Test
    public void testResponseTimeInUnit(){
        long t = given().get("https://jsonplaceholder.typicode.com/photos/1").timeIn(TimeUnit.MILLISECONDS);
        System.out.println("Time(ms): " + t);
    }

    @Test
    public void testResponseAssertion(){
       given().get("https://jsonplaceholder.typicode.com/photos/1").then().time(lessThan(5000L));
    }
}
