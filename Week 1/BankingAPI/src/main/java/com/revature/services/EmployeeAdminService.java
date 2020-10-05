package com.revature.services;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public class EmployeeAdminService {

	/*
	 * endpoint: /users 
	 * res: [User]
	 */
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * endpoint: /users/:id
	 * res: User
	 */
	public User getUserByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * endpoint: /users 
	 * res: User
	 */
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * endpoint: /accounts 
	 * res: [Account]
	 */
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * endpoint: /accounts/:id 
	 * res: Account
	 */
	public Account getAccountByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * endpoint: /accounts/status/:statusId 
	 * res: [Account]
	 */
	public Account getAccountByStatus(int statusId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * endpoint: /accounts/owner/:userId or  /accounts/owner/:userId?accountType=type
	 * res: [User]
	 */
	public Account getAccountByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * endpoint: /accounts 
	 * res: Status Code: 201 CREATED
	 */
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * endpoint: /accounts 
	 * res: Account
	 */
	public Account updateAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

}
