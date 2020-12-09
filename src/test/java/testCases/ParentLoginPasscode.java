package testCases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ParentLoginPasscode {

    static String parentLoginPasscodeToken;
    @Test
    public void Setup_Passcode_Success() {
        RestAssured.baseURI = "http://ec2-54-169-186-182.ap-southeast-1.compute.amazonaws.com:9000";
        JSONObject request = new JSONObject();
        request.put("userId", Register.parentUserID);
        request.put("passcode", ParentSetupPasscode.passcode);
        request.put("deviceId", "JZO54Kc");
        request.put("fcmToken", "cJaxFKFlrtE:APA91bHeBzeCPjyYlFy7AWpmMdq93zkSpKx4LvTUujBLNEOf7pkoRx8PMu7ga2lG2sNv3VnsU6gNytG1Y1goq1g7iU3NYU_lie7yp4pemZJZRVaKQEmUCgDSPVYrr2EFr5hnHfv2Gupp");

        Response response = given().header("X-Client-Key", "EJXM9hqU2QbzpU7mRyFF").
                header("Content-Type", "application/json").
                header("Authorization", "Bearer " + ParentSetupPasscode.setupPasscodeToken).
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString()).
                when().post("/verify-code").
                then().log().all().extract().response();

        parentLoginPasscodeToken = response.jsonPath().get("token");
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
        Assert.assertEquals(response.jsonPath().get("kyc_status"),null);
        Assert.assertEquals(response.jsonPath().get("isKyc"),false);
    }
}
