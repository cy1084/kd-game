package com.game.service.impl;

import java.util.List;
import java.util.Map;

import com.game.dao.UserInfoDAO;
import com.game.dao.impl.UserInfoDAOImpl;
import com.game.service.UserInfoService;

public class UserInfoServiceImpl implements UserInfoService {

	private UserInfoDAO userInfoDAO = new UserInfoDAOImpl();

	@Override
	public List<Map<String, String>> selectUserInfoList(Map<String, String> userInfo) {

		return userInfoDAO.selectUserInfoList(userInfo);
	}

	@Override
	public Map<String, String> selectUserInfo(String uiNum) {
	
		return userInfoDAO.selectUserInfo(uiNum);
	}

	@Override
	public int insertUserInfo(Map<String, String> userInfo) {

		return userInfoDAO.insertUserInfo(userInfo);
	}

	@Override
	public int updateUserInfo(Map<String, String> userInfo) {

		return userInfoDAO.updateUserInfo(userInfo);
	}

	@Override
	public int deleteUserInfo(String uiNum) {

		return userInfoDAO.deleteUserInfo(uiNum);
	}

}
