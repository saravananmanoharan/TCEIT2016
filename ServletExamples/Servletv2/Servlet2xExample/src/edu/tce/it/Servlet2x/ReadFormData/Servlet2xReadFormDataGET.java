package edu.tce.it.Servlet2x.ReadFormData;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet2xReadFormDataGET
 * 
 * Note, the url http://localhost:8080/Servlet2xExample/Servlet2xReadFormDataGET?firstName=Sachin&lastName=Tendulkar 
 */

public class Servlet2xReadFormDataGET extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet2xReadFormDataGET() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get an array of Cookies associated with this domain
		Cookie[] cookies = request.getCookies();
		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		StringBuilder myResponse =  new StringBuilder();
		String title = "Using GET Method to Read Form Data";
		myResponse.append("<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">");
		myResponse.append("<html>");
		myResponse.append("<head>");
		myResponse.append("<title>").append(title).append("</title>");
		myResponse.append("</head>");
		myResponse.append("<body>");
		myResponse.append("<p>Parameters from GET request</p>");
		//code to use getParameter
/*		myResponse.append("<ul>");
		myResponse.append("<li><b>First Name</b>:");
		myResponse.append(request.getParameter("firstName"));
		myResponse.append("<li><b>Last Name</b>:");
		myResponse.append(request.getParameter("lastName"));
		myResponse.append("</ul>");
*/		
		//code to use the parameter Map
		myResponse.append("<ul>");
		for(Entry<String, String[]> entry:request.getParameterMap().entrySet()){
			myResponse.append("<li><b>"+entry.getKey()+"</b>:");
			for(String value: entry.getValue()){
				myResponse.append(value+"|");
			}
		}
		myResponse.append("</ul>");
		myResponse.append("<table border=\"1\">");
		for(int i =0 ; i < cookies.length ; i++){
			myResponse.append("<tr>");
			myResponse.append("<td>"+cookies[i].getName()+"</td>");
			myResponse.append("<td>"+cookies[i].getValue()+"</td>");
			myResponse.append("</tr>");
		}
		myResponse.append("</table>");
		myResponse.append("</body>");
		myResponse.append("</html>");

		out.println(myResponse.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
