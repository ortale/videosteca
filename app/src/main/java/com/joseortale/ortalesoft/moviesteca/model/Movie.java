package com.joseortale.ortalesoft.moviesteca.model;

import com.google.gson.annotations.SerializedName;
import com.joseortale.ortalesoft.moviesteca.model.apiresponse.CollectionResponse;

public class Movie {
    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("adult")
    private Boolean adult;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("overview")
    private String overview;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("name")
    private String name;

    @SerializedName("belongs_to_collection")
    private CollectionResponse belongsToCollection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CollectionResponse getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(CollectionResponse belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }
}