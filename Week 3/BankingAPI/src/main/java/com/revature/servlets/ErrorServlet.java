package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
	      // Set response content type so it will always render to HTML
	      response.setContentType("text/html");
	      
	      // Do error stuff here!
	      Error error = (Error) request.getAttribute("javax.servlet.error");
	      int code = (Integer) request.getAttribute("javax.servlet.error.status_code");
	      String message = (String) request.getAttribute("javax.servlet.error.message");
	      
	      PrintWriter pw = response.getWriter();
	      
	      pw.println(message);
	     

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}