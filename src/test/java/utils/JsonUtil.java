package utils;


import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

   private static ObjectMapper mapper;

   static {
      mapper=new ObjectMapper();
   }

   //1. method : json datasını java objesine çevirir (De - Serialization)

    public static    <T> T  convertJsonToJavaObject(String json,Class<T> cls){//Generic Method
       T javaResult=null;
        try {
          javaResult=  mapper.readValue(json,cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return javaResult;
    }

  // 2.Method: java objesini json data cevirir(serialization)

    public static    String  convertJavaObjectToJson(Object obj){//Generic Method
        String jsonResult=null;
        try {
            jsonResult=  mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResult;
    }



}
