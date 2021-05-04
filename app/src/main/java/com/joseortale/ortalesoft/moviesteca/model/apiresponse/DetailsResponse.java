package com.joseortale.ortalesoft.moviesteca.model.apiresponse;

import com.google.gson.annotations.SerializedName;

public class DetailsResponse {
    @SerializedName("homepage")
    private String homepage;

    @SerializedName("id")
    private String id;

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
