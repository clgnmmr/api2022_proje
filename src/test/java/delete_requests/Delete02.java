package delete_requests;

import base_urls.DummyRestApiUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyDataPojo;
import pojos.DummyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Delete02 extends DummyRestApiUrl {
    /*
    URL: https://dummy.restapiexample.com/api/v1/delete/2
   HTTP Request Method: DELETE Request
   Test Case: Type by using Gherkin Language
   Given
   https://dummy.restapiexample.com/api/v1/delete/2
   When
   HTTP Request Method: DELETE Request
   Then
   Status code is 200
   And
   "status" is "success"
   And
   "data" is "2"
   And
   "message" is "Successfully! Record has been deleted"

   Assert:     i) Status code is 200
           ii) "status" is "success"
          iii) "data" is "2"
           iv) "message" is "Successfully! Record has been deleted"
 */
    @Test
    public void  delete01(){
        spec.pathParams("first","delete","second",2);
        DummyDataPojo dummyDataPojo=new DummyDataPojo();
        DummyPojo expectedData=new DummyPojo("success",dummyDataPojo,"Successfully! Record has been deleted");

        Response response=given().spec(spec).contentType(ContentType.JSON).body(dummyDataPojo).when().delete("/{first}/{second}");
        response.prettyPrint();


        response.then().assertThat().statusCode(200).
       body("status",equalTo("success"),"data",equalTo("2"),"message",equalTo("Successfully! Record has been deleted"));




    }
}
