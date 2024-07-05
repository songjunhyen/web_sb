package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.vo.User;

import util.Util;

@Service
public class UserService {
	private final UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public void Signup(User user) {
		userDao.Signup(user);
	}

	public User Login(String userid, String userpw) {
		return userDao.Login(userid, userpw);
	}

	public void updateUserLoginSession(User user, String loginCode) {
		userDao.updateUserLoginSession(user.getUserid(), user.getNickname(), loginCode);
		userDao.updateLoginCode(user.getUserid(), loginCode);
	}

	public void Logout(User foundUser) {
		userDao.Logout(foundUser);
	}

	public void Update(User foundUser) {
		userDao.Update(foundUser);
	}

	public void Delete(User foundUser) {
		userDao.Delete(foundUser);
	}

	public User getUserById(String userid) {
		User user = userDao.getUserById(userid);
		
		if(user == null) {
			return null;
		}
		return user;
	}

}
