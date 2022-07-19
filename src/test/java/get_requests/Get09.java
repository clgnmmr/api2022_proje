package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;


public class Get09 extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking/91
        When
            I send GET Request to the url
        Then
            Response body should be like that;
      {
        "firstname": "James",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
      }
     */
@Test
    public void get01(){
    //1.Step : set the url
    spec.pathParams("first","booking","second",3750);

    //2.step: ste the expected data

    Map<String,String> bookingdateMap=new HashMap<>();
    bookingdateMap.put("checkin","2018-01-01");
    bookingdateMap.put("checkout","2019-01-01");


    Map<String,Object> expectedData=new HashMap<>();
    expectedData.put("firstname", "James");
    expectedData.put("lastname", "Brown");
    expectedData.put("totalprice", 111);
    expectedData.put("depositpaid", true);
    expectedData.put("bookingdates",bookingdateMap);
    expectedData.put( "additionalneeds", "Breakfast");
    //3.Step : Send the request amd het the response

    Response response=given().spec(spec).when().get("/{first}/{second}");

    Map<String,Object> actualData=response.as(HashMap.class);

    //4.step : Do Assertion

    assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
    assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
    assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
    assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
    assertEquals(bookingdateMap.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
    assertEquals(bookingdateMap.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));











}
}
