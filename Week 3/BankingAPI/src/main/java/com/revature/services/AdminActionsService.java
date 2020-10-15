package com.revature.services;

import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.Account;
import com.revature.models.User;

public class AdminActionsService {

	UserDAO userDAO = new UserDAOImpl();
	AccountDAO accountDAO = new AccountDAOImpl();
	
	// Get all users
	public List<User> getUsers() {

		return userDAO.getAll();
	}

	// Get user by Id
	public User getUserById(int id) {
		return userDAO.get(id);
	}

	// Update a user
	public User updateUser(User user) {
		return userDAO.update(user);
	}

	// Get all accounts
	public List<Account> getAccounts() {
		return accountDAO.getAll();
	}

	// Get account by id
	public Account getAccountById(int id) {
		return accountDAO.get(id);
	}

	// Get accounts by status id
	public List<Account> getAccountsByStatus(int statusId) {
		return accountDAO.getAccountsByStatus(statusId);
	}

	// Get accounts by owner
	public List<Account> getAccountsByUser(int userId) {
		return accountDAO.getAccountsByUser(userId);
	}

	// Create account
	public Account createAccount(Account account) {
		 return accountDAO.create(account);
	}

	// Update account
	public Account updateAccount(Account account) {
		 return accountDAO.update(account);
	}

}
