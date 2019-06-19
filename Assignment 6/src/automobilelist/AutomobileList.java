package automobilelist;

import java.io.IOException;
import client.*;
import adapter.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AutomobileList
 */
@WebServlet("/AutomobileList")
public class AutomobileList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		// come here after landing page. purpose is to get a list of available automobile
		// and return to the user
		
		// purpose of this servlet is to interact with the client program from Assignment 5
		// creating a client interface 
		AutoClientable client = new BuildAuto();
		// start the client 
		client.startClient("10.44.3.26", 4500);
		
		
		
		
	}

}
