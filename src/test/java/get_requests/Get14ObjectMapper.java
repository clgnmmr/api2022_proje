package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import org.junit.Test;
import utils.JsonUtil;

import java.util.HashMap;

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
        JsonUtil.convertJsonToJavaObject(expectData, HashMap.class);







    }
}
