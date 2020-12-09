package testCases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ParentLogin {
    static String parentLoginToken;

    @Test
    public void Login_Successful() {
        RestAssured.baseURI = "http://ec2-54-169-186-182.ap-southeast-1.compute.amazonaws.com:9000";
        JSONObject request = new JSONObject();
        System.out.println(Register.parentEmail);
        request.put("id", Register.parentEmail);
        request.put("type", "EMAIL");
        request.put("password", Register.parentPassword);
        request.put("role", "PARENT");
        request.put("deviceId", "JZO54K");

        Response response = given().header("Content-Type", "application/json").
                header("X-Client-Key", "EJXM9hqU2QbzpU7mRyFF").
                contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString()).
                when().post("/login").
                then().log().all().extract().response();

        parentLoginToken = response.jsonPath().get("token").toString();

        Assert.assertEquals(response.jsonPath().get("userId"), Register.parentUserID);
        Assert.assertEquals(response.jsonPath().get("success").toString(), "true");
        Assert.assertEquals(response.jsonPath().get("role").toString(), "PARENT");
        Assert.assertEquals(response.jsonPath().get("firstName"), Register.parentFirstName);
        Assert.assertEquals(response.jsonPath().get("lastName"), Register.parentLastName);
        Assert.assertEquals(response.jsonPath().get("avatar"), null);
        Assert.assertEquals(response.jsonPath().get("isSetPasscode"), false);
        Assert.assertEquals(response.jsonPath().get("isKyc"), false);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");

    }
}
