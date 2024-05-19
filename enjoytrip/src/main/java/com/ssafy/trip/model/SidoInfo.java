package com.ssafy.trip.model;

public class SidoInfo {
	private String sido_code;
	private String sido_name_kor;
	private String sido_name_eng;
	private String sido_description;
	public String getSido_code() {
		return sido_code;
	}
	public void setSido_code(String sido_code) {
		this.sido_code = sido_code;
	}
	public String getSido_name_kor() {
		return sido_name_kor;
	}
	public void setSido_name_kor(String sido_name_kor) {
		this.sido_name_kor = sido_name_kor;
	}
	public String getSido_name_eng() {
		return sido_name_eng;
	}
	public void setSido_name_eng(String sido_name_eng) {
		this.sido_name_eng = sido_name_eng;
	}
	public String getSido_description() {
		return sido_description;
	}
	public void setSido_description(String sido_description) {
		this.sido_description = sido_description;
	}
	@Override
	public String toString() {
		return "SidoInfo [sido_code=" + sido_code + ", sido_name_kor=" + sido_name_kor + ", sido_name_eng="
				+ sido_name_eng + ", sido_description=" + sido_description + "]";
	}
}
