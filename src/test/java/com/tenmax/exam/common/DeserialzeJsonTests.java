package com.tenmax.exam.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DeserialzeJsonTests {

    @Test
    public void testToObject() {
        JsonObject emptyObject = DeserializeJson.toObject("{}");

        Assert.assertEquals(emptyObject.toString(), "{}");

        JsonObject jsonObject = DeserializeJson.toObject("{\"key\":\"value\"}");

        Assert.assertTrue(jsonObject.get("key") != null);

        Assert.assertEquals(jsonObject.get("key").getAsString(), "value");
    }
}
