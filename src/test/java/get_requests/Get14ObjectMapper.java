package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper  extends JsonPlaceHolderBaseUrl {
    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    @Test
    public void getObjectMapper01(){
        //1.Step :Set the url
        spec.pathParams("first","todos","second",198);


        // 2Step : set the expected data

        String expectData = "{\n" +
                "   \"userId\": 10,\n" +
                "  \"id\": 198,\n" +
                "   \"title\": \"quis eius est sint explicabo\",\n" +
                "   \"completed\": true\n" +
                "  }";
        HashMap<String,Object> expectedDataMap= JsonUtil.convertJsonToJavaObject(expectData, HashMap.class);

        JsonUtil.convertJavaObjectToJson(expectedDataMap);
        //3.Step : Send the get  request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");

        //4.Step : Do Assertion

        HashMap<String,Object> actualDataMap=JsonUtil.convertJsonToJavaObject(response.asString(),HashMap.class);
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));


    }

    @Test
    public void getPojo02(){
        //1.Step :Set the url
        spec.pathParams("first","todos","second",198);


        // 2Step : set the expected data

        String expectData = "{\n" +
                "   \"userId\": 10,\n" +
                "  \"id\": 198,\n" +
                "   \"title\": \"quis eius est sint explicabo\",\n" +
                "   \"completed\": true\n" +
                "  }";
        JsonPlaceHolderTestData jsonPlaceHolderTestData=new JsonPlaceHolderTestData();
        String expectedData2=jsonPlaceHolderTestData.expectedDataInString(10,"quis eius est sint explicabo",true);
        JsonPlaceHolderPojo expectedDataMap1= JsonUtil.convertJsonToJavaObject(expectedData2, JsonPlaceHolderPojo.class);
       // JsonPlaceHolderPojo expectedDataMap= JsonUtil.convertJsonToJavaObject(expectData, JsonPlaceHolderPojo.class);

       // JsonUtil.convertJavaObjectToJson(expectedDataMap);
        //3.Step : Send the get  request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");

        //4.Step : Do Assertion

        JsonPlaceHolderPojo actualDataMap=JsonUtil.convertJsonToJavaObject(response.asString(), JsonPlaceHolderPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataMap1.getUserId(),actualDataMap.getUserId());
        assertEquals(expectedDataMap1.getTitle(),actualDataMap.getTitle());
        assertEquals(expectedDataMap1.getCompleted(),actualDataMap.getCompleted());



    }
}
