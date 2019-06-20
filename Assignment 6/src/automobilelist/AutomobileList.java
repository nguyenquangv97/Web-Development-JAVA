package automobilelist;

import java.io.*;
import java.net.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Automobile;
import client.CarModelOptionsIO;
import client.SelectCarOptions;


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
		response.getWriter().println("<h1>INSIDE AUTOLIST</h1>");

		InputHelper helper = new InputHelper();
		helper.setToServer(2);
		WebClientSocket client = new WebClientSocket(helper);
		client.establishConnection();
		System.out.println(client.getListFromServer());
		
		
		

		
		
		
	}
	
	
	public Socket createSocket () throws IOException {
		Socket clientSocket = new Socket("10.44.3.26", 4500);
		System.out.println("Socket created on port " + 4500);
		return clientSocket;
	}


}
