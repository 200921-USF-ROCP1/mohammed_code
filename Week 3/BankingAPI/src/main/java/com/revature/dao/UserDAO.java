package com.revature.dao;

import com.revature.models.User;

public interface UserDAO extends GenericDAO<User> {
	
	public User getUserByUsernameAndPassword(String userName, String password);
}
