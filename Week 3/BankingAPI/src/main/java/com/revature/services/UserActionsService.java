package com.revature.services;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.revature.dao.AccountDAOImpl;
import com.revature.dao.GenericDAO;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.Transfer;
import com.revature.models.User;
import com.revature.models.UsernamePassword;
import com.revature.models.WithdrawDeposit;

public class UserActionsService {

	UserDAO userDao = new UserDAOImpl();
	GenericDAO<Account> accountDao = new AccountDAOImpl();
	
	// Log in
	public User login(UsernamePassword usernamePass) {
		return userDao.getUserByUsernameAndPassword(usernamePass.getUsername(), usernamePass.getPassword());
	}

	
	public Object logout() {
		
		
		return null;
	}

	
	// Register user
	public User register(User user) {
		return userDao.create(user);
	}

	// Withdraw
	public boolean withdraw(WithdrawDeposit withdraw) {
		
		Account account = accountDao.get(withdraw.getAccountId());
		if (account == null) {
			return false;
		}
		double newBalance = account.getBalance() - withdraw.getAmount();
		
		if (newBalance > 0) {
			account.setBalance(newBalance);
			
			accountDao.update(account);
			return true;
		}else { //Not sufficient balance
			return false;
		}
		
	}

	// /deposit
	public boolean deposit(WithdrawDeposit deposit) {
		Account account = accountDao.get(deposit.getAccountId());
		
		if (account == null) {
			return false;
		}
		double newBalance = account.getBalance() + deposit.getAmount();
		
		account.setBalance(newBalance);
		
		accountDao.update(account);
		return true;
	}

	// /transfer
	public boolean transfer(Transfer transfer) {
		
		Account sourceAccount = accountDao.get(transfer.getSourceAccountId());
		Account targetAccount = accountDao.get(transfer.getTargetAccountId());

		if (sourceAccount == null || targetAccount == null) {
			return false;
		}
		double sourceBalance = sourceAccount.getBalance() - transfer.getAmount();
		double targetBalance = targetAccount.getBalance() + transfer.getAmount();
		
		
		if (sourceBalance > 0) {
			sourceAccount.setBalance(sourceBalance);
			targetAccount.setBalance(targetBalance);
			
			accountDao.update(sourceAccount);
			accountDao.update(targetAccount);
			return true;
		}else { // Source Does Not have sufficient balance
			return false;
		}
		
	}


}
