package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12Pojo extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/19
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                       {
                   "firstname": "Sally",
                   "lastname": "Brown",
                   "totalprice": 111,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2013-02-23",
                       "checkout": "2014-10-23"
                   },
                   "additionalneeds": "Breakfast"
                   }
     */
    @Test
    public void getPojo01(){
        //1.Step= set the url

        spec.pathParams("first","booking","second",19);
        //2.Step : set the excepted data
        BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2013-02-23","2014-10-23");
        BookingPojo bookingPojo=new BookingPojo("Sally","Brown",111,true,bookingDatesPojo,"Breakfast");


        //3.Step Send the get request and get the respıonse
        Response response=given().spec(spec).when().get("/{first}/{second}");

        //4.Step :Do Assertion

        BookingPojo actualPojo=response.as(BookingPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(bookingPojo.getFirstname(),actualPojo.getFirstname());
        assertEquals(bookingPojo.getLastname(),actualPojo.getLastname());
        assertEquals(bookingPojo.getTotalprice(),actualPojo.getTotalprice());
        assertEquals(bookingPojo.getDepositpaid(),actualPojo.getDepositpaid());
        assertEquals(bookingPojo.getAdditionalneeds(),actualPojo.getAdditionalneeds());

        assertEquals(bookingPojo.getBookingdates().getCheckin(),actualPojo.getBookingdates().getCheckin());
        assertEquals(bookingPojo.getBookingdates().getCheckout(),actualPojo.getBookingdates().getCheckout());

    }



}
