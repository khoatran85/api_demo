package testCases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.security.auth.login.LoginContext;

import static io.restassured.RestAssured.given;

public class ParentSetupPasscode {
    static String passcode = "1234";
    static String setupPasscodeToken;

    @Test
    public void Setup_Passcode_Success() {
        RestAssured.baseURI = "http://ec2-54-169-186-182.ap-southeast-1.compute.amazonaws.com:9000";
        JSONObject request = new JSONObject();
        request.put("userId", Register.parentUserID);
        request.put("passcode", passcode);

        Response response = given().header("X-Client-Key", "EJXM9hqU2QbzpU7mRyFF").
                header("Content-Type", "application/json").
                header("Authorization", "Bearer " + ParentLogin.parentLoginToken).
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString()).
                when().post("/passcode-setup").
                then().log().all().extract().response();

        setupPasscodeToken = response.jsonPath().get("token");
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");

    }
}
