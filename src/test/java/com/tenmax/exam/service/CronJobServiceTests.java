package com.tenmax.exam.service;

import com.tenmax.exam.common.HttpUtil;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HttpUtil.class)
class CronJobServiceTests {

    @Test
    public void testRunCronJob() {

        PowerMockito.mockStatic(HttpUtil.class);
        when(HttpUtil.get("\"https://tenmax-mock-dsp.azurewebsites.net/api/getAds?code=s9Ybtsb6hwigndO6a5OwsLmXOPR0olrW7nBFFE7QmHfvaQ6p9GWXwg==\"")).thenReturn("{\"native\":{\"assets\":[{\"type\":\"description\",\"data\":{\"value\":\"2019新社花海熱鬧登場，尤其新社這家最美的餐廳，夕陽下湖邊倒影，怎麼拍都美！無菜單料理更是許多台中人最愛的招待餐廳之一，逛完小王子花毯節，就來又見一炊煙來份最暖胃又暖心的餐點吧！ \"}},{\"type\":\"imageUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/5/3/b96edbc3.jpg?v=1\",\"w\":1200,\"h\":627}},{\"type\":\"title\",\"data\":{\"value\":\"來新社花海不能錯過,湖邊浪漫下午茶,隨便拍都是網美照\"}},{\"type\":\"iconUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/5/3/b96edbc3_icon.jpg?v=1\",\"w\":250,\"h\":250}},{\"type\":\"clickUrl\",\"link\":{\"url\":\"https://www.tenmax.io\"}}],\"impressionLink\":[\"https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488000309/50aac6c1-049c-11ea-b603-bd9939e8cf90/23784/23753/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g\"]}}");

        CronJobService cronJobService = new CronJobService();
        cronJobService.run();

        PowerMockito.verifyStatic(VerificationModeFactory.calls(1));
    }

}