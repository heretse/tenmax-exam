package com.tenmax.exam.common;

import org.junit.Assert;
import org.junit.Test;

public class HttpUtilTests {
    @Test
    public void testGet() {
        String response = HttpUtil.get("https://www.gemteks.com/");

        Assert.assertTrue(response.contains("<title>正文科技股份有限公司</title>"));

        response = HttpUtil.get("http://wrongdomain.com/");

        Assert.assertEquals(response, "");
    }
}
