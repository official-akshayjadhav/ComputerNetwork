import java.lang.System;
import java.util.*;
import java.net.*;
import java.io.*;

public class Client {
        static Socket connection;

	public static void main(String a[]) throws SocketException {
		try {
		        Random randomNumberGenerator = new Random(); 
			int v[];
			int n = 0;;
			Scanner sc =  new Scanner(System.in);
			//int g[] = new int[8];
			InetAddress addr = InetAddress.getByName("Localhost");
			System.out.println(addr);
			connection = new Socket(addr, 8011);
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			DataInputStream in = new DataInputStream(
					connection.getInputStream());
					
			System.out.print("Enter the no of frames: ");
			int p = sc.nextInt();
			System.out.println("No of frame is:" + p);
			v =  new int[p];
			out.write(p);
			out.flush();

			for (int i = 0; i < p; i++) {
				v[i] = in.read();
				System.out.println(v[i]);
				//g[i] = v[i];
			}
			int value = randomNumberGenerator.nextInt(p);
			v[value] = -1;
			for (int i = 0; i < p; i++)
			 {
					System.out.println("Received frame is: " + v[i]);

				}
			for (int i = 0; i < p; i++)
				if (v[i] == -1) {
					System.out.println("Request to retransmit from packet no "
							+ (i+1) + " again!!");
					n = i;
					out.write(n);
					out.flush();
				}

			System.out.println();
			
				v[n] = in.read();
				System.out.println("Received frame is: " + v[n]);
			
			

			System.out.println("quiting");
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}

