package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AdminActionsService;
import com.revature.utilities.ServletUtilities;

public class CRUDAccountFrontController extends HttpServlet {

	AdminActionsService adminActions = new AdminActionsService();

	ObjectMapper mapper = new ObjectMapper();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			String[] path = request.getRequestURI().split("/");
			
			HttpSession session = request.getSession();

			User currentUser = (User) session.getAttribute("currentUser");

			String responseUser;
			
			Account currentUserAccount;
			
			// If user is logged in, determine type of get request
			if (currentUser != null) {
				
				if (path.length == 3) {  //  getAll	
					
					//User must be Admin or an Employee
					if (ServletUtilities.employeeOrAdmin(currentUser)) {

						// First, we get the actual data
						List<Account> accounts =  adminActions.getAccounts();
						
						// Convert our list into JSON and write it to the body
						response.getWriter().println(mapper.writeValueAsString(accounts));
					}else {
						ServletUtilities.unauthorizedResponse(response);
					}
				//If length = 4, its get account by id	
				} else if (path.length == 4) {
					
					if (ServletUtilities.isInteger(path[3])) {
					
						int id = Integer.parseInt(path[3]);
			
						Account account = adminActions.getAccountById(id);
						
						// Employee or Admin or if the id provided matches the id of the current user
						if (ServletUtilities.employeeAdminAccountOwner(account, currentUser)) {
							response.getWriter().println(mapper.writeValueAsString(account));
						}else {
							ServletUtilities.unauthorizedResponse(response);
						}
						
					} else {
						// do some error handling
						response.getWriter().println("Invalid input");
					}
				
				//If length = 5, its get account by status or user	
				}else if (path.length == 5){
					
					// Check if given status/user id is a number
					if (ServletUtilities.isInteger(path[4])) {
						
						// Get by status
						if (path[3].equals("status")) {
	
							// If Role is Employee or Admin
							if(ServletUtilities.employeeOrAdmin(currentUser) ) {
								int id = Integer.parseInt(path[4]);
								
								List<Account> accounts = adminActions.getAccountsByStatus(id);
								
								response.getWriter().println(mapper.writeValueAsString(accounts));
							}else {
								ServletUtilities.unauthorizedResponse(response);;
							}
							
						// Get by user
						}else {
							int id = Integer.parseInt(path[4]);
							
							// Employee or Admin or if the id provided matches the id of the current user
							if(ServletUtilities.employeeAdminUserId(currentUser, id)) {
								List<Account> accounts = adminActions.getAccountsByUser(id);
								
								response.getWriter().println(mapper.writeValueAsString(accounts));
							}else {
								ServletUtilities.unauthorizedResponse(response);
							}
						}
					} else {
						// do some error handling
						response.getWriter().println("Invalid input");
					}
				}
			}
			// if user doesn't have access, return 401
			else { 
				ServletUtilities.unauthorizedResponse(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			response.setStatus(500);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();

			User currentUser = (User) session.getAttribute("currentUser");

			// If user is logged in
			if (currentUser != null) {
				// Get Account object
				Account account = mapper.readValue(request.getReader(), Account.class);
				
				// Send it to admin service
				Account newAccount = adminActions.createAccount(account);
				
				// Serialize account object
				String responseAccount = mapper.writeValueAsString(newAccount);
				
				// Write back the account
				response.getWriter().println(responseAccount);
				
				// Set status to 201 created
				response.setStatus(201);
			}else { // Unauthorized request
				ServletUtilities.unauthorizedResponse(response);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doPut(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();

			User currentUser = (User) session.getAttribute("currentUser");

			// If user is logged in and is an Admin
			if (currentUser != null && ServletUtilities.currentUserIsAdmin(currentUser)) {
				// Get Account object
				Account account = mapper.readValue(request.getReader(), Account.class);
				
				// Send it to admin service
				Account updatedAccount = adminActions.updateAccount(account);
				
				// Serialize account object
				String responseAccount = mapper.writeValueAsString(updatedAccount);
				
				// Write back the account
				response.getWriter().println(responseAccount);
				
			}else {
				ServletUtilities.unauthorizedResponse(response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
