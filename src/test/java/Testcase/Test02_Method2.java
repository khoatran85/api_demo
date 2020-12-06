package Testcase;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class Test02_Method2 {

//    @Test
    public void get_method(){
        RestAssured.baseURI = "http://localhost:3000/users";
    given().when().
            get().
    then().statusCode(200).body("id[0]", equalTo (1))
    .body("id", hasItems (1,2,3));

    }

    @Test
    public void Register() {
        String registerURI = "http://ec2-54-169-186-182.ap-southeast-1.compute.amazonaws.com:9000/sign-up";

        JSONObject request = new JSONObject();
        request.put("email", "khoatwewq3das221@gmail.com");
        request.put("password", "12345678");
        request.put("firstName", "khoa");
        request.put("lastName", "tran");
        request.put("phone", "84903910613");
        request.put("birthday", "03-12-2019");

        request.put("children", Arrays.asList(new LinkedHashMap<String, Object>() {
            {
                put("cardId", "174a7957-fbe6-453f-af00-b72dd2bb95a9");
                put("firstName", "mohamed");
                put("lastName", "ally");
                put("childPassword", "87654321");
            }
        }));

      Response response =  given().header("Content-Type", "application/json").header("X-Client-Key", "EJXM9hqU2QbzpU7mRyFF")
                .contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString())
                .post(registerURI).then().extract().response();

//      String childuserId =  given().header("Content-Type", "application/json").header("X-Client-Key", "EJXM9hqU2QbzpU7mRyFF")
//                .contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString())
//                .post(registerURI)
//                .then().statusCode(200).log().all().extract().path("children.userId");
     String parentuserId = response.path("userId");
     String childrenID = response.path("children.userId");
        System.out.println(parentuserId);
        System.out.println(childrenID);
    }





//@Test
    public void Login(){
    JSONObject request = new JSONObject();
    request.put("id", "khoatran113s3@gmail.com");
    request.put("type", "EMAIL");
    request.put("password", "12345678");
    request.put("role", "PARENT");
    request.put("deviceId", "JZO54K");

    given().header("Content-Type", "application/json").header("X-Client-Key", "EJXM9hqU2QbzpU7mRyFF")
            .contentType(ContentType.JSON).accept(ContentType.JSON)
            .body(request.toJSONString()).
            when().
            post("http://ec2-54-169-186-182.ap-southeast-1.compute.amazonaws.com:9000/login").
    then()
                    .statusCode(200)
                    .body("firstName", equalTo ("khoa"))
                    .body("lastName", equalTo("tran"))
                    .body("role", is("PARENT"))
                    .body("avatar", is("null"))
                    .body("isSetPasscode", is("true"))
                    .body("isKyc", is("false"))
                    .body("kyc_status", is("null"));

    }
}
