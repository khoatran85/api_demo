package demo;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
//import static restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;


public class Test01_Method1 {


    @BeforeClass
    public void BeforeClass() {

    }

    //  @Test
    public void Request1_C1() {
        Response response = get("https://reqres.in/api/users?page=2");
        System.out.println(response.getBody());
        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getTime());
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    // @Test
    public void Request1_GET() {
        given().get("https://reqres.in/api/users?page=2").then()
                .statusCode(200)
                .body("data.id[0]", equalTo(7));
    }

//    @Test
    public void Request1_POST() {
        JSONObject request = new JSONObject();
        request.put("name", "khoa");
        request.put("job", "QC");
        System.out.println(request);
        System.out.println(request.toJSONString());
        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(request.toJSONString())
           .when()
                .post("https://reqres.in/api/users")
           .then()
                .statusCode(201);


    }

//    @Test
    public void Request1_PUT() {
        JSONObject request = new JSONObject();
        request.put("name", "khoa1");
        request.put("job", "QC1");
        System.out.println(request);
        System.out.println(request.toJSONString());
        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("khoa1"))
                .body("job", equalTo("QC1"))
                .log().all();
    }

//    @Test
    public void Request1_PATCH() {
        JSONObject request = new JSONObject();
        request.put("name", "khoa2");
        request.put("job", "QC2");
        System.out.println(request);
        System.out.println(request.toJSONString());
        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("khoa2"))
                .body("job", equalTo("QC2"))
                .log().all();
    }

//    @Test
    public void Request1_DELETE() {

        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)
                .log().all();
    }

    @Test
    public void Request1_PATCH1() {
        baseURI = "https://reqres.in/api/";

        JSONObject request = new JSONObject();
        request.put("name", "khoa3");
        request.put("job", "QC3");
        System.out.println(request);
        System.out.println(request.toJSONString());
        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .patch("users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("khoa3"))
                .body("job", equalTo("QC3"))
                .log().all();
    }
}
