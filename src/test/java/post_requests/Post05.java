package post_requests;

import base_urls.DummyRestApiUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyDataPojo;
import pojos.DummyPojo;
import test_data.DummyTestData;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05 extends DummyRestApiUrl {
    /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body: {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language


       Given
       https://dummy.restapiexample.com/api/v1/create
        Request body: {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       When
       HTTP Request Method: POST Request


       Then
       Status code is 200
       And
       Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }



       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

    @Test
    public  void post01(){
        //1.Step : Set the url
        spec.pathParam("first","create");
        //2.Step : Set the expected data

        DummyDataPojo expectedData=new DummyDataPojo("Tom Hanks",111111,23,"Perfect image");
        DummyPojo expectedDataMap=new DummyPojo("success",expectedData,"Successfully! Record has been added.");


        //3.Step : Send the post request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");

        //4.Step :Do Assertion

        DummyPojo actualData=JsonUtil.convertJsonToJavaObject(response.asString(),DummyPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataMap.getMessage(),actualData.getMessage());
        assertEquals(expectedDataMap.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(),actualData.getData().getProfile_image());







    }



}
