package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    private int port;
    private ClientHandler clientHandler;
    private boolean isRunning;
    private ServerSocket serverSocket;

    public MyServer(int port, ClientHandler clientHandler) {
        this.port = port;
        this.clientHandler = clientHandler;
    }

    public void start() {
        isRunning = true;
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                serverSocket.setSoTimeout(1000);
                while (isRunning) {
                    Socket clientSocket = serverSocket.accept();
                    InputStream inFromClient = clientSocket.getInputStream();
                    OutputStream outToClient = clientSocket.getOutputStream();
                    clientHandler.handleClient(inFromClient, outToClient);
                }
            } catch (IOException e) {
                //System.out.println("11111");
            } finally {
                close();
            }
        }).start();
    }

    public void close() {
        isRunning = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            // log the exception
        }
    }
}