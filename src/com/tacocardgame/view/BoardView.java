package com.tacocardgame.view;

import com.tacocardgame.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BoardView {

    public BoardView(Board board) {

    }

    public BoardView() {

    }

    public void displayTurnInfo(String playerName, String playerStates, String asciiArt) {
        System.out.println(playerName + " says: " + playerStates);
        System.out.println(playerName + " flips a card and reveals: ");
        System.out.println(asciiArt);
    }

    public void displaySlapTimes(Map<Player, Long> playerSlapTimes) {
        // Sort the entries by value (slap time)
        List<Map.Entry<Player, Long>> sortedEntries = new ArrayList<>(playerSlapTimes.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue());

        System.out.println("\nSlap Results:");
        System.out.println("---------------------");

        // shows all player names and slap time
        for (Map.Entry<Player, Long> entry : sortedEntries) {
            System.out.printf("%s: %.2f seconds\n", entry.getKey().getName(), entry.getValue() / 1000.0);
        }
    }

    public void showLoser(Player loser, int pileCardCount) {
        System.out.printf("\n\"%s loses and picks up %d cards.\"\n", loser.getName(), pileCardCount);
    }

    public void displayPlayerCardCounts(Collection<Player> players) {
        System.out.println("\nPlayer Card Count:");
        System.out.println("----------------------");

        for (Player player : players) {
            System.out.printf("%s: %d\n", player.getName(), player.getPlayerHand().size());
        }
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
