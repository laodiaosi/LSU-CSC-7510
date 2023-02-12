

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

import java.io.PrintWriter;
/**
 * Servlet implementation class Retrieve
 */
@WebServlet("/Retrieve")
public class Retrieve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Retrieve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		 response.setContentType("text/html; charset = utf-8");
		 PrintWriter out = response.getWriter();
		 String docType = "<!doctype html>\n";
	      String studentname = request.getParameter("studentName");
	     // JDBC driver name and database URLString JDBC_DRIVER = "com.mysql.jdbc.Driver";
	      //String DB_URL = "jdbc:mysql://52.26.86.130:3306/student";
	      String DB_URL = "jdbc:mysql://3.143.220.151:3306/testDB";
	      // Database credentials
	      String USER = "zsui1";
	      String PASS = "1";
	      Connection conn = null;

	      //STEP 2: Register JDBC driver
	      out.println();
	      try { 
	    	  String sql;
		      sql = "SELECT * FROM student WHERE name=(?)";
	      Class.forName("com.mysql.jdbc.Driver");
	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      PreparedStatement stmt = conn.prepareStatement(sql);
	      stmt.setString(1,studentname );
	      ResultSet rs = (ResultSet) stmt.executeQuery();
	      //STEP 5: Extract data from result set
	      out.println(docType+"</html>");
	      while(rs.next()){
	      //Retrieve by column name
	      String myName = rs.getString("name");
	      String myEmail = rs.getString("email");
	      String myLocation = rs.getString("location");
	      String myGender = rs.getString("gender");
	      String myBirth = rs.getString("birth");
	      String myExperience = rs.getString("experience");
	      //Display values
	      System.out.print("name: " + myName);
	      System.out.println(", experience: " + myExperience);
//	      return the query results to client
//	      name = name + "-" + myName;
//	      experience = experience + "-" + myExperience;
//	      System.out.print("name: " + myName);
////	      System.out.print("email: " + myEmail);
//	      System.out.print("experience: " + myLocation);
	    	out.println(
	    			"<head><title>User retreive</title></head>\n"+
	    			"<body>\n"+
	    			"<h1 align=\"center\">Welcome "+ myName +", the following is your profile info:</h1>"+
	    			//change to real ip
	    			"<img src=\'myProPic.jpg\'>"+"\n"+
	    			"<ul>\n"+
	    			" <li><b>Your name</b>: " + myName+"\n"+
	    			" <li><b>Your email</b>: " + myEmail+"\n"+
	    			" <li><b>Your location</b>: " + myLocation+"\n"+
	    			" <li><b>Your gender</b>: " + myGender+"\n"+
	    			" <li><b>Your gender</b>: " + myBirth+"\n"+
	    			" <li><b>Your experience</b>: " + myExperience+"\n"+
//	    			"<li><b>Your profile picture myProPic.jpg has been upload successful</b>:"+"\n"+
	    			"</ul>\n"
//	    			//tomcat cannot know the absolute file path
////	    			file:///C:/Users/eclipse-workspace/UserRegistrationProject/WebContent/picture.jpg
////	    			http://localhost:8080/UserRegistrationProject/WebContent/picture.jpg
////	    			C:/Users/eclipse-workspace/UserRegistrationProject/WebContent/picture.jpg
	    			);
	      }
	      out.println("</body></html>");
	      rs.close();
	      stmt.close();
	      conn.close();
	      } catch (ClassNotFoundException | SQLException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
