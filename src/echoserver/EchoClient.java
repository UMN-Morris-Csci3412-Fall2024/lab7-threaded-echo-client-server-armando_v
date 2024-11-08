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

    private void start() throws IOException {
        Socket socket = new Socket("localhost", PORT_NUMBER);
        InputStream socketInputStream = socket.getInputStream();
        OutputStream socketOutputStream = socket.getOutputStream();

        goIntoServer(System.in, socketOutputStream);

        socket.shutdownOutput();

        gotByServer(socketInputStream, System.out);

        socket.close();
        socketInputStream.close();
        socketOutputStream.close();
    }

    private void goIntoServer(InputStream input, OutputStream output) throws IOException {
    	for (int byteData = input.read(); byteData != -1; byteData = input.read()) {
        	output.write(byteData);
    }
    output.flush(); 
}

	private void gotByServer(InputStream input, OutputStream output) throws IOException {
    	for (int byteData = input.read(); byteData != -1; byteData = input.read()) {
        	output.write(byteData);
    }
    output.flush(); 
}

}
