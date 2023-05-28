package test;

import java.io.*;

public class BookScrabbleHandler implements ClientHandler {
    private DictionaryManager dictionaryManager;

    public BookScrabbleHandler() {
        // Initialize the dictionaryManager field with a default value
        this.dictionaryManager = new DictionaryManager();
    }

    public BookScrabbleHandler(DictionaryManager dictionaryManager) {
        this.dictionaryManager = dictionaryManager;
    }

    @Override
    public void handleClient(InputStream input, OutputStream output) {
        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            reader = new BufferedReader(new InputStreamReader(input));
            writer = new PrintWriter(output, true);

            String request = reader.readLine();
            if (request == null || request.isEmpty()) {
                throw new IllegalArgumentException("Invalid request: Empty input");
            }
            String[] parts = request.split(",", 2);
            if (parts.length < 2) {
                throw new IllegalArgumentException("Invalid request: " + request);
            }
            String command = parts[0];
            String[] books = parts[1].split(",");
            if (books.length < 1) {
                throw new IllegalArgumentException("Invalid request: " + request);
            }
            if (!command.equals("Q") && !command.equals("C")) {
                throw new IllegalArgumentException("Invalid command: " + command);
            }

            boolean result;
            if (command.equals("Q")) {
                result = dictionaryManager.query(books);
            } else {
                result = dictionaryManager.challenge(books);
            }
            writer.println(result ? "true" : "false");
        } catch (IllegalArgumentException e) {
            writer.println("Error: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void close() {
        // Close any resources that the BookScrabbleHandler class is using
    }

}