package com.tenmax.exam.model;

import org.junit.Assert;
import org.junit.Test;

public class AdvertiseTests {

    @Test
    public void testAdvertise() {
        Advertise advertise = new Advertise();

        advertise.setClickUrl("//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec_icon.jpg?v=1");
        advertise.setDescription("萬事起頭難，想找工作不知道怎麼開始嗎？1111中台灣快速設定想要的工作條件，讓你輕鬆找到符合自己的工作！");
        advertise.setIconUrl("https://www.tenmax.io");
        advertise.setId("5ea04e1dd50e1f22f5e4ed50");
        advertise.setImageUrl("//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec.jpg?v=1");
        advertise.setImpressionLink("https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488002834/522c36a1-049c-11ea-b603-bd9939e8cf90/23785/23814/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g");
        advertise.setTitle("求職找工作，我該怎麼選？推薦《行政後勤＋週休六日》");

        Assert.assertEquals(advertise.getClickUrl(), "//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec_icon.jpg?v=1");
        Assert.assertEquals(advertise.getDescription(), "萬事起頭難，想找工作不知道怎麼開始嗎？1111中台灣快速設定想要的工作條件，讓你輕鬆找到符合自己的工作！");
        Assert.assertEquals(advertise.getIconUrl(), "https://www.tenmax.io");
        Assert.assertEquals(advertise.getId(), "5ea04e1dd50e1f22f5e4ed50");
        Assert.assertEquals(advertise.getImageUrl(), "//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec.jpg?v=1");
        Assert.assertEquals(advertise.getImpressionLink(), "https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488002834/522c36a1-049c-11ea-b603-bd9939e8cf90/23785/23814/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g");
        Assert.assertEquals(advertise.getTitle(), "求職找工作，我該怎麼選？推薦《行政後勤＋週休六日》");

        Assert.assertNotNull(advertise);
        Assert.assertNotNull(advertise.toString());
    }
}
