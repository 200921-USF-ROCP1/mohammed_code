package com.revature.services;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.revature.models.Role;
import com.revature.models.User;

public class UserService {

	Connection connection;
	public UserService(Connection connection) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
	}
	// /login
	// Error- Status Code: 400 BAD REQUEST
	// {
	//	  "message": "Invalid Credentials"
	// }
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	// /logout
	// 
	// Error- Status Code: 400 BAD REQUEST
	// {
	//	  "message": "There was no user logged into the session"
	// }
	public Object logout() {
		// {
		//	  "message": "You have successfully logged out {username}"
		// }
		
		return null;
	}

	// /register
	// Error- Status Code: 400 BAD REQUEST
	// {
	//	  "message": "Invalid fields"
	// }
	public User register(User user) {
		// TODO Auto-generated method stub
		// Status Code: 201 CREATED
		return null;
	}

	// /accounts/withdraw
	public boolean withdraw(int accountId, double amount) {
		// TODO Auto-generated method stub
		// {
		//	  "message": ${amount} has been withdrawn from Account #{accountId}"
		// }
		return false;
	}

	// /deposit
	public boolean deposit(int accountId, double amount) {
		// TODO Auto-generated method stub
		// {
		//	  "message": ${amount} has been deposited to Account #{accountId}"
		// }
		return false;
	}

	// /transfer
	public boolean transfer(int sourceAccountId, int targetAccountId, double amount) {
		// TODO Auto-generated method stub
		// {
		//	  "message": ${amount} has been transferred from Account #{sourceAccountId}"
		// }
		return false;
	}

	public void createRole(Role role) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO role (role) VALUES (?)");
			ps.setString(1, role.getRole());
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//ResultSet rs = ps.executeQuery();
		//while(rs.next()) {
		//	rs.getString(row_num or column_name) or rs.getInt() or rs.getDouble() 
		//}
	}

}
