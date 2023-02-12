import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import java.sql.* ;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;
/**
 * Servlet implementation class UserRegistrationServlet
 */
@WebServlet("/normal")
@MultipartConfig
public class normal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public normal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		    response.setContentType("text/html; charset=utf-8");
		    PrintWriter out = response.getWriter();
	        //connect mysql user -u zsui1 -p 1
		    String docType = "<!doctype html>\n";
//		     String studentname = request.getParameter("studentName");
         
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String location = request.getParameter("location");
	        String gender = request.getParameter("gender");
	        String birth = request.getParameter("birth");
	        String experience = request.getParameter("experience");
            SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
            char genderFirstOne = gender.charAt(0);
            java.util.Date util_Date = null;
			try {
				util_Date = dataFormat.parse(birth);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            java.sql.Date sql_Date = new java.sql.Date( util_Date.getTime() );

			
	        
		    if(name.isEmpty()|| email.isEmpty()|| location.isEmpty()|| gender== null|| experience.isEmpty()) {
		    	
		    	out.println(docType +
		    			"<html>\n"+
		    			"<body>\n"+
		    			"<p>"+"Your input: information is not complete,try again"+"</p>"+"<a href="+"MainPage.html"+">"+"go back"+"<a>"+"\n"+
		    			"</body></html>");
		    }else{
		    	
		    	out.println(docType +
		    			"<html>\n"+
		    			"<head><title>User registration</title></head>\n"+
		    			"<body>\n"+
		    			"<h1 align=\"center\">Welcome "+ name +"</h1>"+
		    			//change to real ip
		    			"<img src=\'myProPic.jpg\'>"+"\n"+
		    			"<ul>\n"+
		    			" <li><b>Your name</b>: " + name+"\n"+
		    			" <li><b>Your email</b>: " + email+"\n"+
		    			" <li><b>Your location</b>: " + location+"\n"+
		    			" <li><b>Your gender</b>: " + gender+"\n"+
		    			" <li><b>Your experience</b>: " + experience+"\n"+
		    			"</ul>\n"+
      	    			"</body>"+"\n"
      	    			+ "</html>");
				   // JDBC driver name and database URLString JDBC_DRIVER = "com.mysql.jdbc.Driver";
			      //String DB_URL = "jdbc:mysql://52.26.86.130:3306/student";
			      String DB_URL = "jdbc:mysql://3.143.220.151:3306/testDB";
			      // Database credentials
			      String USER = "zsui1";
			      String PASS = "1";
			      Connection conn = null;
			      Statement stmt = null;
			    try { 
				      Class.forName("com.mysql.jdbc.Driver");
				      //STEP 3: Open a connection
				      System.out.println("Connecting to database...");
				      conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
				      //STEP 4: Execute a query
				      System.out.println("Creating statement...");
				      PreparedStatement st = conn.prepareStatement("insert into student values(?,?,?,?,?,?)");
				      st.setString(1,name);
				      st.setString(2, email);
				      st.setString(3, location);
				      st.setString(4, String.valueOf(genderFirstOne));
				      st.setDate(5,sql_Date);
	                  st.setString(6, experience);
				      st.executeUpdate();
					     
					      st.close();
					      conn.close();
					      } catch (ClassNotFoundException | SQLException e) {
					      // TODO Auto-generated catch block
					      e.printStackTrace();
					      }
		    }
//			   // JDBC driver name and database URLString JDBC_DRIVER = "com.mysql.jdbc.Driver";
//		      //String DB_URL = "jdbc:mysql://52.26.86.130:3306/student";
//		      String DB_URL = "jdbc:mysql://3.143.220.151:3306/testDB";
//		      // Database credentials
//		      String USER = "zsui1";
//		      String PASS = "1";
//		      Connection conn = null;
//		      Statement stmt = null;
//		    try { 
//			      Class.forName("com.mysql.jdbc.Driver");
//			      //STEP 3: Open a connection
//			      System.out.println("Connecting to database...");
//			      conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
//			      //STEP 4: Execute a query
//			      System.out.println("Creating statement...");
//			      PreparedStatement st = conn.prepareStatement("insert into student values(?,?,?,?,?,?)");
//			      st.setString(1,name);
//			      st.setString(2, email);
//			      st.setString(3, location);
//			      st.setString(4, String.valueOf(genderFirstOne));
//			      st.setDate(5,sql_Date);
//                  st.setString(6, experience);
//			      st.executeUpdate();
//				     
//				      st.close();
//				      conn.close();
//				      } catch (ClassNotFoundException | SQLException e) {
//				      // TODO Auto-generated catch block
//				      e.printStackTrace();
//				      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
