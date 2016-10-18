package edu.tce.it.Servlet2x.ReadHeaders;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet2xReadHeaders
 */
public class Servlet2xReadHeaders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		StringBuilder myResponse =  new StringBuilder();
		String title = "Read Headers";
		myResponse.append("<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">");
		myResponse.append("<html>");
		myResponse.append("<head>");
		myResponse.append("<title>").append(title).append("</title>");
		myResponse.append("</head>");
		myResponse.append("<body>");
		myResponse.append("<p>Parameters from Header</p>");
		myResponse.append("<table>");
		
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String paramName = (String) headerNames.nextElement();
			String paramValue = request.getHeader(paramName);
			myResponse.append("<tr>");
			myResponse.append("<td>" + paramName  + "</td>");
			myResponse.append("<td>" + paramValue + "</td>");
			myResponse.append("</tr>\n");
		}
		myResponse.append("</table>");
		myResponse.append("</body>");
		myResponse.append("</html>");

		out.println(myResponse.toString());
	}


}
