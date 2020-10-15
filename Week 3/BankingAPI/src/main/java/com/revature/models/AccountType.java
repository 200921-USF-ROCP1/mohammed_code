package com.revature.models;

public class AccountType {
	private int id; // primary key
	private String type; // not null, unique
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
		
	
}
