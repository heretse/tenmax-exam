package com.tenmax.exam.service;

import com.google.gson.JsonObject;
import com.tenmax.exam.common.DeserializeJson;
import com.tenmax.exam.common.HttpUtil;
import com.tenmax.exam.model.Advertise;
import com.tenmax.exam.repo.AdvertisesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

@Component
public class CronJobService {

    private Logger logger = LoggerFactory.getLogger(CronJobService.class);

    @Value("${advertise.fetchPath}")
    private String advertiseFetchPath;

    @Autowired
    private AdvertisesRepository advertisesRepository;

    @Scheduled(cron = "1 * * * * ?")
    public void run() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            fetchAdvertise(new HttpUtil());
        });
    }

    protected boolean fetchAdvertise(HttpUtil httpUtil) {
        logger.debug("Current time in runAsync() :: " + Calendar.getInstance().getTime());

        String response = httpUtil.get(this.advertiseFetchPath);

        logger.debug("response :: " + response);

        JsonObject jsonObject = DeserializeJson.toObject(response);

        Advertise newAdvertise = AdvertiseService.parseFromJsonObject(jsonObject);

        if (newAdvertise != null) {
            advertisesRepository.save(newAdvertise);
            return true;
        }

        return false;
    }

}
