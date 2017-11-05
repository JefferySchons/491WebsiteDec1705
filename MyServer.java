// GOALS
//
// 1. to show sample Server code
// Note that the server is running on LOCALHOST (which is THIS computer) and the 
// port number associated with this server program is 4444.
// The accept() method just WAITS until some client program tries to access this server
//
// 2. to show how a thread is created to handle client request
//

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextArea;

public class MyServer {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null;
		int clientNum = 0; // keeps track of how many clients were created
		
		int port=4444;
		//int port=5000;

		// 1. CREATE A NEW SERVERSOCKET
		try {
			
			serverSocket = new ServerSocket(port); // provide MYSERVICE at port
													// 4444
			System.out.println(serverSocket);
		} catch (IOException e) {
			System.out.println("Could not listen on port:"+ port);
			System.exit(-1);
		}

		// 2. LOOP FOREVER - SERVER IS ALWAYS WAITING TO PROVIDE SERVICE!
		while (true) {
			Socket clientSocket = null;
			try {

				// 2.1 WAIT FOR CLIENT TO TRY TO CONNECT TO SERVER
				System.out.println("Waiting for client " + (clientNum + 1)
						+ " to connect!");
				clientSocket = serverSocket.accept();

				// 2.2 SPAWN A THREAD TO HANDLE CLIENT REQUEST
				System.out.println("Server got connected to a client"
						+ ++clientNum);
				Thread t = new Thread(new ClientHandler(clientSocket, clientNum));
				t.start();

			} catch (IOException e) {
				System.out.println("Accept failed: 4444");
				System.exit(-1);
			}

			// 2.3 GO BACK TO WAITING FOR OTHER CLIENTS
			// (While the thread that was created handles the connected client's
			// request)

		} // end while loop

	} // end of main method

} // end of class MyServer

class ClientHandler implements Runnable {
	Socket s; // this is socket on the server side that connects to the CLIENT
	int num; // keeps track of its number just for identifying purposes

	ClientHandler(Socket s, int n) {
		this.s = s;
		num = n;
	}

	// This is the client handling code
	public void run() {
		printSocketInfo(s); // just print some information at the server side about the connection
		Scanner in;
		
		try {
			// 1. USE THE SOCKET TO READ WHAT THE CLIENT IS SENDING
			in = new Scanner(s.getInputStream()); 
			String clientMessage = in.nextLine();
			
			if(clientMessage.substring(0,3).equals("got"))
			{
				System.out.println(" "  + clientMessage);
				
			}
			else if(clientMessage.substring(0,3).equals("ned"))
			{
				System.out.println(": "  + clientMessage);
				String waypoint=givewaypoints();
				
			}
			else if(clientMessage.substring(0,3).equals("way")) //send in waypoints
			{
				System.out.println(""  + clientMessage);
				String waypoint=clientMessage.substring(4);
				addwaypoint(waypoint);
			}
			else if(clientMessage.substring(0,3).equals("img")) //recive image send to process
			{
				System.out.println(" "  + clientMessage);
				String encoded= clientMessage.substring(4);
				String answer = toOpenCV(encoded);
				printOut(answer);
			}
				
				/*
				 * image
				 * img
				 * need waypoint
				 * way
				 * here are waypoints
				 * ned
				 * go to
				 * got
				 */
			
			
			// 2. PRINT WHAT THE CLIENT SENT
			System.out.println("Message from Client:"  + clientMessage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// This handling code dies after doing all the printing
	} // end of method run()

	private String givewaypoints() {
		// TODO Auto-generated method stub
		
	}

	private void addwaypoint(String waypoint) {
		// TODO Auto-generated method stub
		
	}

	private String toOpenCV(String encoded) {  //will contain opencv
		// TODO Auto-generated method stub
		//TODO decode encoded image
		//might need to case
		return "answer";
		
	}

	void printSocketInfo(Socket s) {
		System.out.print("Socket on Server " + Thread.currentThread() + " ");
		System.out.print("Server socket Local Address: " + s.getLocalAddress()
				+ ":" + s.getLocalPort());
		System.out.println("  Server socket Remote Address: "
				+ s.getRemoteSocketAddress());
	} // end of printSocketInfo
	
	void printOut(String output)
	{
		PrintWriter out;
		try {
			//.modify this to string from object to string
			ObjectOutputStream outStream = new ObjectOutputStream(s.getOutputStream());
			out.writeObject(output);
			outStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
} // end of class ClientHandler



