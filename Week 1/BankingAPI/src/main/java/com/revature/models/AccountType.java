package com.revature.models;

public class AccountType {
	private int typeId; // primary key
	private String type; // not null, unique
	
	public AccountType(String type) {
		super();
		this.type = type;
	}
	
	
}
