package com.tenmax.exam.repo;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.tenmax.exam.model.Advertise;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, Object> _native = new HashMap<String, Object>();
        List<Map<String, Object>> assets = new ArrayList<Map<String, Object>>();

        // clickUrl
        Map<String, Object> clickUrl = new HashMap<String, Object>();
        Map<String, Object> link = new HashMap<String, Object>();
        link.put("url", "https://www.tenmax.io");
        clickUrl.put("type", "clickUrl");
        clickUrl.put("link", link);
        assets.add(clickUrl);

        // description
        Map<String, Object> description = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("value", "萬事起頭難，想找工作不知道怎麼開始嗎？1111中台灣快速設定想要的工作條件，讓你輕鬆找到符合自己的工作！");
        description.put("type", "description");
        description.put("data", data);
        assets.add(description);

        // iconUrl
        Map<String, Object> iconUrl = new HashMap<String, Object>();
        Map<String, Object> image1 = new HashMap<String, Object>();
        image1.put("url", "//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec.jpg?v=1");
        image1.put("w", 1200);
        image1.put("h", 627);
        iconUrl.put("type", "iconUrl");
        iconUrl.put("img", image1);
        assets.add(iconUrl);

        // imageUrl
        Map<String, Object> imageUrl = new HashMap<String, Object>();
        Map<String, Object> image2 = new HashMap<String, Object>();
        image2.put("url", "//tenmaximg.cacafly.net/upload/2/3/8/1/4/790f81ec_icon.jpg?v=1");
        image2.put("w", 250);
        image2.put("h", 250);
        imageUrl.put("type", "imageUrl");
        imageUrl.put("img", image2);
        assets.add(imageUrl);

        // title
        Map<String, Object> title = new HashMap<String, Object>();
        Map<String, Object> data2 = new HashMap<String, Object>();
        data2.put("value", "求職找工作，我該怎麼選？推薦《行政後勤＋週休六日》");
        title.put("type", "title");
        title.put("data", data2);
        assets.add(title);

        List<String> impressionLink = new ArrayList<String>();
        impressionLink.add("https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488002834/522c36a1-049c-11ea-b603-bd9939e8cf90/23785/23814/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g");

        _native.put("assets", assets);
        _native.put("impressionLink", impressionLink);

        DBObject objectToSave1 = BasicDBObjectBuilder.start()
                .add("_native", _native)
                .get();

        // when
        mongoTemplate.save(objectToSave1, "Advertises");
//        mongoTemplate.save(objectToSave2, "Advertises");

        // then
        List<Advertise> advertiseList = advertisesRepository.findByTitle("求職找工作");
        Assert.assertEquals(advertiseList.size(), 1);
        Assert.assertNotEquals(advertiseList.get(0).getNative().getAssets().length, 0);
    }

}
