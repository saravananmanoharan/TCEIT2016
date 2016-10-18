package edu.tce.it.Servlet2x.SessionManagementExample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet2xSessionManagementExample
 */
public class Servlet2xSessionManagementExample extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet2xSessionManagementExample() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// Create a session object if it is already not created.
		HttpSession session = request.getSession(true);
		// Get session creation time.
		Date createTime = new Date(session.getCreationTime());
		// Get last access time of this web page.
		Date lastAccessTime = new Date(session.getLastAccessedTime());

		String title = "Welcome Back to my website";
		Integer visitCount = new Integer(0);
		String visitCountKey = new String("visitCount");
		String userIDKey = new String("userID");
		String userID = new String("ABCD");

		// Check if this is new comer on your web page.
		if (session.isNew()) {
			title = "Welcome to my website";
			session.setAttribute(userIDKey, userID);
		} else {
			visitCount = (Integer) session.getAttribute(visitCountKey);
			visitCount = visitCount + 1;
			userID = (String) session.getAttribute(userIDKey);
		}
		session.setAttribute(visitCountKey, visitCount);
		StringBuilder myResponse =  new StringBuilder();
		myResponse.append("<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">");
		myResponse.append("<html>");
		myResponse.append("<head>");
		myResponse.append("<title>").append(title).append("</title>");
		myResponse.append("</head>");
		myResponse.append("<body>");
		myResponse.append("<div align=\"center\">Session Infomation<div>");
		myResponse.append("<table border=\"1\" align=\"center\">");
		myResponse.append("<th>Session info</th><th>value</th>");
		myResponse.append("<tr><td>User ID</td><td>" + userID + "</td></tr>");
		myResponse.append("<tr><td>id</td><td>" + session.getId() + "</td></tr>");
		myResponse.append("<tr><td>Creation Time</td><td>" + createTime + "</td></tr>");
		myResponse.append("<tr><td>Last Access</td><td>" + lastAccessTime + "</td></tr>");
		myResponse.append("<tr><td>Number of visits</td><td>" + visitCount + "</td></tr>");
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
