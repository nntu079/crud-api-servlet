package tu.testsql;


import java.util.*;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MySQLDemo
 */

@WebServlet("/test-mysql")
public class MySQLDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("application/json"
		 		+ "");
	        PrintWriter pw=response.getWriter();
	        //initializing connections
	        Connection con=null;
	        Statement stmt=null;
	        ResultSet rs=null;
	        
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testservlet","root","");
	            stmt =  con.createStatement();   
	            
	            rs =  stmt.executeQuery("select * from User");
	            
	            List<JSONObject> users = new ArrayList<JSONObject>();

	            
	            while (rs.next()) {
	            	JSONObject user = new JSONObject();
	            	user.put("name", rs.getString("Name"));
	            	user.put("email", rs.getString("Email"));
	            	users.add(user);
	            }
	            
	            pw.print( users);
	            
	            pw.close();
	        }// End of try block
	        catch(Exception e) {e.printStackTrace();}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
