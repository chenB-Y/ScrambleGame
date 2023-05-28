package Model;

import java.util.List;

import test.Tile;

public interface GameModel {
	void openServer();
    void closeServer();
    void connectToServer(String serverAddress);
    void sendRequest(String request);
    void updateBoard(List<Tile> board);
}	
