package com.tenmax.exam.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

public class AdvertiseTests {

    @Test
    public void testAdvertise() {

        ObjectMapper objectMapper = new ObjectMapper();

        Advertise advertise = null;
        try {
            advertise = objectMapper.readValue("{\"native\":{\"assets\":[{\"type\":\"description\",\"data\":{\"value\":\"即日起至2019/12/31，報名參加可樂旅遊出發之韓國樂天世界指定行程，即可獲得抽獎機會！\"}},{\"type\":\"imageUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/2/1/4/09f9464b.jpg?v=1\",\"w\":1200,\"h\":627}},{\"type\":\"title\",\"data\":{\"value\":\"瘋玩韓國樂天世界  即有機會抽中iPhone 11 Pro\"}},{\"type\":\"iconUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/2/1/4/09f9464b_icon.jpg?v=1\",\"w\":250,\"h\":250}},{\"type\":\"clickUrl\",\"link\":{\"url\":\"https://www.tenmax.io\"}}],\"impressionLink\":[\"https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488001083/50f7d551-049c-11ea-b603-bd9939e8cf90/22633/23214/null/${WINNING_PRICE}/?optInfo=xlKYgRwutql5\"]}}",
                    Advertise.class);

            advertise.setId("5ea04e1dd50e1f22f5e4ed50");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(advertise);
        Assert.assertEquals(advertise.getId(), "5ea04e1dd50e1f22f5e4ed50");
        Assert.assertEquals(advertise.getNative().getAssets().length, 5);
    }
}
