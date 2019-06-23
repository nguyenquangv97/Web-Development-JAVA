package servlet;

import java.io.IOException;
import model.*;
import adapter.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetChoice
 */
@WebServlet("/SetChoice")
public class SetChoice extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Choice carChoice = (Choice) request.getSession().getAttribute("carChoice");
		
		Automobile car = (Automobile) request.getSession().getAttribute("automobile");
		
		for(int i = 0; i < car.getOpset().size(); i++) {
			String currentChoice = (String) request.getParameter("set" + i);
			carChoice.setOptionChoice(car.getOpsetName(i), currentChoice.split("\\s*,\\s*")[0]);
		}
		response.sendRedirect("Result.jsp");
	}

}
