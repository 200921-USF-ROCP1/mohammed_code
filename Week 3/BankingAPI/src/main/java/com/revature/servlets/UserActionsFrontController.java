package com.revature.servlets;


import java.io.StringReader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.Account;
import com.revature.models.Transfer;
import com.revature.models.User;
import com.revature.models.UsernamePassword;
import com.revature.models.WithdrawDeposit;
import com.revature.services.AdminActionsService;
import com.revature.services.UserActionsService;
import com.revature.utilities.ServletUtilities;

public class UserActionsFrontController extends HttpServlet {
	UserDAO userDao = new UserDAOImpl();
	UserActionsService userService = new UserActionsService();
	AdminActionsService employeeAdminService = new AdminActionsService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String[] path = request.getRequestURI().split("/");
			ObjectMapper mapper = new ObjectMapper();
			
			HttpSession session = request.getSession();

			User currentUser = (User) session.getAttribute("currentUser");

			String responseUser;
			
			Account account;
			
			String firstPath = path[2];
			
			if (path.length == 3) {
				switch(firstPath) {
				
				case "login":
					UsernamePassword usernamePass =  mapper.readValue(request.getReader(), UsernamePassword.class);
					
					// If user already not logged in, validate login info
					if (currentUser == null) {
						currentUser = userService.login(usernamePass);
						
						// If login info is not valid
						if (currentUser == null) {
							response.getWriter().println("{\"message\": \"Invalid Credentials\"}");
							response.setStatus(401);
						}else {
							// Store current User in session
							session.setAttribute("currentUser", currentUser);
							
							// Convert User object to JSON string
							responseUser = mapper.writeValueAsString(currentUser);
							response.getWriter().println(responseUser);
						}
					}else {
						// Convert User object to JSON string
						responseUser = mapper.writeValueAsString(currentUser);
						response.getWriter().println(responseUser);
					}
					
					break;
					
				case "logout":						
					// If user is not logged in
					if (currentUser == null) {
						response.getWriter().println("{\"message\": \"There was no user logged into the session\"}");
						response.setStatus(400);
					}else {
						// Remove user from session
						session.invalidate();
						
						// Send logged out successfully message
						response.getWriter().println("{\"message\": \"You have successfully logged out " + currentUser.getUserName() + "\"}");
					}
					
					break;
					
				case "register":
					
									
					// User is logged in
					if (currentUser != null) {
						
						// Get user object
						User newUser =  mapper.readValue(request.getReader(), User.class);
						
						// If logged in user is an Admin, register
						if (ServletUtilities.currentUserIsAdmin(currentUser)){
							User user = userService.register(newUser);
							if(user != null) {
								responseUser = mapper.writeValueAsString(user);
								response.getWriter().println(responseUser);
								response.setStatus(201);
							}else {
								response.getWriter().println("{\"message\": \"Invalid fields\"}");
								response.setStatus(400);
							}
	
						
						// If logged in user is not an Admin, send unauthorized
						}else if (!ServletUtilities.currentUserIsAdmin(currentUser)){
							response.getWriter().println("{\"message\": \"You are not authorized to make this request.\"}");
							response.setStatus(401);
						
						// Invalid fields
						}else {
							response.getWriter().println("{\"message\": \"Invalid fields\"}");
							response.setStatus(400);
						}
					}
					// User is not logged in, ask them to log in
					else {
						response.getWriter().println("{\"message\": \"Please Login\"}");
						response.setStatus(401);
					}
										
					break;
				
				default:
					response.sendError(404);
				}
			}else {
				String secondPath = path[3];
				
				if (currentUser != null) {
					
					switch (secondPath) {
					
					case "withdraw":
						// Get withdraw info
						WithdrawDeposit withdraw =  mapper.readValue(request.getReader(), WithdrawDeposit.class);
						
						// Get account
						account = employeeAdminService.getAccountById(withdraw.getAccountId());
						
						// Admin or if the account belongs to the current user
						if (ServletUtilities.adminOrAccountOwner(account, currentUser)) {
							if (userService.withdraw(withdraw)) {
								response.getWriter().println("{\"message\": \"" + withdraw.getAmount() + " has been withdrawn from Account #" + withdraw.getAccountId() + "\"}");
							}else {
								response.getWriter().println("{\"message\": \"Withdraw Unsuccessfull\"}");
								response.setStatus(400);

							}
						}else { //Unauthorized request
							ServletUtilities.unauthorizedResponse(response);
						}
						
						break;
						
					case "deposit":
						
						// Get deposit info
						WithdrawDeposit deposit =  mapper.readValue(request.getReader(), WithdrawDeposit.class);
						
						// Get account
						account = employeeAdminService.getAccountById(deposit.getAccountId());

						// Admin or if the account belongs to the current user
						if (ServletUtilities.adminOrAccountOwner(account, currentUser)) {

							if (userService.deposit(deposit)) {
								response.getWriter().println("{\"message\": \"" + deposit.getAmount() + " has been deposited to Account #" + deposit.getAccountId() + "\"}");
							}else {
								response.getWriter().println("{\"message\": \"Deposit Unsuccessfull\"}");
								response.setStatus(400);
		
							}
						}else { //Unauthorized request
							ServletUtilities.unauthorizedResponse(response);
						}
						break;
						
					case "transfer":
						
						// Get transfer info
						Transfer transfer =  mapper.readValue(request.getReader(), Transfer.class);
						
						// Get account
						account = employeeAdminService.getAccountById(transfer.getSourceAccountId());

						// Admin or if the source account belongs to the current user
						if (ServletUtilities.adminOrAccountOwner(account, currentUser)) {
		
							if (userService.transfer(transfer)) {
								response.getWriter().println("{\"message\": \"" + transfer.getAmount() + " has been transferred from Account #" + transfer.getSourceAccountId() + " to Account #" + transfer.getTargetAccountId() + "\"}");
							}else {
								response.getWriter().println("{\"message\": \"Transfer Unsuccessfull\"}");
								response.setStatus(400);
		
							}
						}else { // Unauthorized request
							ServletUtilities.unauthorizedResponse(response);
						}
						break;
					
					default:
						response.sendError(404);
					}

				}else { // Not logged in
					response.getWriter().println("{\"message\": \"Please Login\"}");
					response.setStatus(401);
				}
			}
				

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
