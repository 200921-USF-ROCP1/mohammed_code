package com.revature.models;

public class User {
	private int userId; // primary key
	private String userName; // not null, unique
	private String password; // not null
	private String firstName; // not null
	private String lastName; // not null
	private String email; // not null
	private Role role;
	
	public User(String userName, String password, String firstName, String lastName, String email,
			Role role) {
		super();
//		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}
	
	public boolean validateCredentials(String userName, String password) {
		return userName.equals(this.userName) && password.equals(this.password);
	}
	
	public void setEmail(String email) {
		//TODO: Validate email
		this.email = email;
	}
	
	public void setPassword(String password) {
		//TODO: Ask for old password and validate
		this.password = password;
	}
	
	public void setUserName(String userName) {
		//TODO: Ask for old user name and validate
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		String welcomeUser = "Welcome " + firstName ;
		return welcomeUser;
	}
	
	
}
