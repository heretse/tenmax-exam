package com.tenmax.exam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @MockBean
    private ObjectMapper objectMapper;

    private CronJobService cronJobService = new CronJobService();

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(cronJobService, "advertiseFetchPath", "http://localhost:8080/getAds");
        ReflectionTestUtils.setField(cronJobService, "advertisesRepository", advertisesRepository);
        ReflectionTestUtils.setField(cronJobService, "objectMapper", objectMapper);
    }

    @Test
    public void testRunCronJob() {

        when(httpUtil.get("http://localhost:8080/getAds")).thenReturn("{\"native\":{\"assets\":[{\"type\":\"description\",\"data\":{\"value\":\"2019新社花海熱鬧登場，尤其新社這家最美的餐廳，夕陽下湖邊倒影，怎麼拍都美！無菜單料理更是許多台中人最愛的招待餐廳之一，逛完小王子花毯節，就來又見一炊煙來份最暖胃又暖心的餐點吧！ \"}},{\"type\":\"imageUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/5/3/b96edbc3.jpg?v=1\",\"w\":1200,\"h\":627}},{\"type\":\"title\",\"data\":{\"value\":\"來新社花海不能錯過,湖邊浪漫下午茶,隨便拍都是網美照\"}},{\"type\":\"iconUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/5/3/b96edbc3_icon.jpg?v=1\",\"w\":250,\"h\":250}},{\"type\":\"clickUrl\",\"link\":{\"url\":\"https://www.tenmax.io\"}}],\"impressionLink\":[\"https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488000309/50aac6c1-049c-11ea-b603-bd9939e8cf90/23784/23753/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g\"]}}");

        Advertise advertise = new Advertise();

        try {
            when(objectMapper.readValue("{\"native\":{\"assets\":[{\"type\":\"description\",\"data\":{\"value\":\"2019新社花海熱鬧登場，尤其新社這家最美的餐廳，夕陽下湖邊倒影，怎麼拍都美！無菜單料理更是許多台中人最愛的招待餐廳之一，逛完小王子花毯節，就來又見一炊煙來份最暖胃又暖心的餐點吧！ \"}},{\"type\":\"imageUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/5/3/b96edbc3.jpg?v=1\",\"w\":1200,\"h\":627}},{\"type\":\"title\",\"data\":{\"value\":\"來新社花海不能錯過,湖邊浪漫下午茶,隨便拍都是網美照\"}},{\"type\":\"iconUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/5/3/b96edbc3_icon.jpg?v=1\",\"w\":250,\"h\":250}},{\"type\":\"clickUrl\",\"link\":{\"url\":\"https://www.tenmax.io\"}}],\"impressionLink\":[\"https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488000309/50aac6c1-049c-11ea-b603-bd9939e8cf90/23784/23753/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g\"]}}", Advertise.class)).thenReturn(advertise);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        when(advertisesRepository.save(advertise)).thenReturn(advertise);

        boolean result = cronJobService.fetchAdvertise(httpUtil);

        Assert.assertTrue(result);
    }

}