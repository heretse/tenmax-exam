package com.tenmax.exam.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tenmax.exam.common.DeserializeJson;
import com.tenmax.exam.common.HttpUtil;
import com.tenmax.exam.model.Advertise;
import com.tenmax.exam.repo.AdvertisesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

@Component
public class CronJobService {

    Logger logger = LoggerFactory.getLogger(CronJobService.class);

    @Autowired
    private AdvertisesRepository advertisesRepository;

    @Scheduled(cron = "1 * * * * ?")
    public void run() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            logger.debug("Current time in runAsync() :: " + Calendar.getInstance().getTime());

            String response = new HttpUtil().get("https://tenmax-mock-dsp.azurewebsites.net/api/getAds?code=s9Ybtsb6hwigndO6a5OwsLmXOPR0olrW7nBFFE7QmHfvaQ6p9GWXwg==");

            logger.debug("response :: " + response);

            JsonObject jsonObject = DeserializeJson.toObject(response);
            JsonObject nativeObj = jsonObject.getAsJsonObject("native");
            JsonArray assetsArray = nativeObj.getAsJsonArray("assets");

            Advertise newAdvertise = new Advertise();

            assetsArray.forEach(jsonElement -> {
                JsonObject jsonObject1 = (JsonObject)jsonElement;
                String type = jsonObject1.get("type").getAsString();

                if ("description".equals(type)) {
                    newAdvertise.setDescription(jsonObject1.getAsJsonObject("data").get("value").getAsString());
                } else if ("imageUrl".equals(type)) {
                    newAdvertise.setImageUrl(jsonObject1.getAsJsonObject("img").get("url").getAsString());
                } else if ("title".equals(type)) {
                    newAdvertise.setTitle(jsonObject1.getAsJsonObject("data").get("value").getAsString());
                } else if ("iconUrl".equals(type)) {
                    newAdvertise.setIconUrl(jsonObject1.getAsJsonObject("img").get("url").getAsString());
                } else if ("clickUrl".equals(type)) {
                    newAdvertise.setClickUrl(jsonObject1.getAsJsonObject("link").get("url").getAsString());
                }
            });

            if (nativeObj.getAsJsonArray("impressionLink") != null) {
                newAdvertise.setImpressionLink(nativeObj.getAsJsonArray("impressionLink").get(0).getAsString() );
            }

            advertisesRepository.save(newAdvertise);
        });
    }

}
