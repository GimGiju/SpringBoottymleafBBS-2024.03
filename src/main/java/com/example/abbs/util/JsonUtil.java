package com.example.abbs.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component          // 범위가 더 넓음
public class JsonUtil {

    public String list2Json(List<String> list){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("list", list);
        return jsonObj.toString();
    }

    public List<String> json2List(String jsonStr){
        JSONParser parser = new JSONParser();
        List<String> list = null;
        try {
            JSONObject jsonObj = (JSONObject) parser.parse(jsonStr); // json 오브젝트 만들어짐
            JSONArray jsonArr = (JSONArray) jsonObj.get("list");
            list = (List<String>) jsonArr;
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }



}
