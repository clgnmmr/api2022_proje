package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */
    @Test
    public void delete01(){

        // 1.Step: Set the url
        spec.pathParams("first","todos","second",198);

        // 2.Step: set the excepted data
        Map<String,Object> exceptedData=new HashMap<>();

        //3. Step : Send the delete request and get the response

        Response response=given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //4.Step : Do Assertion
        //1.Yol
        Map<String ,Object>actualMap=response.as(HashMap.class);
        response.then().assertThat().statusCode(200);

        assertEquals(exceptedData,actualMap);


        //2.Yol
        assertTrue(actualMap.size()==0);
        assertTrue(actualMap.isEmpty());// tavsiye edilen

        //Delete request yapmadan once post request yapıp bu datayı silmeliyiz
        // cunku vbaşakalarının bilgilerini silmiş oluruz
    }
}
