package edu.tce.it.StudentServletApp.DBBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnector {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";

	// Database credentials
	static final String USER = "system";
	static final String PASS = "custard";
	private Connection conn = null;
	private Statement stmt = null;
	
	private DataBaseConnector(){
		
	}
	
	public ResultSet executeSelectSQL(String sql) throws Throwable{
		ResultSet rs = null;
		if (conn == null) {
			try {
				Class.forName(JDBC_DRIVER);
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
			}catch (SQLException se){
				throw new Throwable("SQLException Exception");
			}catch (Exception e) {
				throw new Throwable("Connection Exception");
			}
		}
		String[] columns = getColumns(sql);
		while(rs.next()){
			
		}
		return rs;
	}

	private String[] getColumns(String sql) {
		String c = 
		return null;
	}
}
