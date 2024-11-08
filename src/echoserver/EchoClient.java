package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
    public static final int PORT_NUMBER = 6013;

    public static void main(String[] args) throws IOException {
        EchoClient client = new EchoClient();
        client.start();
    }
	//it will start with a clinet and takes care of data and tranvels between the server
    private void start() throws IOException {
        Socket socket = new Socket("localhost", PORT_NUMBER);
        InputStream socketInputStream = socket.getInputStream();
        OutputStream socketOutputStream = socket.getOutputStream();
		//will send the data from system.in into the server

        goIntoServer(System.in, socketOutputStream);

		//will tell the server that all info has been sent
        socket.shutdownOutput();
		//will receive data from server and system.out
        gotByServer(socketInputStream, System.out);
		//will close the socket
        socket.close();
        socketInputStream.close();


        socketOutputStream.close();
    }

	//it will read from the steam input and writes the output steam

    private void goIntoServer(InputStream input, OutputStream output) throws IOException {
    	for (int byteData = input.read(); byteData != -1; byteData = input.read()) {
        	output.write(byteData);
    }
    output.flush(); 
}
	//it reads the data and flushes the output
	private void gotByServer(InputStream input, OutputStream output) throws IOException {
    	for (int byteData = input.read(); byteData != -1; byteData = input.read()) {
        	output.write(byteData);
    }
    output.flush(); 
}

}
