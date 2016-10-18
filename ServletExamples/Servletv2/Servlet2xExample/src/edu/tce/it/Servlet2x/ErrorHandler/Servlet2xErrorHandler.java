package edu.tce.it.Servlet2x.ErrorHandler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet2xErrorHandler
 */
public class Servlet2xErrorHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet2xErrorHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		StringBuilder myResponse =  new StringBuilder();
		String title = "Error Handeling";
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null){
			requestUri = "Unknown";
		}
		myResponse.append("<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">");
		myResponse.append("<html>");
		myResponse.append("<head>");
		myResponse.append("<title>").append(title).append("</title>");
		myResponse.append("</head>");
		if (throwable == null && statusCode == null){
			myResponse.append("<h2>Error information is missing</h2>");
			myResponse.append("Please return to the <a href=\"" + 
			response.encodeURL("http://localhost:8080/") + "\">Home Page</a>.");
		}else if (statusCode != null){
			myResponse.append("The status code : " + statusCode);
		}else{
			myResponse.append("<h2>Error information</h2>");
			myResponse.append("Servlet Name : " + servletName + "</br></br>");
			myResponse.append("Exception Type : " + throwable.getClass( ).getName( ) + "</br></br>");
			myResponse.append("The request URI: " + requestUri + "<br><br>");
			myResponse.append("The exception message: " + throwable.getMessage( ));
		}
		myResponse.append("<body>");
		myResponse.append("</body>");
		myResponse.append("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
