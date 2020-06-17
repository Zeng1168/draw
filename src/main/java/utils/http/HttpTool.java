package utils.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;


public class HttpTool {
    // 设置配置请求参数
//    private static String baseUrl = "http://127.0.0.1:8086/";
    private static String baseUrl = "http://api.zeml.top:8086/";
    private static RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60000)    // 连接主机服务超时时间
            .setConnectionRequestTimeout(60000) // 请求超时时间
            .setSocketTimeout(120000)   // 数据读取超时时间
            .build();


    private static CookieStore cookieStore = null;  // 保持cookie

    private static MyResponse doCookieRequest(HttpUriRequest request){
        MyResponse response = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;

        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        // 如果没有cookie，先获取cookie
        if(cookieStore == null){
            try {
                CloseableHttpClient httpClient2 = httpClientBuilder.build();
                CloseableHttpResponse httpResponse2 = httpClient2.execute(request);
                setCookieStore(httpResponse2);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if(cookieStore != null) {
            try {
                httpClient = httpClientBuilder.setDefaultCookieStore(cookieStore).build();
                httpResponse = httpClient.execute(request);
                // 通过返回对象获取返回数据
                HttpEntity entity = httpResponse.getEntity();
                // 通过EntityUtils中的toString方法将结果转换为字符串
                String result = EntityUtils.toString(entity);

                // 解析字符串
                if (result != null && !result.equals("")) {
                    response = JSON.parseObject(result, MyResponse.class);
                } else {
                    response = MyResponse.failureResponse("网络连接异常！");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 释放资源
                if (httpClient != null) {
                    try {
                        httpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (httpResponse != null) {
                    try {
                        httpResponse.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else {
            response = MyResponse.errorResponse("未获取到cookie！");
        }

        return response;

    }

    public static void setCookieStore(CloseableHttpResponse httpResponse) {
        try {
            System.out.println("----setCookieStore");
            cookieStore = new BasicCookieStore();
            // JSESSIONID
            String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
            String JSESSIONID = setCookie.substring("JSESSIONID=".length(),
                    setCookie.indexOf(";"));
            System.out.println("JSESSIONID:" + JSESSIONID);
            // 新建一个Cookie
            BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",JSESSIONID);
            cookie.setVersion(0);
            cookieStore.addCookie(cookie);
            System.out.println("cookie设置成功！");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**  Get请求 无参数  **/
    public static MyResponse doGet(String url){
        return doGet(url, null);
    }

    /**  Get请求  **/
    public static MyResponse doGet(String url, Map<String, String> paramMap) {
        MyResponse response = null;

        // 把数据加到url中
        if(paramMap != null && paramMap.size() > 0){
            Set<Map.Entry<String, String>> entrySet = paramMap.entrySet();
            Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
            int i=0;
            while (iterator.hasNext()) {
                Map.Entry<String, String> mapEntry = iterator.next();
                url += (i == 0)?"?":"&";
                url += mapEntry.getKey();
                url += "=";
                url += mapEntry.getValue();
                i++;
            }
        }

        // 数据输出
        System.out.println(url);

        try{
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(baseUrl + url);
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);

            response = doCookieRequest(httpGet);
        }catch (Exception e){
            e.printStackTrace();
            response = MyResponse.errorResponse("Get请求异常！");
        }

        return  response;
    }


    /**  Post请求  **/
    public static MyResponse doPost(String url, Map<String, String> paramMap) {
        MyResponse response = null;
        UrlEncodedFormEntity data = null;

        // 数据输出
        System.out.println(url);
        if(paramMap != null){
            System.out.println(paramMap.toString());
        }

        // 数据封装
        if (paramMap != null && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 通过map集成entrySet方法获取entity
            Set<Map.Entry<String, String>> entrySet = paramMap.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue()));
            }
            try {
                data = new UrlEncodedFormEntity(nvps, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        try{
            // 创建httpPost远程连接实例
            HttpPost httpPost = new HttpPost(baseUrl + url);
            // 配置请求参数实例

            // 为httpPost实例设置配置
            httpPost.setConfig(requestConfig);
            // 设置请求头
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            // 封装post请求参数
            if (data != null) {
                httpPost.setEntity(data);
            }
            response = doCookieRequest(httpPost);
        }catch (Exception e){
            response = MyResponse.errorResponse("Post请求异常！");
            e.printStackTrace();
        }
        return response;
    }
}