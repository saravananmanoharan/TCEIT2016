package edu.tce.it.Servlet2x.ReadFormData;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet2xReadFormDataPOST
 */
public class Servlet2xReadFormDataPOST extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet2xReadFormDataPOST() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		StringBuilder myResponse = new StringBuilder();
		String title = "Using POST Method to Read Form Data";
		myResponse.append("<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">");
		myResponse.append("<html>");
		myResponse.append("<head>");
		myResponse.append("<title>").append(title).append("</title>");
		myResponse.append("</head>");
		myResponse.append("<body>");
		myResponse.append("<p>Parameters from POST request</p>");
		// code to use getParameter
		/*
		 * myResponse.append("<ul>");
		 * myResponse.append("<li><b>First Name</b>:");
		 * myResponse.append(request.getParameter("firstName"));
		 * myResponse.append("<li><b>Last Name</b>:");
		 * myResponse.append(request.getParameter("lastName"));
		 * myResponse.append("</ul>");
		 */
		// code to use the parameter Map
		myResponse.append("<ul>");
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			myResponse.append("<li><b>" + entry.getKey() + "</b>:");
			for (String value : entry.getValue()) {
				myResponse.append(value + "|");
			}
		}
		myResponse.append("</ul>");
		myResponse.append("</body>");
		myResponse.append("</html>");

		out.println(myResponse.toString());
	}

}
