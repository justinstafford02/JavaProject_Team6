package com.tacocardgame.view;

import com.tacocardgame.model.*;
import java.util.Collection;

public class BoardView {

    public BoardView(Board board) {

    }

    public BoardView() {

    }

    public void showLoser(Player loser) {
        System.out.println("Loser of the slap: " + loser.getName());
    }

    public void displayTurnInfo(String playerName, String playerStates, String asciiArt) {
        System.out.println(playerName + " says: " + playerStates);
        System.out.println(playerName + " flips a card and reveals: " + asciiArt);
    }

    public void show(Collection<Player> players) {


        // Title and headings
        StringBuilder display = new StringBuilder();
        display.append("\nPlayer Information\n");
        display.append("========================\n");
        display.append("\n");
        display.append("Player Name      #ofCards\n");
        display.append("-----------        ----\n");

        // Player information
        if (players.isEmpty()) {
            display.append("\nThere are currently no players to show\n");
        } else {
            for (Player player : players) {
                String row = String.format("%-15s %4d\n",
                        player.getName(), player.getPlayerHand().size());
                display.append(row);
            }
        }

    }
//    BoardView boardView = new BoardView();
//    boardView.showLoser(lastToSlap);
}
