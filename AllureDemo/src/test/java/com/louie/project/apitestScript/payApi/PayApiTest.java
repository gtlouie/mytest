package com.louie.project.apitestScript.payApi;

import com.alibaba.fastjson.JSONObject;
import com.louie.project.constant.LoginConstant;
import com.louie.project.constant.ProjectConstant;
import com.louie.system.base.allureReport.AllureCustom;
import com.louie.system.base.utils.HttpClientUtil;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Feature("支付功能接口测试")
@Story("支付接口")
public class PayApiTest {
    private String url;
    private HttpClientUtil httpClientUtil;
    private AllureCustom allureCustom;

    @BeforeTest
    public void BeforeTest(){
        httpClientUtil=new HttpClientUtil();
        allureCustom=new AllureCustom();
        url= ProjectConstant.URL +"/out/test/pay/";
    }



    @Test(description = "正常购买支付")
    public void test_001(){
        //请求参数
        Map<String,String> map=new HashMap<String, String>();
        map.put("account", LoginConstant.ACCOUNT);
        map.put("password",LoginConstant.PASSWORD);
        String body= JSONObject.toJSONString(map);
        //请求返回结果
        String result= httpClientUtil.doPost(url,map);

        //组装Allure报告
        allureCustom.AllureCustomReport(url,body,result);

        //请求结果返回验证
        JSONObject jsonObj = JSONObject.parseObject(result);
        String code=jsonObj.getString("code");
        String msg=jsonObj.getString("msg");

        //验证信息，调用自定义的验证，方便生成报告信息
        allureCustom.allureAssert(code,LoginConstant.CODECORRECT,"验证code返回值为200");
        allureCustom.allureAssert(msg,LoginConstant.CODECORRECTMSG,"验证msg返回值为successful");
    }

    @Test(description = "正常取消支付")
    public void test_002(){
        //请求参数
        Map<String,String> map=new HashMap<String, String>();
        map.put("account", LoginConstant.ACCOUNT);
        map.put("password",LoginConstant.PASSWORD);
        String body= JSONObject.toJSONString(map);
        //请求返回结果
        String result= httpClientUtil.doPost(url,map);

        //组装Allure报告
        allureCustom.AllureCustomReport(url,body,result);

        //请求结果返回验证
        JSONObject jsonObj = JSONObject.parseObject(result);
        String code=jsonObj.getString("code");
        String msg=jsonObj.getString("msg");

        //验证信息，调用自定义的验证，方便生成报告信息
        allureCustom.allureAssert(code,LoginConstant.CODECORRECT,"验证code返回值为200");
        allureCustom.allureAssert(msg,LoginConstant.CODECORRECTMSG,"验证msg返回值为successful");
    }


}
