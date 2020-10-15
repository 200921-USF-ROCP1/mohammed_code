package com.revature.utilities;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.revature.models.Account;
import com.revature.models.User;

public class ServletUtilities {

	// Checks if given string is an Integer character
	public static boolean isInteger(String s) {
		if (s == null || s.length() == 0) return false;
		
		for (int i = 0; i < s.length(); ++i) {
			if (!Character.isDigit(s.charAt(i)))
				return false;
		}
		
		return true;
	}
	
	// Returns Unauthorized response
	public static void unauthorizedResponse(HttpServletResponse response) throws IOException {
		response.getWriter().println("You are not authorized to make this request.");
		response.setStatus(401);
	}
	
	
	
	/* *******************************
	 * 								 *
	 * ROLE CHECKERS				 *
	 * 								 *
	 * ****************************** */
	
	// Checks if User is Employee or Admin or if the account belongs to the current user
	public static boolean employeeAdminAccountOwner(Account account, User user) {
		ArrayList<String> roles = new ArrayList<>();
		roles.add("Admin");
		roles.add("Employee");
				
		return (roles.contains(user.getRole().getRole()) || account.getOwner().getUserName().equals(user.getUserName()));
	}
	
	// Checks if User is Admin or if the account belongs to the current user
	public static boolean adminOrAccountOwner(Account account, User user) {				
		return (user.getRole().getRole().equals("Admin") || account.getOwner().getUserName().equals(user.getUserName()));
	}
	
	// Checks if User is Employee or Admin or if the id provided matches the id of the current user
	public static boolean employeeAdminUserId(User user, int userId) {
		ArrayList<String> roles = new ArrayList<>();
		roles.add("Admin");
		roles.add("Employee");
				
		return (roles.contains(user.getRole().getRole()) || user.getId() == userId);
	}
	
	// If role is Admin or Employee
	public static boolean employeeOrAdmin(User user) {
		ArrayList<String> roles = new ArrayList<>();
		roles.add("Admin");
		roles.add("Employee");
				
		return (roles.contains(user.getRole().getRole()));
	}
	
	// If role is Admin or if the id provided matches the id of the current user
	public static boolean currentUserOrAdmin(User user, int userId) {				
		return (user.getRole().getRole().equals("Admin") || user.getId() == userId);
	}

	// If role is Admin
	public static boolean currentUserIsAdmin(User user) {				
		return (user.getRole().getRole().equals("Admin"));
	}

}
