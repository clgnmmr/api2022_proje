package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Post03Pojo extends JsonPlaceHolderBaseUrl {

 /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post01(){
        //1.Step : Set the url
        spec.pathParam("first","todos");

        //2.Step : Set the excepted data
        JsonPlaceHolderPojo requestBody=new JsonPlaceHolderPojo(55,"Tidy your room",false);


        //3.Step : Send the post request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");

       JsonPlaceHolderPojo actualData= response.as(JsonPlaceHolderPojo.class);
        // 4.Step : Do Assertion

        response.then().assertThat().statusCode(201);
        assertEquals(requestBody.getUserId(),actualData.getUserId());
        assertEquals(requestBody.getTitle(),actualData.getTitle());
        assertEquals(requestBody.getCompleted(),actualData.getCompleted());

        assertEquals(requestBody.toString(),actualData.toString());











    }





}
