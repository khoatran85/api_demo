package Testcase;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.*;
import static io.restassured.path.json.JsonPath.from;

public class Test7_JsonPath {
    /** very response type */
//    @Test
    public void testJsonPath1(){
       String responseAsString = given().
                get("https://jsonplaceholder.typicode.com/photos").
        then().
               extract().asString();
       List<String> albumIds = from(responseAsString).get("id");
       System.out.println(albumIds);
    }
//    @Test
    public void testJsonPath1_1(){
        String responseAsString = get("https://jsonplaceholder.typicode.com/photos").asString();
        List<String> albumIds = from(responseAsString).get("id");
        System.out.println(albumIds);
        System.out.println(albumIds.size());
    }

    @Test
    public void testJsonPath2 (){
        String response = get("https://jsonplaceholder.typicode.com/users").asString();
        // List<String> listStreet = from(response).get("address.street");
        JsonPath root = new JsonPath(response).setRootPath("address");
        List<String> listStreet = root.get("street");
        System.out.println(listStreet);
        System.out.println(listStreet.size());

    }
}
