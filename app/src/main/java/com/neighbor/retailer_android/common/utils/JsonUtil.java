package com.neighbor.retailer_android.common.utils;


import android.util.Log;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 */
public class JsonUtil {

    /**
     * Object to Json
     */
    public static String objToJson(Object obj) {
        String str;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            str = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            str = null;
            e.printStackTrace();
            Log.i("JsonUtil", "Obj to jsonStr  出错！");
        }
        return str;
    }

    /**
     * jsonString ---> List<Info>
     *
     * @param jsonStr
     * @param elementClasses             Info.class
     * @return List<Info>
     */
    public static <T> List<T> jsonToList(String jsonStr, Class<?>... elementClasses){
        ObjectMapper mapper = new ObjectMapper();

        JavaType javaType = mapper.getTypeFactory().constructParametricType(
                ArrayList.class, elementClasses);

        List<T> list = new ArrayList<T>();
        try {
            list = mapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            list=null;
            e.printStackTrace();
            Log.i("JsonUtil", "解析成list时出错！");
        }
        return list;
    }

    /**
     * jsonString to Obj
     *
     * @param jsonStr
     * @param collectionClass      Obj.class
     * @return
     */
    public static <T> T jsonToObj(String jsonStr, Class<T> collectionClass){
        ObjectMapper mapper = new ObjectMapper();
        T obj;
        try {
            obj =mapper.readValue(jsonStr, collectionClass);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("JsonUtil", "解析成obj时出错！");
            obj = null;
        }
        return obj;

    }

    public static Map<String,Object> jsonToMap(String jsonStr){
        return jsonToObj(jsonStr, Map.class);
    }

}
