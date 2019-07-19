package com.louie.project.apitestScript.loginApi;

import com.alibaba.fastjson.JSONObject;
import com.louie.project.constant.LoginConstant;
import com.louie.project.constant.ProjectConstant;
import com.louie.system.base.allureReport.AllureCustom;
import com.louie.system.base.constant.Constant;
import com.louie.system.base.utils.HttpClientUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Feature("登录功能接口测试")
@Story("登录接口")
public class LoginApiTest {
    private String url;
    private HttpClientUtil httpClientUtil;
    private AllureCustom allureCustom;

    @BeforeTest
    public void BeforeTest(){
        httpClientUtil=new HttpClientUtil();
        allureCustom=new AllureCustom();
        url=ProjectConstant.URL +"/out/test/api/";
    }

    @Test(description = "账号与密码正确登录")
    public void test_001() throws IOException {
        //请求参数
        Map<String,String> map=new HashMap<String, String>();
        map.put("account", LoginConstant.ACCOUNT);
        map.put("password",LoginConstant.PASSWORD);
        String body=JSONObject.toJSONString(map);
        //请求返回结果
        String result= httpClientUtil.doPost(url,map);

        //组装Allure报告请求报文、返回结果
        allureCustom.AllureCustomReport(url,body,result);

        //请求结果返回验证
        JSONObject jsonObj = JSONObject.parseObject(result);
        String code=jsonObj.getString("code");
        String msg=jsonObj.getString("msg");

        //验证信息，调用自定义的验证，方便生成报告信息
        allureCustom.allureAssert(code,LoginConstant.CODECORRECT,"验证code返回值为200");
        allureCustom.allureAssert(msg,LoginConstant.CODECORRECTMSG,"验证msg返回值为successful");
    }

    @Test(description = "账号正确，密码错误登录")
    public void test_002() throws Exception {
        //请求参数
        Map<String,String> map=new HashMap<String, String>();
        map.put("account", LoginConstant.ACCOUNT);
        map.put("password", Constant.RANDOMSTR);
        String body=JSONObject.toJSONString(map);
        //请求返回结果
        String result= httpClientUtil.doPost(url,map);

        //组装Allure报告
        allureCustom.AllureCustomReport(url,body,result);

        //请求结果返回验证
        JSONObject jsonObj = JSONObject.parseObject(result);
        String code=jsonObj.getString("code");
        String msg=jsonObj.getString("msg");

        //验证信息，调用自定义的验证，方便生成报告信息
        allureCustom.allureAssert(code,LoginConstant.CODEERROR,"验证code返回值为 400");
        allureCustom.allureAssert(msg,LoginConstant.CODEERRORMSG,"验证msg返回值为 account or password are error");
    }


    @Test(description = "账号错误，密码正确登录")
    public void test_003() throws Exception {
        //请求参数
        Map<String,String> map=new HashMap<String, String>();
        map.put("account", Constant.RANDOMSTR);
        map.put("password", LoginConstant.PASSWORD);
        String body=JSONObject.toJSONString(map);
        //请求返回结果
        String result= httpClientUtil.doPost(url,map);

        //组装Allure报告
        allureCustom.AllureCustomReport(url,body,result);

        //请求结果返回验证
        JSONObject jsonObj = JSONObject.parseObject(result);
        String code=jsonObj.getString("code");
        String msg=jsonObj.getString("msg");

        //验证信息，调用自定义的验证，方便生成报告信息
        allureCustom.allureAssert(code,LoginConstant.CODEERROR,"验证code返回值为 400");
        allureCustom.allureAssert(msg,LoginConstant.CODEERRORMSG,"验证msg返回值为 account or password are error");
    }

    @Test(description = "账号与密码都错误登录")
    public void test_004() throws Exception {
        //请求参数
        Map<String,String> map=new HashMap<String, String>();
        map.put("account", Constant.RANDOMSTR);
        map.put("password", LoginConstant.PASSWORD);
        String body=JSONObject.toJSONString(map);
        //请求返回结果
        String result= httpClientUtil.doPost(url,map);

        //组装Allure报告
        allureCustom.AllureCustomReport(url,body,result);

        //请求结果返回验证
        JSONObject jsonObj = JSONObject.parseObject(result);
        String code=jsonObj.getString("code");
        String msg=jsonObj.getString("msg");

        //验证信息，调用自定义的验证，方便生成报告信息
        allureCustom.allureAssert(code,LoginConstant.CODEERROR,"验证code返回值为 400");
        allureCustom.allureAssert(msg,LoginConstant.CODEERRORMSG,"验证msg返回值为 account or password are error");
    }

    @Test(description = "账号与密码都为空登录")
    public void test_005() throws Exception {
        //请求参数
        Map<String,String> map=new HashMap<String, String>();
        map.put("account", Constant.EMPTY);
        map.put("password", Constant.EMPTY);
        String body=JSONObject.toJSONString(map);
        //请求返回结果
        String result= httpClientUtil.doPost(url,map);

        //组装Allure报告
        allureCustom.AllureCustomReport(url,body,result);

        //请求结果返回验证
        JSONObject jsonObj = JSONObject.parseObject(result);
        String code=jsonObj.getString("code");
        String msg=jsonObj.getString("msg");

        //验证信息，调用自定义的验证，方便生成报告信息
        allureCustom.allureAssert(code,LoginConstant.CODEEMPTY,"验证code返回值为 401");
        allureCustom.allureAssert(msg,LoginConstant.CODEEMPTYMSG,"验证msg返回值为 account or password are not empty");
    }

    @Test(description = "账号过长，密码正确登录")
    public void test_006() throws Exception {
        //请求参数
        Map<String,String> map=new HashMap<String, String>();
        map.put("account", Constant.TOOLONGSTR);
        map.put("password", LoginConstant.PASSWORD);
        String body=JSONObject.toJSONString(map);
        //请求返回结果
        String result= httpClientUtil.doPost(url,map);

        //组装Allure报告
        allureCustom.AllureCustomReport(url,body,result);

        //请求结果返回验证
        JSONObject jsonObj = JSONObject.parseObject(result);
        String code=jsonObj.getString("code");
        String msg=jsonObj.getString("msg");

        //验证信息，调用自定义的验证，方便生成报告信息
        allureCustom.allureAssert(code,LoginConstant.CODEERROR,"验证code返回值为 401");
        allureCustom.allureAssert(msg,LoginConstant.CODECORRECTMSG,"验证msg返回值为 account or password are error");
    }



}
