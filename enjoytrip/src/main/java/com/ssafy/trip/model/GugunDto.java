package com.ssafy.trip.model;

public class GugunDto
{
	private int gugun_code;
	private String gugun_name;
	private int sido_code;
	
	public GugunDto(int gugun_code, String gugun_name, int sido_code)
	{
		super();
		this.gugun_code = gugun_code;
		this.gugun_name = gugun_name;
		this.sido_code = sido_code;
	}
	
	public GugunDto() {}
	
	public int getGugun_code()
	{
		return gugun_code;
	}
	public void setGugun_code(int gugun_code)
	{
		this.gugun_code = gugun_code;
	}
	public String getGugun_name()
	{
		return gugun_name;
	}
	public void setGugun_name(String gugun_name)
	{
		this.gugun_name = gugun_name;
	}
	public int getSido_code()
	{
		return sido_code;
	}
	public void setSido_code(int sido_code)
	{
		this.sido_code = sido_code;
	}

	@Override
	public String toString() {
		return "GugunDto [gugun_name=" + gugun_name + ", sido_code=" + sido_code + "]";
	}
	
}
