package servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adapter.BuildAuto;
import adapter.Choice;
import adapter.CreateAuto;
import model.*;

/**
 * Servlet implementation class CarConfiguration
 */
@WebServlet("/Configuration")
public class CarConfiguration extends HttpServlet implements Interactionable {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectOutputStream out = (ObjectOutputStream) request.getSession().getAttribute("outputStream");
		ObjectInputStream in = (ObjectInputStream) request.getSession().getAttribute("inputStream");
		String carName = request.getParameter("selection");
		
		sendRequest(out, carName);
			
		Automobile car = (Automobile) retrieveObject(in);
		
		System.out.println(car);	// print to the console to debug (making sure the car is what the user wants)
		
		CreateAuto createAuto = new BuildAuto();
		createAuto.insertAutoLinkedHashMap(car);
		Choice carChoice = new BuildAuto();
		
		request.getSession().setAttribute("carChoice", carChoice);
		
		request.getSession().setAttribute("automobile", car);
		

		response.sendRedirect("Configuration.jsp");
	}
	@Override 
	public void sendRequest(ObjectOutputStream out, Object togo) {
		try {
			out.writeObject(togo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error Sending Object");
		}
	}

	@Override
	public Object retrieveObject(ObjectInputStream in) {
		Automobile car = null;
		try {
			car = (Automobile) in.readObject();

		} catch(ClassNotFoundException e) {
			System.err.println("Error in downloaded object");
		} catch (IOException e) {
			System.out.println("Error reading object!");
		}
		return car;
	}
	

}
