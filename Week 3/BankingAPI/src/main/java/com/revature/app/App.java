//package com.revature.app;
//
//import com.revature.models.Role;
//import com.revature.services.UserService;
//import com.revature.utilities.DAOUtilities;
//
//import java.sql.Connection;
//
//public class App {
//	public static void main(String[] args) {
//		
//		try {
//			Connection connection = DAOUtilities.getConnection();
//			
//			UserService userDAO = new UserService(connection);
//			
//			Role role = new Role("Employee");
//			
//			userDAO.createRole(role);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//}
