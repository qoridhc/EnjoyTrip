package com.ssafy.trip.model;

public class AttractionDto
{
	private String content_id;
	private String title;
	private String addr1;
	private String first_image;
	private String sido_code;
	private String latitude;
	private String longitude;
	private String overview;
	
	// 생성자
	public AttractionDto() {}
	public AttractionDto(String content_id, String title, String addr1, String first_image, String sido_code, String latitude,String longitude, String overview) {
		this.content_id = content_id;
		this.title = title;
		this.addr1 = addr1;
		this.first_image = first_image;
		this.sido_code = sido_code;
		this.latitude = latitude;
		this.longitude = longitude;
		this.overview = overview;
	}

	// getter & setter
	public String getContent_id() {
		return content_id;
	}
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getFirst_image() {
		return first_image;
	}
	public void setFirst_image(String first_image) {
		this.first_image = first_image;
	}
	public String getSido_code() {
		return sido_code;
	}
	public void setSido_code(String sido_code) {
		this.sido_code = sido_code;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
}
