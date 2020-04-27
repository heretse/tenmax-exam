package com.tenmax.exam.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import com.tenmax.exam.model.Advertise;
import com.tenmax.exam.repo.AdvertisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@RestController
public class AdvertiseApiController {

    @Autowired
    private AdvertisesRepository advertisesRepository;

    private String[] fakeData = {
            "{\"native\":{\"assets\":[{\"type\":\"description\",\"data\":{\"value\":\"2019新社花海熱鬧登場，尤其新社這家最美的餐廳，夕陽下湖邊倒影，怎麼拍都美！無菜單料理更是許多台中人最愛的招待餐廳之一，逛完小王子花毯節，就來又見一炊煙來份最暖胃又暖心的餐點吧！ \"}},{\"type\":\"imageUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/5/3/b96edbc3.jpg?v=1\",\"w\":1200,\"h\":627}},{\"type\":\"title\",\"data\":{\"value\":\"來新社花海不能錯過,湖邊浪漫下午茶,隨便拍都是網美照\"}},{\"type\":\"iconUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/5/3/b96edbc3_icon.jpg?v=1\",\"w\":250,\"h\":250}},{\"type\":\"clickUrl\",\"link\":{\"url\":\"https://www.tenmax.io\"}}],\"impressionLink\":[\"https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488000309/50aac6c1-049c-11ea-b603-bd9939e8cf90/23784/23753/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g\"]}}",
            "{\"native\":{\"assets\":[{\"type\":\"description\",\"data\":{\"value\":\"【免費體驗】想擁有白皙透明美肌?關鍵就在洗臉後的第一步，用「滲透乳」給予肌膚最佳比例的油、水及養分，回復角質層濕潤平衡，讓後續保養事半功倍。\"}},{\"type\":\"imageUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/5/5/3/6aacf3ee.png?v=1\",\"w\":1200,\"h\":627}},{\"type\":\"title\",\"data\":{\"value\":\"最夯保養概念「乳液先行」保養事半功倍 肌膚更顯白皙\"}},{\"type\":\"iconUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/5/5/3/6aacf3ee_icon.png?v=1\",\"w\":250,\"h\":250}},{\"type\":\"clickUrl\",\"link\":{\"url\":\"https://www.tenmax.io\"}}],\"impressionLink\":[\"https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488002851/522ef5c1-049c-11ea-b603-bd9939e8cf90/23097/23553/null/${WINNING_PRICE}/?optInfo=xlKbClUKZ4N_bw\"]}}",
            "{\"native\":{\"assets\":[{\"type\":\"description\",\"data\":{\"value\":\"官網最後倒數！買Dyson空氣清淨機TP06有效持續分解甲醛、過濾PM2.5、吹送涼風至全室！滿萬送千，中信LINE Pay卡再享4%回饋，立即下單！\"}},{\"type\":\"imageUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/9/2/4/dd977626.jpg?v=1\",\"w\":1200,\"h\":627}},{\"type\":\"title\",\"data\":{\"value\":\"就等雙11入手！戴森最新空氣清淨機 持續分解甲醛！\"}},{\"type\":\"iconUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/9/2/4/dd977626_icon.jpg?v=1\",\"w\":250,\"h\":250}},{\"type\":\"clickUrl\",\"link\":{\"url\":\"https://www.tenmax.io\"}}],\"impressionLink\":[\"https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488004418/531c8a11-049c-11ea-b603-bd9939e8cf90/24098/23924/null/${WINNING_PRICE}/?optInfo=xlKYjpzNASd8QA\"]}}",
            "{\"native\":{\"assets\":[{\"type\":\"description\",\"data\":{\"value\":\"一年一度ITF 台北國際旅展盛大舉行，全球最大酒店集團豪華客房33折起。無痛入手五星級飯店住宿券，商務、親子及樂齡旅遊規劃趁現在！\"}},{\"type\":\"imageUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/6/0/3c6f34ee.jpg?v=1\",\"w\":1200,\"h\":627}},{\"type\":\"title\",\"data\":{\"value\":\"2019 ITF台北國際旅展，年度盛大舉行\"}},{\"type\":\"iconUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/7/6/0/3c6f34ee_icon.jpg?v=1\",\"w\":250,\"h\":250}},{\"type\":\"clickUrl\",\"link\":{\"url\":\"https://www.tenmax.io\"}}],\"impressionLink\":[\"https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488002981/5242a4d1-049c-11ea-b603-bd9939e8cf90/23715/23760/null/${WINNING_PRICE}/?optInfo=xlKYjpzNASd8QA\"]}}",
            "{\"native\":{\"assets\":[{\"type\":\"description\",\"data\":{\"value\":\"即日起至2019/12/31，報名參加可樂旅遊出發之韓國樂天世界指定行程，即可獲得抽獎機會！\"}},{\"type\":\"imageUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/2/1/4/09f9464b.jpg?v=1\",\"w\":1200,\"h\":627}},{\"type\":\"title\",\"data\":{\"value\":\"瘋玩韓國樂天世界 即有機會抽中iPhone 11 Pro\"}},{\"type\":\"iconUrl\",\"img\":{\"url\":\"//tenmaximg.cacafly.net/upload/2/3/2/1/4/09f9464b_icon.jpg?v=1\",\"w\":250,\"h\":250}},{\"type\":\"clickUrl\",\"link\":{\"url\":\"https://www.tenmax.io\"}}],\"impressionLink\":[\"https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488002883/5233b0b3-049c-11ea-b603-bd9939e8cf90/22633/23214/null/${WINNING_PRICE}/?optInfo=xlKYgRwutql5\"]}}"
    };

    private Random random = new Random();

     /**
     * The path of API is http://host:port/hello
     * <p>
     * Return Json format message with 'Hello world' 
     * <p>
     * @return Output 'hello' message
     */
    @GetMapping(value = "/hello")
    public  String sayHello(){
        return "Hello";
    }

    /**
     * The path of API is http://host:port/getAdsByTitle?title=xxxxx
     * <p>
     * Return Json format message with 'Hello world' 
     * <p>
     * @param title This is a query by title. The Type is String.
     * @return Output Json message
     */
    @GetMapping(value = "/getAdsByTitle")
    public Map<String, Object> getAdsByTitle(@RequestParam(name = "title", required = true) String title) {

        List<Advertise> advertiseList = advertisesRepository.findByTitle(title);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", advertiseList);

        return map;
    }

//    @GetMapping(value = "/getAds", produces = "application/json;charset=UTF-8")
//    public ResponseBodyEmitter getAds()
//    {
//        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
//
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//
//        executor.execute(() -> {
//            try {
//                emitter.send(
//                        fakeData[random.nextInt(fakeData.length)],
//                        MediaType.APPLICATION_JSON);
//                emitter.complete();
//            }
//            catch (IOException e)
//            {
//                emitter.completeWithError(e);
//                return;
//            }
//        });
//
//        executor.shutdown();
//        return emitter;
//    }

    @GetMapping(value = "/getAds", produces = "application/json;charset=UTF-8")
    public Callable<String> echoHelloWorld()
    {
        boolean skip = (random.nextInt(2) == 0);

        return () ->
        {
            if (skip) {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                     return "{}";
                }
            } else {
                return fakeData[random.nextInt(fakeData.length)];
            }
        };
    }
}
