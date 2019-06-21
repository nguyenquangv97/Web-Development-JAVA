package servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Automobile;

/**
 * Servlet implementation class CarConfiguration
 */
@WebServlet("/Configuration")
public class CarConfiguration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectOutputStream out = (ObjectOutputStream) request.getSession().getAttribute("outputStream");
		ObjectInputStream in = (ObjectInputStream) request.getSession().getAttribute("inputStream");
		String carName = request.getParameter("selection");
		
		Automobile car = null;
		
		out.writeObject(carName);
		try {
			car = (Automobile) in.readObject();

		} catch(ClassNotFoundException e) {
			System.out.println("Error in downloaded object");
		}
		
		request.getSession().setAttribute("automobile", car);
		
		System.out.println(car);
		
		response.sendRedirect("Configuration.jsp");
		
		System.out.println(carName);
	}

}
