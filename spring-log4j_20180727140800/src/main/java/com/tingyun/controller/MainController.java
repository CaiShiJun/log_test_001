package com.tingyun.controller;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tingyun on 2017/8/14.
 */
@Controller
public class MainController {

    //private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(MainController.class);      //log4j 1.x.x 版本
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MainController.class);   //log4j 2.x 版本

    @RequestMapping("/")
    public String index() {
        LOGGER.info("hello,world");
        return "index";
    }

    @RequestMapping("/info")
    @ResponseBody
    public String info(HttpServletRequest request, HttpServletResponse response) {
        System.err.println("this ia a hello test-----------------------------");
        //LOGGER.trace("====== trace");
        LOGGER.debug("====== debug");
        LOGGER.info("====== info");
        LOGGER.warn("====== warn");
        LOGGER.error("====== error");

        //跨应用
        String url =request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath()+"/hello";
        HttpClient httpClient=new DefaultHttpClient();
        HttpGet get=new HttpGet(url);
        HttpResponse httpResponse=null;
        try {
            httpResponse=httpClient.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int statusCode=httpResponse.getStatusLine().getStatusCode();
        if(statusCode==200){
            return LOGGER.toString();
        }else{
            return String.valueOf(statusCode);
        }
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String  hello(){
        //LOGGER.trace("trace====== trace");
        LOGGER.debug("debug====== debug");
        LOGGER.info("info====== info");
        LOGGER.warn("warn====== warn");
        LOGGER.error("error====== error");
        System.err.println("this ia a hello test");
        System.out.println("this ia a hello test");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }

}
