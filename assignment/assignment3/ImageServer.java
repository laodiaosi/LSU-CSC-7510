
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ImageServer {
   public static void main(String[] args) {
       try {
         ServerSocket server = new ServerSocket(8008);
         
         while (true) {  
			System.out.println("server is waiting for connection request from clients");
            Socket s = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                s.getInputStream()));               
            DataOutputStream  out = new DataOutputStream (s.getOutputStream());  
           
            //get picture name
            //input filename version
//            InputStream input =s.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//            String filename = reader.readLine();
            String str1;
            String fileName = "Koala.jpg";
            byte[] buffer = new byte[4096];
			/* Detailed requirement below*/	
			//Step one: check the picture name sent from the client, if the picture name equals "Koala.jpg", go to step two, otherwise go to step three
            String str;
			if ((str = in.readLine()).equals(fileName)) {
                //Step two, read the picture "Koala.jpg" from the local disk, and send the content back to the client. 
            	File file = new File(fileName);
            	FileInputStream getFileStream = new FileInputStream(file);
            	int len = -1;
            	while((len = getFileStream.read(buffer)) > 0) {
            		out.write(buffer,0,len);
            	}
            	out.flush();
            	getFileStream.close();
            }else{
                //step three, then reply to the client with "Sorry, no such picture",
            	str1 = "Sorry, no such picture";
                out.writeBytes(str1);
//                out.write();
                out.flush();
            }
            
            //step four, close the input/output streams, close the socket.
            in.close();
            out.close();
            s.close();
         }

       } catch (Exception e) { e.printStackTrace(); }
   }
}
