package com.tenmax.exam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenmax.exam.SampleApplication;
import com.tenmax.exam.model.Advertise;
import com.tenmax.exam.repo.AdvertisesRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SampleApplication.class)
@AutoConfigureMockMvc
class SampleApiTests {

	@Autowired
	private AdvertisesRepository advertisesRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;

	Advertise advertise;

	@BeforeEach
	public void setUp() {
		advertise = new Advertise();
		advertise.setClickUrl("//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec_icon.jpg?v=1");
		advertise.setDescription("萬事起頭難，想找工作不知道怎麼開始嗎？1111中台灣快速設定想要的工作條件，讓你輕鬆找到符合自己的工作！");
		advertise.setIconUrl("https://www.tenmax.io");
		advertise.setId("5ea04e1dd50e1f22f5e4ed50");
		advertise.setImageUrl("//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec.jpg?v=1");
		advertise.setImpressionLink("https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488002834/522c36a1-049c-11ea-b603-bd9939e8cf90/23785/23814/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g");
		advertise.setTitle("求職找工作，我該怎麼選？推薦《行政後勤＋週休六日》");

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/*
	 * Test Hello API
	 */
    @Test
    public void testHello() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/hello").content("Hello"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		Assert.assertEquals(result.getResponse().getContentAsString(), "Hello");
    }

	/*
	 * Test getAdsByTitle API
	 */
	@Test

	public void getAdsByTitle() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/getAdsByTitle?title=工作").content(objectMapper.writeValueAsString(new ArrayList<Advertise>().add(advertise)))
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();

		int status = result.getResponse().getStatus();

		Assert.assertEquals("錯誤",200, status);
	}

}

