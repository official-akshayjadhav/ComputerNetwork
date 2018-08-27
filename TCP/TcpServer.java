
import java.net.*;
import java.io.*;

public class TcpServer{

	public static void main(String [] args){
	
	
		String messageIn ="";
		String messageOut ="";
		DataInputStream din = null;
		DataOutputStream dout = null;
		Socket ss =null;
		try{
		
		ServerSocket s = new ServerSocket(1872);
		 ss= s.accept();
		s.close();
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
		
		System.out.println("Waiting For CLient....");
		messageIn = din.readUTF();
		if(messageIn.equalsIgnoreCase("Quit")){
			
			System.out.println("Connection is Closed at client side ;");
			ss.close();
					
		}
		System.out.println("Recieved :" +messageIn);
		
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
		
		
			
		}
		
		}catch(IOException e){
		
		e.printStackTrace();
		
		}
	}


}
