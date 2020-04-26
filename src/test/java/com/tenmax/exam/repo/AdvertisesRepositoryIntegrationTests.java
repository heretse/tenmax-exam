package com.tenmax.exam.repo;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.tenmax.exam.model.Advertise;
import com.tenmax.exam.repo.AdvertisesRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class AdvertisesRepositoryIntegrationTests {

    @Autowired
    private AdvertisesRepository advertisesRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void givenAdvertises_whenGetAdvertises_thenStatus200() throws Exception {
        // given
        DBObject objectToSave1 = BasicDBObjectBuilder.start()
                .add("clickUrl", "//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec_icon.jpg?v=1")
                .add("description", "萬事起頭難，想找工作不知道怎麼開始嗎？1111中台灣快速設定想要的工作條件，讓你輕鬆找到符合自己的工作！")
                .add("iconUrl", "https://www.tenmax.io")
                .add("imageUrl", "//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec.jpg?v=1")
                .add("impressionLink", "https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488002834/522c36a1-049c-11ea-b603-bd9939e8cf90/23785/23814/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g")
                .add("title", "求職找工作，我該怎麼選？推薦《行政後勤＋週休六日》")
                .get();

        DBObject objectToSave2 = BasicDBObjectBuilder.start()
                .add("clickUrl", "//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec_icon.jpg?v=1")
                .add("description", "萬事起頭難，想找工作不知道怎麼開始嗎？1111中台灣快速設定想要的工作條件，讓你輕鬆找到符合自己的工作！")
                .add("iconUrl", "https://www.tenmax.io")
                .add("imageUrl", "//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec.jpg?v=1")
                .add("impressionLink", "https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488002834/522c36a1-049c-11ea-b603-bd9939e8cf90/23785/23814/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g")
                .add("title", "求職找工作，我該怎麼選？推薦《行政後勤＋週休六日》")
                .get();

        // when
        mongoTemplate.save(objectToSave1, "Advertises");
        mongoTemplate.save(objectToSave2, "Advertises");

        // then
        List<Advertise> advertiseList = advertisesRepository.findByTitle("求職找工作");
        Assert.assertEquals(advertiseList.size(), 2);
        Assert.assertTrue(advertiseList.get(0).getTitle().contains("求職找工作"));
    }

}
