import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Get01 {

    /*
    1)postman manuel API testi icin kullanilir
    2)API  otomasyon testi icin Rest-Assured library kullanıyoruz.
    3) otomasyon kodalrının yazımı icin şu adımalrı izliyoruz
       a)Gereksinimleri anlama
       b)Testcase'i yazma
         i)Test case yazımı için 'Gherkin Language' kullanıyoruz
            x) Given : ön koşullar icin
            y) When : Aksiyonlar
            z) Then : Dönütler --> dogrulama,response, . . .
            t) And : çoklu
       c)Testing kodunun yazımı
           i) set the Url --> url eklenmesi
           ii) set the expected data (Post-Put-Patch)
           iii) Type code to send request
           iv) Do assertion

     */

    /*
    Given
            https://restful-booker.herokuapp.com/booking/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK

     */
    @Test
    public  void get01(){
        String url=" https://restful-booker.herokuapp.com/booking/555";


        Response response=given().when().get(url);
       //response.prettyPrint();
       response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        System.out.println("statusCode :"+response.statusCode());


    }


}
