package demo;

import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class Test8_GetHeadersAndCookies {

//    @Test
    public void testResponseHeaders(){
        Response response = get("https://jsonplaceholder.typicode.com/photos");
        //Get single header
        String headerCachecontrol = response.getHeader("cache-control");
        System.out.println(headerCachecontrol);

        //Get all headers
        Headers headers = response.getHeaders();
        for(Header h: headers){
            System.out.println(h.getName() + ":" + h.getValue());
        }
    }
//    @Test
    public void testCookies(){
        Response response = get("https://jsonplaceholder.typicode.com/photos");
        Map<String, String> cookies = response.getCookies();
        for(Map.Entry<String, String> entry : cookies.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    @Test
    public void testDetailCookies() {
        Response response = get("https://jsonplaceholder.typicode.com/photos");
        Cookie a = response.getDetailedCookie("__cfduid");
        System.out.println(a.hasExpiryDate());
        System.out.println(a.getExpiryDate());
        System.out.println(a.getValue());
    }
}
