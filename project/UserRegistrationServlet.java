import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.PrintWriter;

/**
 * Servlet implementation class UserRegistrationServlet
 */
@WebServlet("/UserRegistrationServlet")
@MultipartConfig
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 final PrintWriter out = response.getWriter();
	        
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String location = request.getParameter("location");
	        String gender = request.getParameter("gender");
	        String experience = request.getParameter("experience");
	        String fileName = request.getParameter("fileName");
	        
	        /*	
	         * request.getPart is to get the uploaded file handler. 
	         * You can use filePart.getInputStream() to read the streaming data from client, for example:
	         * InputStream filecontent = filePart.getInputStream();
	        */
		    Part filePart = request.getPart("file");
		    InputStream filecontent = filePart.getInputStream();
		    
		    /*
		     * fileout is for you to save the uploaded picture in your local disk. 
		     * */
		    OutputStream fileout = new FileOutputStream("C:/Users/eclipse-workspace/UserRegistrationProject/WebContent/picture.jpg");
		    byte[] buffer = new byte[1024];
		    int len = -1;
		    while((len = filecontent.read(buffer)) >= 0) {
		    	fileout.write(buffer, 0, len);
		    }
//		    File file = new File("C:/Users/eclipse-workspace/UserRegistrationProject/WebContent/picture.jpg");
//		    OutputStream fileout = new FileOutputStream(file);

		    /*
		     * Write your code here
		     * Step 1: check whether the client's inputs are complete or not; if anything is missing, return a web page that contains a link to go back to the registration page (e.g., UserRegistration.html)
		     * Step 2: save the uploaded picture under your project WebContent directory, for example, mine is "F:\workspace\UserRegistrationProject\WebContent". 
		     * Step 3: send back the client's registration information to the client, remember, the client should be able to see all the information, including the profile picture. 
		     * */
		    //step1
		    if(name.isEmpty()|| email.isEmpty()|| location.isEmpty()|| gender== null|| experience.isEmpty()|| fileName.isEmpty()||filePart==null) {
		    	String docType1 = "<!doctype html>\n";
		    	out.println(docType1 +
		    			"<html>\n"+
		    			"<body>\n"+
		    			"<p>"+"Your input: information is not complete,try again"+"</p>"+"<a href="+"UserRegistration.html"+">"+"go back"+"<a>"+"\n"+
		    			"</body></html>");
		    }else{
		    	String docType = "<!doctype html>\n";
		    	out.println(docType +
		    			"<html>\n"+
		    			"<head><title>User registration</title></head>\n"+
		    			"<body>\n"+
		    			"<h1 align=\"center\">Welcome "+ name +"</h1>"
		    			+"<ul>\n"+
		    			" <li><b>Your name</b>: " + name+"\n"+
		    			" <li><b>Your email</b>: " + email+"\n"+
		    			" <li><b>Your location</b>: " + location+"\n"+
		    			" <li><b>Your gender</b>: " + gender+"\n"+
		    			" <li><b>Your experience</b>: " + experience+"\n"+
		    			"<li><b>Your profile picture myProPic.jpg has been upload successful</b>:"+"\n"+
		    			"</ul>\n"+
		    			//tomcat cannot know the absolute file path
//		    			file:///C:/Users/eclipse-workspace/UserRegistrationProject/WebContent/picture.jpg
//		    			http://localhost:8080/UserRegistrationProject/WebContent/picture.jpg
//		    			C:/Users/eclipse-workspace/UserRegistrationProject/WebContent/picture.jpg
                        "<img src=\"/UserRegistrationProject/picture.jpg\">"+"\n"+
		    			"</body></html>");
		    }
		    
		    fileout.close();
		    filecontent.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
