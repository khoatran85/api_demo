package testCases;

import commons.RestUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ParentAddChild extends RestUtil {

    static String childFirstName = "Vina";
    static String childLastName = "Milk";
    static String childUsername = "child" + randomNum();
    static String childBirthday = "22-01-2000";
    static String childPassword = "87654321";


    @Test
    public void Add_Child_Success() {
        RestAssured.baseURI = "http://ec2-54-169-186-182.ap-southeast-1.compute.amazonaws.com:9000";
        JSONObject request = new JSONObject();
        request.put("cdId", "174a7957-fbe6-453f-af00-b72dd2bb95a9");
        request.put("firstName", childFirstName);
        request.put("lastName", childLastName);
        request.put("username", childUsername);
        request.put("birthday", childBirthday);
        request.put("password", childPassword);

        Response response = given().header("X-Client-Key", "EJXM9hqU2QbzpU7mRyFF").
                header("Content-Type", "application/json").
                header("Authorization", "Bearer " + ParentLoginPasscode.parentLoginPasscodeToken).
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString()).
                when().post("/add-child/" + Register.parentUserID).
                then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
        Assert.assertEquals(response.jsonPath().get("success"),true);
    }
}
