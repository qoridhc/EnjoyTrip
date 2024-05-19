package com.ssafy.trip.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {
		private String userId;
	    private List<RouteInfo> infoList;
	    private int route_id;

	    @Data
	    @NoArgsConstructor
	    @AllArgsConstructor
	    public static class RouteInfo {
	        private int sequence;
	        
	        @JsonProperty("content_id")
	        private int contentId;

	        private String description;
	    }
}
