package com.ssafy.user.model.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.user.model.UserDto;

@Mapper
public interface UserDao
{
	public UserDto login(String id) throws SQLException;
	
	public void signUp(Map<String,String> signInfo) throws SQLException;
	
	public int idCheck(String userId) throws Exception;
	
	public void modify(Map<String, String> map);
	
    public void delete(String id);
    
	UserDto userInfo(String userId) throws SQLException;
    
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	
	Object getRefreshToken(String userid) throws SQLException;
	
	void deleteRefreshToken(Map<String, String> map) throws SQLException;

	public int modifyUser(UserDto user) throws SQLException;
}
