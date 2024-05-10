package com.ssafy.user.model.service;

import java.util.Map;

import com.ssafy.user.model.UserDto;

public interface UserService
{
	public UserDto login(String id) throws Exception;
	public void signUp(Map<String,String> signInfo) throws Exception;
	
	public int idCheck(String userId) throws Exception;
	public void modify(Map<String, String> map);
 
    public void delete(String id);
}
