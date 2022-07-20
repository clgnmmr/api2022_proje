package get_requests;

import base_urls.DummyRestApiUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyRestApiUrl {

    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */

    @Test
    public void get01(){
        //1.Step sewt the url
        spec.pathParam("first","employees");

        //2.Step :set the expected data
        //3.Step : send the get request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).when().get("/{first}");
        response.prettyPrint();
        //4.Step : Do Assertion
        response.then().assertThat().
                statusCode(200).//i) Status code is 200
                body("data.id",hasSize(24),// ii) There are 24 employees
                        "data.employee_name",
                        hasItems("Tiger Nixon","Garrett Winters"));//iii) "Tiger Nixon" and "Garrett Winters" are among the employees




        JsonPath json = response.jsonPath();

        List<Integer> ageList=json.getList("data.findAll{it.employee_age>0}.employee_age");//Groovy Language = Java temelli bir proglamlama dili
        System.out.println(ageList);
        Collections.sort(ageList);
        assertEquals(66,(int)ageList.get(ageList.size() - 1));//v) The greatest age is 66



        List<String> nameList=json.getList("data.findAll{it.employee_age==19}.employee_name");
        System.out.println(nameList);
        assertEquals("Tatyana Fitzpatrick",nameList.get(0));



        List<Integer> salaryList=json.getList("data.findAll{it.employee_age>0}.employee_salary");//Groovy Language = Java temelli bir proglamlama dili
       int toplam=0;
        for (Integer w:salaryList) {
            toplam+=w;
        }
        System.out.println(toplam);

        assertEquals(6644770,toplam);


    }


}
