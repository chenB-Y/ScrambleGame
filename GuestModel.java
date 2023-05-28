package Model;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import test.Tile;

public class GuestModel extends Player implements GameModel {
	
	 private Socket socket;
	 private String serverAddress;
	 private int port;
	public GuestModel(int id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void openServer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeServer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectToServer(String serverAddress) {
		 this.serverAddress = serverAddress;
	        this.port = port;
	        try {
	            // Connect to the server using the specified IP address and port
	            socket = new Socket(serverAddress, port);
	            System.out.println("Connected to server: " + serverAddress + ":" + port);
	            
	            // Perform any necessary communication with the server here
	            
	        } catch (IOException e) {
	            System.err.println("Error connecting to the server: " + e.getMessage());
	        }
	}

	@Override
	public void sendRequest(String request) {
		try {
            // Get the output stream of the socket to send data to the host
            OutputStream outputStream = socket.getOutputStream();
            
            // Convert the request to bytes and send it to the host
            byte[] requestData = request.getBytes();
            outputStream.write(requestData);
            outputStream.flush();
            
            // Optionally, you can wait for a response from the host
            // by reading from the input stream of the socket
            // InputStream inputStream = socket.getInputStream();
            // Read the response from the host here
            
        } catch (IOException e) {
            System.err.println("Error sending request: " + e.getMessage());
        }
		
	}

	@Override
	public void updateBoard(List<Tile> board) {
		// TODO Auto-generated method stub
		
	}

}
