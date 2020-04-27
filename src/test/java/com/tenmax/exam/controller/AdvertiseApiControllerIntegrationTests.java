package com.tenmax.exam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenmax.exam.SampleApplication;
import com.tenmax.exam.model.Advertise;
import com.tenmax.exam.repo.AdvertisesRepository;
import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SampleApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class AdvertiseApiControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AdvertisesRepository advertisesRepository;

    @BeforeEach
    public void setup() {
        svaeTesingData();
    }

    @Test
    public void givenAdvertises_whenGetAdvertisesByTitle_thenStatus200() throws Exception {

        svaeTesingData();

        mvc.perform(get("/getAdsByTitle?title=浪漫下午茶")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.length()").value(2))
                .andDo(result -> {
                    result.getResponse().setCharacterEncoding("UTF-8");
                    System.out.println(result.getResponse().getContentAsString());
                });

    }

    private void svaeTesingData() {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Advertise advertise1 = objectMapper.readValue("{\"native\":{\"assets\":[{\"type\":\"description\",\"data\":{\"value\":\"2019新社花海熱鬧登場，尤其新社這家最美的餐廳，夕陽下湖邊倒影，怎麼拍都美！無菜單料理更是許多台中人最愛的招待餐廳之一，逛完小王子花毯節，就來又見一炊煙來份最暖胃又暖心的餐點吧！ \"}},{\"type\":\"imageUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/5/3/b96edbc3.jpg?v=1\",\"w\":1200,\"h\":627}},{\"type\":\"title\",\"data\":{\"value\":\"來新社花海不能錯過,湖邊浪漫下午茶,隨便拍都是網美照\"}},{\"type\":\"iconUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/5/3/b96edbc3_icon.jpg?v=1\",\"w\":250,\"h\":250}},{\"type\":\"clickUrl\",\"link\":{\"url\":\"https://www.tenmax.io\"}}],\"impressionLink\":[\"https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488000309/50aac6c1-049c-11ea-b603-bd9939e8cf90/23784/23753/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g\"]}}",
                    Advertise.class);
            advertisesRepository.save(advertise1);

            Advertise advertise2 = objectMapper.readValue("{\"native\":{\"assets\":[{\"type\":\"description\",\"data\":{\"value\":\"2019新社花海熱鬧登場，尤其新社這家最美的餐廳，夕陽下湖邊倒影，怎麼拍都美！無菜單料理更是許多台中人最愛的招待餐廳之一，逛完小王子花毯節，就來又見一炊煙來份最暖胃又暖心的餐點吧！ \"}},{\"type\":\"imageUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/5/3/b96edbc3.jpg?v=1\",\"w\":1200,\"h\":627}},{\"type\":\"title\",\"data\":{\"value\":\"來新社花海不能錯過,湖邊浪漫下午茶,隨便拍都是網美照\"}},{\"type\":\"iconUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/5/3/b96edbc3_icon.jpg?v=1\",\"w\":250,\"h\":250}},{\"type\":\"clickUrl\",\"link\":{\"url\":\"https://www.tenmax.io\"}}],\"impressionLink\":[\"https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488000309/50aac6c1-049c-11ea-b603-bd9939e8cf90/23784/23753/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g\"]}}",
                    Advertise.class);

            advertisesRepository.save(advertise2);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
