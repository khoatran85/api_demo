package testCases;

import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;

import static io.restassured.RestAssured.filters;
import static io.restassured.RestAssured.given;

public class ParentAddAvatar {

    @Test
    public void Add_Avatar_Success() {
        RestAssured.baseURI = "http://ec2-54-169-186-182.ap-southeast-1.compute.amazonaws.com:9000";
        File file = new File("./data/image.JPG");
        Response response = given().
        header("X-Client-Key", "EJXM9hqU2QbzpU7mRyFF").
        header("Authorization", "Bearer " + ParentLoginPasscode.parentLoginPasscodeToken).
//        multiPart("file", new File("./data/image1.png")).
        multiPart("file", file, "application/json").

        when().post("change-avatar/" + Register.parentUserID).
        then().log().all().extract().response();

    }
}
