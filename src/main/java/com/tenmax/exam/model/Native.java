package com.tenmax.exam.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Native {

    private Asset[] assets;

    private String [] impressionLink;

    public Asset[] getAssets() {
        return assets;
    }

    public void setAssets(Asset[] assets) {
        this.assets = assets;
    }

    public String[] getImpressionLink() {
        return impressionLink;
    }

    public void setImpressionLink(String[] impressionLink) {
        this.impressionLink = impressionLink;
    }
}
