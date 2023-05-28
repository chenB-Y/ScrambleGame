package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import test.BookScrabbleHandler;
import test.ClientHandler;

public class HostHandler implements ClientHandler{

    private String playerName;
    private BookScrabbleHandler bookScrabbleHandler;

    public void handleClient(InputStream inputStream, OutputStream outputStream) {
    	 try {
             // Read player name from input stream until a comma is encountered
             StringBuilder playerNameBuilder = new StringBuilder();
             int character;
             while ((character = inputStream.read()) != -1) {
                 if (character == ',') {
                     break;
                 }
                 playerNameBuilder.append((char) character);
             }
             String playerName = playerNameBuilder.toString();

             // Pass the modified data to the BookScrabbleHandler
             byte[] dataWithoutPlayerName = playerName.getBytes();
             bookScrabbleHandler.handleClient(new DummyInputStream(dataWithoutPlayerName), outputStream);
             
         } catch (IOException e) {
             e.printStackTrace();
         }
    }


    public void close() {
        // Close any necessary resources
    }
}