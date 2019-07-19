package com.louie.system.base.allureReport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.qameta.allure.Attachment;
import org.testng.Assert;

import java.util.Map;

//自定义Allure输出报告模板
public class AllureCustom {

    public  void AllureCustomReport(String URL, String Body,String Respond){
        requestBody(URL,Body);
        respondBody(Respond);
        debugBody(URL,Body);
    }


    @Attachment("请求报文")
    public  String requestBody(String URL, String body) {
        //格式化json串
        boolean prettyFormat = true; //格式化输出
        JSONObject jsonObject = JSONObject.parseObject(body);
        String str = JSONObject.toJSONString(jsonObject,prettyFormat);

        //报告展现请求报文
        return URL+"\n"+str;
    }

    @Attachment("响应报文")
    public  String respondBody(String respond) {

        //格式化json串
        boolean prettyFormat = true; //格式化输出
        JSONObject jsonObject = JSONObject.parseObject(respond);
        String str = JSONObject.toJSONString(jsonObject,prettyFormat);

        //报告展现响应报文
        return str;
    }

    @Attachment("调试报文")
    public  String debugBody(String URL, String body) {
        JSONObject jsonObject = JSON.parseObject(body);
        URL=URL+"?";
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            URL+=entry.getKey()+"="+entry.getValue()+"&";
        }
        URL = URL.substring(0,URL.length() - 1);
        return URL;
    }


    @Attachment("验证信息")
    public  String allureAssert(String actual,String expect,String msg) {
        Assert.assertEquals(actual,expect);
        String assertMsg="";
        assertMsg="验证描述:"+msg+"\n";
        assertMsg+="实际结果:"+actual+"\n";
        assertMsg+="预期结果"+expect+"\n";
        //报告展现响应报文
        return assertMsg;
    }



}
