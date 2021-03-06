package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {

    //De-Serialization: JSON formatından Java objesine çevirme.
    //Serialization: Java objesini JSON formatına çevirme.
    // De-Serialization ve Serialization iki türlü yapılır:
    //Gson: Google tarafından üretiliştir.
    //Object Mapper: Daha popüler ***

     /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

    @Test
    public void get01(){
        // 1.step : set the Url
        spec.pathParams("first","todos","second",2);
        // 2.step: set the b expected data

        Map<String,Object> expectedData=new HashMap<>();
        // id konulmaz uniq olduğu için karmaşa olmaması için
        expectedData.put("userId",1);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        expectedData.put("StatusCode",200);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");

        //3.Step: Send the request amd get the response

        Response response=given().spec(spec).when().get("/{first}/{second}");


        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println(actualData);

        //4.Step : Do Assertion

        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("StatusCode"),response.getStatusCode());
        assertEquals(expectedData.get("Via"),response.header("Via"));
        assertEquals(expectedData.get("Server"),response.header("Server"));




    }
    @Test
    public void get02() {
        // 1.step : set the Url
        spec.pathParams("first", "todos", "second", 2);

        // 2.step: set the b expected data

        JsonPlaceHolderTestData expectedDate=new JsonPlaceHolderTestData();

        Map<String,Object> expectedDateMap=expectedDate.expectedDataWithAllKeys(1,"quis ut nam facilis et officia qui",false);
        expectedDateMap.put("StatusCode",200);
        expectedDateMap.put("Via","1.1 vegur");
        expectedDateMap.put("Server","cloudflare");
        //3.Step: Send the request amd get the response

        Response response=given().spec(spec).when().get("/{first}/{second}");


        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println(actualData);
        //4.Step : Do Assertion
        assertEquals(expectedDateMap.get("userId"),actualData.get("userId"));
        assertEquals(expectedDateMap.get("title"),actualData.get("title"));
        assertEquals(expectedDateMap.get("completed"),actualData.get("completed"));
        assertEquals(expectedDateMap.get("StatusCode"),response.getStatusCode());
        assertEquals(expectedDateMap.get("Via"),response.header("Via"));
        assertEquals(expectedDateMap.get("Server"),response.header("Server"));


    }

}
