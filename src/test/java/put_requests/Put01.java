package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {

     /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */

    @Test
    public void put01(){
        //1.Step : set the url
        spec.pathParams("first","todos","second",198);
        //2.Syep set the expected data
        JsonPlaceHolderTestData jsonPlaceHolderTestData=new JsonPlaceHolderTestData();
        Map<String,Object>expecteedData=jsonPlaceHolderTestData.expectedDataWithAllKeys(21,"Wash the dishes",false);

        //3.Step : Send Put request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expecteedData).when().put("/{first}/{second}");
        response.prettyPrint();

        // 4. Step : Do Assertion
        Map<String,Object> actualData=response.as(HashMap.class);

        assertEquals(200,response.statusCode());
        assertEquals(expecteedData.get("userId"),actualData.get("userId"));
        assertEquals(expecteedData.get("title"),actualData.get("title"));
        assertEquals(expecteedData.get("completed"),actualData.get("completed"));




    }
}
