package com.tenmax.exam.controller;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SampleApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")

public class SampleApiIntegrationTests {

    private static final String DATABASE_NAME = "test";

    @Autowired
    private MockMvc mvc;

//    private MongodExecutable mongodExecutable;

//    @Autowired
//    private MongoTemplate mongoTemplate;

    @Autowired
    private AdvertisesRepository advertisesRepository;

    @BeforeEach
    public void setup() {
        svaeTesingData();
    }

    @Test
    public void givenAdvertises_whenGetAdvertises_thenStatus200() throws Exception {

        svaeTesingData();

        mvc.perform(get("/getAdsByTitle?title=找工作")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    result.getResponse().setCharacterEncoding("UTF-8");
                    System.out.println(result.getResponse().getContentAsString());
                });
//                .andExpect(jsonPath("$.result.length()", is(2)));
    }

    private void svaeTesingData() {
        Advertise advertise = new Advertise();

        advertise.setClickUrl("//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec_icon.jpg?v=1");
        advertise.setDescription("萬事起頭難，想找工作不知道怎麼開始嗎？1111中台灣快速設定想要的工作條件，讓你輕鬆找到符合自己的工作！");
        advertise.setIconUrl("https://www.tenmax.io");
        advertise.setId("5ea04e1dd50e1f22f5e4ed50");
        advertise.setImageUrl("//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec.jpg?v=1");
        advertise.setImpressionLink("https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488002834/522c36a1-049c-11ea-b603-bd9939e8cf90/23785/23814/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g");
        advertise.setTitle("求職找工作，我該怎麼選？推薦《行政後勤＋週休六日》");

        advertisesRepository.save(advertise);
    }
}
