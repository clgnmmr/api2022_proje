package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;
import test_data.HerOkuAppTestData;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper  extends HerOkuAppBaseUrl {
    /*
    Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
               {
    "firstname": "Oliver",
    "lastname": "Smith",
    "totalprice": 100,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-07-18",
        "checkout": "2022-07-19"
    },
    "additionalneeds": "Breakfast"
}
     */
    @Test
    public void get01(){
        //1.Step : set the url
        spec.pathParams("first","booking","second",22);


        //2.Step :set the expedcted data
        HerOkuAppTestData herOkuAppTestData=new HerOkuAppTestData();
        String expectedData= herOkuAppTestData.expectedDataInString("Oliver","Smith",100,true,"2022-07-18","2022-07-19","Breakfast");

        BookingPojo expectedDataBody=JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);
        //3.Step : Sent the get request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");

        //4.Step : Do Assertion
        BookingPojo actualDataBody=JsonUtil.convertJsonToJavaObject(response.asString(),BookingPojo.class);


        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataBody.getFirstname(),actualDataBody.getFirstname());
        assertEquals(expectedDataBody.getLastname(),actualDataBody.getLastname());
        assertEquals(expectedDataBody.getTotalprice(),actualDataBody.getTotalprice());
        assertEquals(expectedDataBody.getDepositpaid(),actualDataBody.getDepositpaid());
        assertEquals(expectedDataBody.getAdditionalneeds(),actualDataBody.getAdditionalneeds());
        assertEquals(expectedDataBody.getBookingdates().getCheckin(),actualDataBody.getBookingdates().getCheckin());
        assertEquals(expectedDataBody.getBookingdates().getCheckout(),actualDataBody.getBookingdates().getCheckout());

        // soft assert
        //1.Adım Softassert objesi oluşturma

        SoftAssert softAssert=new SoftAssert();

        //2.assertion yap
        softAssert.assertEquals(200,response.getStatusCode());
        softAssert.assertEquals(expectedDataBody.getFirstname(),actualDataBody.getFirstname());
        softAssert.assertEquals(expectedDataBody.getLastname(),actualDataBody.getLastname());
        softAssert.assertEquals(expectedDataBody.getTotalprice(),actualDataBody.getTotalprice());
        softAssert.assertEquals(expectedDataBody.getDepositpaid(),actualDataBody.getDepositpaid());
        softAssert.assertEquals(expectedDataBody.getAdditionalneeds(),actualDataBody.getAdditionalneeds());
        softAssert.assertEquals(expectedDataBody.getBookingdates().getCheckin(),actualDataBody.getBookingdates().getCheckin());
        softAssert.assertEquals(expectedDataBody.getBookingdates().getCheckout(),actualDataBody.getBookingdates().getCheckout());

        //3.SoftassertAll methodunu çalıştır
        softAssert.assertAll();
    }



}
