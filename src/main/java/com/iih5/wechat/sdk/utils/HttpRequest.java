package com.iih5.wechat.sdk.utils;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/** 
 * ---------------------------------------------------------------------------   
 * 类名称   ： HttpRequest.java
 * 类描述   ： Http请求接口
 * 创建人   ： xue.yi 
 * 创建时间： 2015年7月4日 下午12:20:11     
 * 版权拥有：
 * --------------------------------------------------------------------------- 
 */

public class HttpRequest {
    /**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            int TIMEOUT = 15000;
            StringBuffer params = new StringBuffer();;
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("发送GET请求出现异常！" + e);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    /**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet2(String url,Map<String,String> property) {
        String result = "";
        BufferedReader in = null;
        try {
            int TIMEOUT = 15000;
            StringBuffer params = new StringBuffer();;
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            for (String key:property.keySet()) {
                connection.setRequestProperty(key,property.get(key));
            }
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("发送GET请求出现异常！" + e);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	/**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @param hm 请求参数
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, Map<String, Object> hm) {
        String result = "";
        BufferedReader in = null;
        try {
        	int TIMEOUT = 15000;
        	StringBuffer params = new StringBuffer();
        	if (hm!=null) {
        		for (Iterator<?> iter = hm.entrySet().iterator(); iter.hasNext();){
        			Entry<?, ?> element = (Entry<?, ?>) iter.next();
        			params.append(element.getKey().toString());
        			params.append("=");
        			params.append(URLEncoder.encode(element.getValue().toString(),"UTF-8"));
        			params.append("&");
        		}	
        	}
        	String urlNameString = url + "?" + params;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("发送GET请求出现异常！" + e);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param obj 请求参数
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url,Object obj) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            String tmp="";
            if (!(obj instanceof String)){
                tmp=JSON.toJSONString(obj);
            }else {
                tmp=String.valueOf(obj);
            }
            out.print(tmp);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送 POST 请求出现异常！"+e);
        }
        finally{
            try{
                if(out!=null) out.close();
                if(in!=null) in.close();
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param obj 请求参数
     * @param property
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost2(String url,Object obj,Map<String,String> property) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            for (String key:property.keySet()) {
                conn.setRequestProperty(key,property.get(key));
            }
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            String tmp="";
            if (!(obj instanceof String)){
                tmp=JSON.toJSONString(obj);
            }else {
                tmp=String.valueOf(obj);
            }
            out.print(tmp);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送 POST 请求出现异常！"+e);
        }
        finally{
            try{
                if(out!=null) out.close();
                if(in!=null) in.close();
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param obj 请求参数
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url,Object obj,Map<String,Object> headers) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            Set<Entry<String,Object>> set= headers.entrySet();
            for (Entry<String,Object> entry:set ){
                conn.setRequestProperty(entry.getKey(),String.valueOf(entry.getValue()));
            }
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            String tmp="";
            if (!(obj instanceof String)){
                tmp=JSON.toJSONString(obj);
            }else {
                tmp=String.valueOf(obj);
            }
            out.print(tmp);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送 POST 请求出现异常！"+e);
        }
        finally{
            try{
                if(out!=null) out.close();
                if(in!=null) in.close();
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

}
