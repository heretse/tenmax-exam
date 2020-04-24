package com.tenmax.exam.common;

public enum DataType {

    DESCRIPTION("description"),
    IMAGE_URL("imageUrl"),
    TITLE("title"),
    ICON_URL("iconUrl"),
    CLICK_URL("clickUrl"),
    IMPRESSION_LINK("impressionLink");

    private String type;

    DataType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
