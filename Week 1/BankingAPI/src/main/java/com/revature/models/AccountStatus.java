package com.revature.models;

public class AccountStatus {
	private int statusId; // primary key
	private String status; // not null, unique
	
	public AccountStatus(String status) {
		super();
		this.status = status;
	}
	
}
