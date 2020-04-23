package com.tenmax.exam.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Advertises")
public class Advertise {
    @Id
    private String id;

    private String title;
    private String description;
    private String imageUrl;
    private String iconUrl;
    private String impressionLink;
    private String clickUrl;

    //getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getImpressionLink() {
        return impressionLink;
    }

    public void setImpressionLink(String impressionLink) {
        this.impressionLink = impressionLink;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    @Override
    public String toString() {
        return String.format(
                "Advertise[id=%s, title='%s', description='%s']",
                id, title, description);
    }
}
