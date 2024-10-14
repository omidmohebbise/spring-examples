package com.omidmohebbise.sb.webclient.webclient.dto;

public class SupportDTO {
    private String url;
    private String text;

    // Constructors, getters, and setters

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "SupportDTO{" +
                "url='" + url + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
