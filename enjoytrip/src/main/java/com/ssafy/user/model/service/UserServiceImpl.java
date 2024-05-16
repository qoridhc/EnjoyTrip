package com.ssafy.user.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.tags.shaded.org.apache.regexp.recompile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.dao.UserDao;

@Service
public class UserServiceImpl implements UserService
{

	private UserDao userDao;

	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDto login(String id) throws Exception{
		return userDao.login(id);
	}

	@Override
	public void signUp(Map<String, String> signInfo) throws SQLException {
		userDao.signUp(signInfo);
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return userDao.idCheck(userId);
	}

	@Override
	public void modify(Map<String, String> map) {
		 userDao.modify(map);
	}

	@Override
	public void delete(String id) {
		userDao.delete(id);
	}
	

	@Override
	public UserDto userInfo(String userId) throws Exception {
		return userDao.userInfo(userId);
	}

	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		userDao.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return userDao.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		userDao.deleteRefreshToken(map);
	}
	
}
