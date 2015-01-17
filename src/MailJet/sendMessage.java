package MailJet;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MailJet.SendEmailAction;

/**
 * Servlet implementation class sendMessage
 */
@WebServlet("/sendMessage")
public class sendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Postprocess request: gather and validate submitted data and display result in same JSP.

        // Prepare messages.
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        
		String zipCode = request.getParameter("zipCode");
		String emailID = request.getParameter("emailID");
		String highTemp = request.getParameter("highTemp");
		String lowTemp = request.getParameter("lowTemp");
		
		System.out.println(zipCode + " zipCode");
		System.out.println(emailID + " email");
		System.out.println(highTemp + " high");
		System.out.println(lowTemp + " low");
		
		SendEmailAction.sendMail(zipCode, emailID, highTemp, lowTemp);
		//response.sendRedirect("index.html");
		
		messages.put("success", String.format("Success!, an email will be sent to %s if the current temperature at %s goes above %s or below %s.", emailID, zipCode, highTemp, lowTemp));
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
