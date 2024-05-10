package com.ssafy.user.model;

public class UserDto
{
	private String id;
	private String pw;
	private String name;
	private String email_1;
	private String email_2;

	public UserDto()
	{
	}

	public UserDto(String id, String pw)
	{
		this.id = id;
		this.pw = pw;
	}

	public UserDto(String id, String pw, String name, String email_1,
			String email_2)
	{
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email_1 = email_1;
		this.email_2 = email_2;
	}


	@Override
	public String toString()
	{
		return "User{" + "id='" + id + '\'' + ", pw='" + pw + '\'' + ", name='"
				+ name + '\'' + ", email_1='" + email_1 + '\'' + ", email_2='"
				+ email_2 + '\'' + '}';
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getPw()
	{
		return pw;
	}

	public void setPw(String pw)
	{
		this.pw = pw;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail_1()
	{
		return email_1;
	}

	public void setEmail_1(String email_1)
	{
		this.email_1 = email_1;
	}

	public String getEmail_2()
	{
		return email_2;
	}

	public void setEmail_2(String email_2)
	{
		this.email_2 = email_2;
	}
}
