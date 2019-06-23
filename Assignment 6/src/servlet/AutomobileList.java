package servlet;

import java.io.*;
import java.net.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Automobile;
import client.CarModelOptionsIO;
import client.SelectCarOptions;


/**
 * Servlet implementation class AutomobileList
 */
@WebServlet("/AutomobileList")
public class AutomobileList extends HttpServlet implements Interactionable{
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
		Socket clientSocket;
		String carList = "";
		HttpSession session = request.getSession();
		try {
			clientSocket = new Socket("192.168.1.78", 4500);

			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());	
			
			retrieveObject(in);
			sendRequest(out, 2);
			
			
			carList = retrieveObject(in).toString();
			
			
			session.setAttribute("inputStream", in);
			session.setAttribute("outputStream", out);
			session.setAttribute("CarList", carList);
			
			
		} catch(IOException e) {
			System.err.println("Error obtaining I/O for connection to host ... ");
			System.exit(1);
		}
		
		response.sendRedirect("CarList.jsp");
	}

	@Override
	public Object retrieveObject(ObjectInputStream in) {
		Object returned = null;
		try {
			returned = in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error reading object");
		}
		return returned;
	}

	@Override
	public void sendRequest(ObjectOutputStream out, Object togo) {
		try {
			out.writeObject(togo);
		} catch (IOException e) {
			System.err.println("Error sending request");
		}		
	}
}
