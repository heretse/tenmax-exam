package com.tenmax.service;

import java.util.List;

import com.tenmax.service.model.Advertise;
import com.tenmax.service.repo.AdvertisesRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
class MongoDBTests {

    @Autowired
    private AdvertisesRepository advertisesRepository;

	@Test
	public void testAdvertisesRepositoryForMongoDB(){
		List<Advertise> advertiseList = advertisesRepository.findAll();
		System.out.printf("advertiseList's number:%d\n", advertiseList.size());

		for (Advertise advertise : advertiseList) {
			System.out.printf(advertise.toString());
		}
	}
}