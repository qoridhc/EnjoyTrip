package com.ssafy.trip.model;

import java.util.List;

public class RouteDto {
	private String userId;
    private List<String> contentInfos;

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getContentInfos() {
        return contentInfos;
    }

    public void setContentInfos(List<String> contentInfos) {
        this.contentInfos = contentInfos;
    }
}
