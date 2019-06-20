package servlet;

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
		

		ObjectOutputStream out; 
		ObjectInputStream in; 

		response.getWriter().println("<h1>INSIDE AUTOLIST</h1>");
		try {
			Socket clientSocket = new Socket("10.44.3.26", 4500);

			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());	
			in.readObject();
			out.writeObject(2);
			System.out.println(in.readObject().toString());
			
			
		} catch(IOException e) {
			System.err.println("Error obtaining I/O for connection to host ... ");
			System.exit(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
