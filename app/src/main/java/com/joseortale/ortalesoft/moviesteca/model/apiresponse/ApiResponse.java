package com.joseortale.ortalesoft.moviesteca.model.apiresponse;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    @SerializedName("results")
    private NowPlayingResponse nowPlayingResponse;

    public NowPlayingResponse getNowPlayingResponse() {
        return nowPlayingResponse;
    }

    public void setNowPlayingResponse(NowPlayingResponse nowPlayingResponse) {
        this.nowPlayingResponse = nowPlayingResponse;
    }
}
