package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface AccountDAO extends GenericDAO<Account> {

	public List<Account> getAccountsByStatus(int statusId);
	public List<Account> getAccountsByUser(int userId);

}
