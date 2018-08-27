
import java.net.*;
import java.io.*;

public class TcpClient{

		public static void main(String [] args){
	
	
		String messageIn = "";
		String messageOut = "";
		DataInputStream din = null;
		DataOutputStream dout =null;
		Socket ss =null;
		try{
		
		
		ss= new Socket("localhost",1872);
		
		 din = new DataInputStream(ss.getInputStream());
		  dout = new DataOutputStream(ss.getOutputStream());
		
		
		}catch(IOException e){
		System.out.println("Error In Establishing Connection");
		System.out.println(e.toString());
		}
		
		
		try{
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("***Enter Quit if you want to close the chat****");
		
		
		while(true){
		
		//System.out.println("Waiting For CLient....");
		System.out.print("Send :");
		messageOut = buff.readLine();
		if(messageOut.equalsIgnoreCase("Quit")){
			
			dout.writeUTF(messageOut);
			dout.flush();
			ss.close();
			System.out.println("Connection is Closed ");
			break;
					
		}
		dout.writeUTF(messageOut);
		dout.flush();
		
		
		messageIn = din.readUTF();
		
		
		if(messageIn.equalsIgnoreCase("Quit")){
			
			System.out.println("Connection is Closed at client side ;");
			ss.close();
					
		}
		System.out.println("Recieved :" +messageIn);
		
		
		
		
		
		
			
		}
		
		}catch(IOException e){
		
		e.printStackTrace();
		
		}
	}




}
