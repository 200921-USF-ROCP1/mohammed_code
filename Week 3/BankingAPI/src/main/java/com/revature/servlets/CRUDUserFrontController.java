package com.revature.servlets;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.User;
import com.revature.services.AdminActionsService;
import com.revature.utilities.ServletUtilities;

public class CRUDUserFrontController extends HttpServlet {
	 AdminActionsService adminActions = new AdminActionsService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			String[] path = request.getRequestURI().split("/");
			ObjectMapper mapper = new ObjectMapper();
			
			HttpSession session = request.getSession();

			User currentUser = (User) session.getAttribute("currentUser");

			String responseUser;
			
			// If User is logged in, identify specific get request
			if (currentUser != null) { 
				
				// If there is no ID, it is a getAll
				if (path.length == 3) {
					
					//User must be Admin or an Employee
					if (ServletUtilities.employeeOrAdmin(currentUser)) {

						// First, we get the actual data
						List<User> users =  adminActions.getUsers();
						
						// Finally, we convert our list into JSON and write it to the body
						response.getWriter().println(mapper.writeValueAsString(users));
					}else {
						ServletUtilities.unauthorizedResponse(response);
					}
					
				} else if (path.length == 4) {
					
					// Otherwise, the last element is an id
					if (ServletUtilities.isInteger(path[3])) {
						int id = Integer.parseInt(path[3]);
						
						User user = adminActions.getUserById(id);
						
						response.getWriter().println(mapper.writeValueAsString(user));
					} else {
						// do some error handling
						response.getWriter().println("Invalid input");
					}
				}
			}
			//User not logged in, send message and 401 status
			else {
				ServletUtilities.unauthorizedResponse(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			response.setStatus(500);
		}
	}
	
	public void doPut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		User currentUser = (User) session.getAttribute("currentUser");
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(request.getReader(), User.class);
			
			// If role is Admin or if the id of user matches the id of the current user
			if (ServletUtilities.currentUserOrAdmin(currentUser, user.getId())) {
				User updatedUser = adminActions.updateUser(user);
				response.getWriter().println(mapper.writeValueAsString(updatedUser));
				
			}else {
				ServletUtilities.unauthorizedResponse(response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}