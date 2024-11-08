package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	// REPLACE WITH PORT PROVIDED BY THE INSTRUCTOR
    public static final int PORT_NUMBER = 6013;
    public static void main(String[] args) throws IOException, InterruptedException {
        EchoServer server = new EchoServer();
        server.start();
    }

    private void start() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);

		//will accept the client connections
        while (true) {
            Socket clientSocket = serverSocket.accept();
			//it creates a new thread for the client
            Thread clientManage = new Thread(() -> ManagesClient(clientSocket));
			//start the clients thread
            clientManage.start();
        }
    }
	//it manges the client that is connected
    private void ManagesClient(Socket clientSocket) {
    try {
        InputStream input = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();
		//goes back and forth with data to the clients 
        for (int byteData; (byteData = input.read()) != -1;) {
            if (byteData != -1) {
                output.write(byteData);
            } else {
                break;
            }
        }
        output.flush(); 

        clientSocket.close();

    } catch (IOException e) {
    }
}
}