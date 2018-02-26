package com.example.grv.dailyinfonews.Model;

import java.util.List;

/**
 * Created by GRV on 2/26/2018.
 */

class UrlsToLogos{
    private String small,medium,large;

}

public class Source {

    private String id;
    private String description;
    private String name;
    private String url;
    private String catagory;
    private String language;
    private String country;
    private UrlsToLogos urlsToLogos;
    private List<String> sortByAvailable;

    public Source(String id, String description, String name, String url, String catagory, String language, String country, UrlsToLogos urlsToLogos, List<String> sortByAvailable) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.url = url;
        this.catagory = catagory;
        this.language = language;
        this.country = country;
        this.urlsToLogos = urlsToLogos;
        this.sortByAvailable = sortByAvailable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UrlsToLogos getUrlsToLogos() {
        return urlsToLogos;
    }

    public void setUrlsToLogos(UrlsToLogos urlsToLogos) {
        this.urlsToLogos = urlsToLogos;
    }

    public List<String> getSortByAvailable() {
        return sortByAvailable;
    }

    public void setSortByAvailable(List<String> sortByAvailable) {
        this.sortByAvailable = sortByAvailable;
    }
}
