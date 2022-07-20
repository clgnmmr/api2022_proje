package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Wash the dishes"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									    }
     */
    @Test
    public void patch01(){
        //1.Step : set the url
        spec.pathParams("first","todos","second",198);
        //2.Syep set the expected data
        JsonPlaceHolderTestData requestBody=new JsonPlaceHolderTestData();
        Map<String,Object> expectedData= requestBody.expectedDataWithMissingKeys(null,"Wash the dishes56898",null);

        // 3. Step : Sent the patch request and get tehe resaponse
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");

        response.prettyPrint();

        // 4.Step : Do Assertion
        Map<String,Object> actualDataMap=response.as(HashMap.class);

        response.then().assertThat().statusCode(200).body("title",equalTo(expectedData.get("title")));


        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("title"),actualDataMap.get("title"));
        assertEquals(expectedData.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedData.get("completed"),actualDataMap.get("completed"));


    }
}
