package testCases;

import commons.RestUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class Register extends RestUtil {
    static String parentUserID;
    static String childrenUserID;

    static String parentEmail = "tnk" + randomNum() + "@gmail.com";
    static String parentPassword = "12345678";
    static String parentFirstName = "khoa";
    static String parentLastName = "Tran";
    static String parentPhone = "84903910613";
    static String parentBirthday = "03-12-2019";


    @Test
    public void Register_successfully(){
        RestAssured.baseURI = "http://ec2-54-169-186-182.ap-southeast-1.compute.amazonaws.com:9000/";
        JSONObject request = new JSONObject();
        request.put("email", parentEmail);
        request.put("password", parentPassword);
        request.put("firstName", parentFirstName);
        request.put("lastName", parentLastName);
        request.put("phone", parentPhone);
        request.put("birthday", parentBirthday);

        request.put("children", Arrays.asList(new LinkedHashMap<String, String>() {
            {
                put("cardId", "174a7957-fbe6-453f-af00-b72dd2bb95a9");
                put("firstName", "mohamed");
                put("lastName", "ally");
                put("childPassword", "87654321");
            }
        }));

        Response response =  given().
                                header("Content-Type", "application/json").
                                header("X-Client-Key", "EJXM9hqU2QbzpU7mRyFF").
                                contentType(ContentType.JSON).accept(ContentType.JSON).
                                body(request.toJSONString()).
                            post("/sign-up").
                            then().extract().response();

        parentUserID = response.jsonPath().get("userId").toString();
        childrenUserID = response.jsonPath().get("children.userId").toString();
        System.out.println(parentUserID);
        System.out.println(childrenUserID);
    }


}
