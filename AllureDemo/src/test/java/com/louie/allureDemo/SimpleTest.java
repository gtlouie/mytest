package com.louie.allureDemo;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
public class SimpleTest {


    @Feature("登录模块测试")
    @Stories(value ={@Story(value ="测试登录接口是否正常")})
    @Test(groups = "测试模块")

    public void simpleTest1() {
        Assert.assertEquals(1,1);

    }


    @Feature("登录模块测试")
    @Stories(value ={@Story(value ="测试登录接口是否不正常")})
    @Parameters({"1","2"})
    @Test(groups = "测试模块")
    public void simpleTest2() {
        Assert.assertEquals(2,2);
    }


    @Test(description="test3")
    public void simpleTest3() {
        Assert.assertEquals(2,3);
    }


    @Test(description="test4")
    public void simpleTest4() {
        Assert.assertEquals(3,3);
    }

}

