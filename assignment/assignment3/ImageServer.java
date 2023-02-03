package asign275101;
//package socket.test1;


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
            String str,str1;
            byte[] buffer = new byte[1024];
			/* Detailed requirement below*/	
			//Step one: check the picture name sent from the client, if the picture name equals "Koala.jpg", go to step two, otherwise go to step three
            if ((str = in.readLine()) == "Koala.jpg") {
                //Step two, read the picture "Koala.jpg" from the local disk, and send the content back to the client. 
            	FileInputStream getFileStream = new FileInputStream(str);
            	int count;
            	while((count = getFileStream.read(buffer)) > 0) {
            		out.write(buffer,0,count);
            	}
            	getFileStream.close();
            }else {
                //step three, then reply to the client with "Sorry, no such picture",
            	str1 = "Sorry, no such picture";
                out.writeBytes(str1);
            }
            
            //step four, close the input/output streams, close the socket.
            in.close();
            out.close();
            s.close();
         }
       } catch (Exception e) { e.printStackTrace(); }
   }
