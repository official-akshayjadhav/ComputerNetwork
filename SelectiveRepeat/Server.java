import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

public class Server {
	static ServerSocket Serversocket;
	static DataInputStream dis;
	static DataOutputStream dos;

	public static void main(String[] args) throws SocketException {

		try {
			int a[];
			Random randomNumberGenerator = new Random();
			Serversocket = new ServerSocket(8011);
			System.out.println("waiting for connection");
			Socket client = Serversocket.accept();
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
			int frames = dis.read();
			System.out.println("Client has requested for " + frames + " frames");
			a = new int[frames];
			for(int i = 0; i < frames; i++){
				int value = randomNumberGenerator.nextInt(101);
				a[i] = value;
			}
			
			for (int i = 0; i < a.length; i++) {
				System.out.println("Sending " + a[i]);
				dos.write(a[i]);
				dos.flush();
			}

			int k = dis.read();
			System.out.println("\n\n");
			System.out.println("Sending re-transmission frame value: " + a[k]);
			dos.write(a[k]);
			dos.flush();

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				dis.close();
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
