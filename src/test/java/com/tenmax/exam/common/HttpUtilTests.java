package com.tenmax.exam.common;

import org.junit.Assert;
import org.junit.Test;

public class HttpUtilTests {
    @Test
    public void testGet() {
        HttpUtil httpUtil = new HttpUtil();

        String response = httpUtil.get("https://www.gemteks.com/");

        Assert.assertTrue(response.contains("<title>正文科技股份有限公司</title>"));

        response = httpUtil.get("http://wrongdomain.com/");

        Assert.assertEquals(response, "");
    }
}
