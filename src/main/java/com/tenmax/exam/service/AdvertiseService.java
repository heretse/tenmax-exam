package com.tenmax.exam.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tenmax.exam.common.DataType;
import com.tenmax.exam.model.Advertise;

public class AdvertiseService {

    public static Advertise parseFromJsonObject(JsonObject jsonObject) {

        if (jsonObject == null) {
            return null;
        }

        JsonObject nativeObj = jsonObject.getAsJsonObject("native");

        if (nativeObj == null) {
            return null;
        }

        JsonArray assetsArray = nativeObj.getAsJsonArray("assets");

        if (assetsArray == null) {
            return null;
        }

        Advertise newAdvertise = new Advertise();

        assetsArray.forEach(jsonElement -> {
            JsonObject jsonObject1 = (JsonObject)jsonElement;
            String type = jsonObject1.get("type").getAsString();

            if (DataType.DESCRIPTION.getType().equals(type)) {
                newAdvertise.setDescription(jsonObject1.getAsJsonObject("data").get("value").getAsString());
            } else if (DataType.IMAGE_URL.getType().equals(type)) {
                newAdvertise.setImageUrl(jsonObject1.getAsJsonObject("img").get("url").getAsString());
            } else if (DataType.TITLE.getType().equals(type)) {
                newAdvertise.setTitle(jsonObject1.getAsJsonObject("data").get("value").getAsString());
            } else if (DataType.IMAGE_URL.getType().equals(type)) {
                newAdvertise.setIconUrl(jsonObject1.getAsJsonObject("img").get("url").getAsString());
            } else if (DataType.CLICK_URL.getType().equals(type)) {
                newAdvertise.setClickUrl(jsonObject1.getAsJsonObject("link").get("url").getAsString());
            }
        });

        if (nativeObj.getAsJsonArray(DataType.IMPRESSION_LINK.getType()) != null) {
            newAdvertise.setImpressionLink(nativeObj.getAsJsonArray("impressionLink").get(0).getAsString() );
        }

        return newAdvertise;
    }
}
