package com.revature.models;

public class Account {
	private int accountId; // primary key
	private double balance; // not null
	private AccountStatus status;
	private AccountType type;
	
	public Account(double balance, AccountStatus status, AccountType type) {
		super();
		this.balance = balance;
		this.status = status;
		this.type = type;
	}
	
	
	public boolean withdraw(int amount) {
		if (balance <= 0) {
			return false;
		}
		this.balance -= amount;
		return true;
	}
		
	public boolean deposit(int amount) {
		this.balance += amount;
		return true;
	}
}
