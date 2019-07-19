

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;


public class GeoCodingApi {

    // 工具类, 只提供静态方法, 所以私有化构造
    private GeoCodingApi(){}
    // 记录日志
    private static Logger logger = LoggerFactory.getLogger(GeoCodingApi.class);

    // 对Map内所有value作utf8编码，拼接返回结果
    private static String toQueryString(Map<?, ?> data) throws UnsupportedEncodingException {
        StringBuffer queryString = new StringBuffer();
        for (Map.Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(),"UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        logger.info("udf转码后的内容为: " + queryString.toString());
        return queryString.toString();
    }



    /**
     * 核心方法
     * 根据经纬度返回商业兴趣标签
     * @param latAndLng 纬度,经度
     * @return
     */
    public static String bussinessTagFromBaidu(String latAndLng){
        // 最终需要返回的标签
        StringBuffer businessTags = new StringBuffer();
        HttpClient httpClient = new HttpClient();
        GetMethod method = null;
        try {
            Map paramsMap = new LinkedHashMap<String, String>();
            // 计算sn跟参数对出现顺序有关，get请求请使用LinkedHashMap保存<key,value>，该方法根据key的插入顺序排序；
            // post请使用TreeMap保存<key,value>，该方法会自动将key按照字母a-z顺序排序。
            // 所以get请求可自定义参数顺序（sn参数必须在最后）发送请求，但是post请求必须按照字母a-z顺序填充body（sn参数必须在最后）。
            // 以get请求为例：
            // http://api.map.baidu.com/geocoder/v2/?address=百度大厦&output=json&ak=yourak，paramsMap中先放入address，再放output，然后放ak，放入顺序必须跟get请求中对应参数的出现顺序保持一致。
            paramsMap.put("callback", "renderReverse");
            paramsMap.put("location", latAndLng); //纬度, 经度47.008052,130.723014
            paramsMap.put("output", "json");
            paramsMap.put("pois", "1"); //是否显示指定位置周边的poi，0为不显示，1为显示。当值为1时，默认显示周边1000米内的poi。
            // paramsMap.put("radius", "500"); // poi召回半径，允许设置区间为0-1000米，超过1000米按1000米召回。
            paramsMap.put("ak", "4TstSBSFXzsKRdzvI4PzbZ0YZRyvi7Ny");
            String paramsStr = toQueryString(paramsMap);

            String finalURL = "http://api.map.baidu.com/reverse_geocoding/v3/?"+paramsStr;

            logger.info("生成的url为: " + finalURL);

            method = new GetMethod(finalURL);
            int i = httpClient.executeMethod(method);
            if (i == 200) {
                String bodyAsString = method.getResponseBodyAsString();


                // 带回调函数只留json串
                if (!bodyAsString.startsWith("{")) {
                    bodyAsString = bodyAsString.substring(29, bodyAsString.length() - 1);
                }

                System.out.println(bodyAsString);

                // json 解析结果数据, 解析出来bussiness
                JSONObject parseObject = JSONObject.parseObject(bodyAsString);
                int status = parseObject.getIntValue("status");


                // 说明成功查询到信息
                if (status == 0) {

                    JSONObject result = parseObject.getJSONObject("result");
                    businessTags.append(result.getString("business"));

                    // 如果没找到商圈信息
                    if (businessTags.length() == 0) {
                        // 就找周围的poi
                        JSONArray jsonArray = result.getJSONArray("pois");
                        // 如果pois存在, 就取第一个pois的tag
                        if (jsonArray.size() > 0) {
                            System.out.println("周边不为空");
                            JSONObject object = jsonArray.getJSONObject(0);
                            businessTags.append(object.getString("tag"));
                        }
                    }
                }

            }
        } catch (Exception e) {
            logger.error(e.fillInStackTrace() + e.getMessage());
        } finally {
            if (null!= method) method.releaseConnection();
        }
        return businessTags.toString();
    }

    //"47.008052,130.723014"
    public static void main(String[] args) {
        System.out.println(bussinessTagFromBaidu("40.0020912082,116.4872588088"));

    }
}

