package com.portal;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
    public static final ObjectMapper mapper = new ObjectMapper();

    /*
    * 将对象转换成json字符串
    * */
    public static String toString(Object obj){
        if (obj == null){
            return null;
        }
        if (obj.getClass() == String.class){
            return (String) obj;
        }
        try{
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    *将json转换成对象
    * */
    public static <T> T toBean(String json,Class<T> tClass){
        try {
            return mapper.readValue(json,tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
