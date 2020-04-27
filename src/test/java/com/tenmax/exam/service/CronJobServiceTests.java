package com.tenmax.exam.service;

import com.tenmax.exam.common.HttpUtil;
import com.tenmax.exam.model.Advertise;
import com.tenmax.exam.repo.AdvertisesRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;


import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CronJobService.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class CronJobServiceTests {

    @MockBean
    private AdvertisesRepository advertisesRepository;

    @MockBean
    private HttpUtil httpUtil;

    private CronJobService cronJobService = new CronJobService();

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(cronJobService, "advertiseFetchPath", "http://localhost:8080/getAds");
        ReflectionTestUtils.setField(cronJobService, "advertisesRepository", advertisesRepository);
    }

    @Test
    public void testRunCronJob() {

        when(httpUtil.get("http://localhost:8080/getAds")).thenReturn("{\"native\":{\"assets\":[{\"type\":\"description\",\"data\":{\"value\":\"2019新社花海熱鬧登場，尤其新社這家最美的餐廳，夕陽下湖邊倒影，怎麼拍都美！無菜單料理更是許多台中人最愛的招待餐廳之一，逛完小王子花毯節，就來又見一炊煙來份最暖胃又暖心的餐點吧！ \"}},{\"type\":\"imageUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/5/3/b96edbc3.jpg?v=1\",\"w\":1200,\"h\":627}},{\"type\":\"title\",\"data\":{\"value\":\"來新社花海不能錯過,湖邊浪漫下午茶,隨便拍都是網美照\"}},{\"type\":\"iconUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/5/3/b96edbc3_icon.jpg?v=1\",\"w\":250,\"h\":250}},{\"type\":\"clickUrl\",\"link\":{\"url\":\"https://www.tenmax.io\"}}],\"impressionLink\":[\"https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488000309/50aac6c1-049c-11ea-b603-bd9939e8cf90/23784/23753/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g\"]}}");

        Advertise advertise = new Advertise();
        advertise.setClickUrl("//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec_icon.jpg?v=1");
        advertise.setDescription("萬事起頭難，想找工作不知道怎麼開始嗎？1111中台灣快速設定想要的工作條件，讓你輕鬆找到符合自己的工作！");
        advertise.setIconUrl("https://www.tenmax.io");
        advertise.setId("5ea04e1dd50e1f22f5e4ed50");
        advertise.setImageUrl("//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec.jpg?v=1");
        advertise.setImpressionLink("https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488002834/522c36a1-049c-11ea-b603-bd9939e8cf90/23785/23814/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g");
        advertise.setTitle("求職找工作，我該怎麼選？推薦《行政後勤＋週休六日》");

        when(advertisesRepository.save(advertise)).thenReturn(advertise);

        boolean result = cronJobService.fetchAdvertise(httpUtil);

        Assert.assertTrue(result);
    }

}