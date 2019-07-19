package test;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http.HttpUtil;

import java.util.ArrayList;
import java.util.List;


public class Map_baidu {
//    public static String getCity(String lat, String lng) {
//        JSONObject obj = getLocationInfo(lat, lng).getJSONObject("result").getJSONObject("addressComponent");
//        return obj.getString("city");
//      }

//      public static JSONObject getLocationInfo(String lat, String lng) {
//        String url = "http://api.map.baidu.com/geocoder/v2/?location=" + lat + ","
//            + lng + "&output=json&ak=4TstSBSFXzsKRdzvI4PzbZ0YZRyvi7Ny&pois=0";
//        JSONObject obj = JSONObject.fromObject(HttpUtil.getRequest(url));
//        return obj;
//      }

//      public static void main(String[] args) {
////        System.out.println(LngAndLatUtil.getLocationInfo("30.00", "114"));
//      }


    public static void main(String[] args) {
//        System.out.println(Map_baidu.getLocationInfo("30.00", "114"));
//        String jstr= "{\"name\":1,\"age\":2,\"friends\":{\"tercher\":\"laowang\",\"mate\":\"xiaowang\"}";
        List<Person> list =new ArrayList<Person>();
        list.add(new Person("zhangsan","1"));
        list.add(new Person("xiaozhang","2"));

//        //str ->jsonobject
//        JSONObject jo = JSON.parseObject(jstr);
//
//        //JO->Str
//       String js = JSON.toJSONString(jo);

        //jo->Person
//        Person p = JSON.parseObject(jstr,Person.class);
//        System.out.println(p.age);
    }
}

class Person{
    String name;
    String age;
//    JSONObject friends;
    Person(String name,String age){
        this.name = name;
        this.age =age;
    }
}