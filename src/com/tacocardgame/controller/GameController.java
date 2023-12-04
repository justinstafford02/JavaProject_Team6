package com.tacocardgame.controller;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.tacocardgame.model.*;
import com.tacocardgame.view.BoardView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class GameController {
    private final Deck deck;
    private final Prompter prompter = new Prompter(new Scanner(System.in));
    private final List<Player> players;
    private final Pile pile;
    private Player winner;
    private boolean gameWon = false;
    private final BoardView boardView;
    int currentPlayerIndex = 0;

    public GameController() throws IOException {
        this.deck = new Deck();
        this.players = new ArrayList<>();
        this.pile = new Pile();
        this.boardView = new BoardView();
        BoardView boardView = new BoardView();
        boardView.show(players); // Assuming 'players' List<Player>

        // Initialize players here
        players.add(new User("User", 1)); // User player
        players.add(new Npc("Chuck", 2)); // NPC players
        players.add(new Npc("CJ", 3));
        players.add(new Npc("Justin", 4));
        players.add(new Npc("Keith", 5));
    }

    // method chaining execute() into playGame().
    public void execute() throws IOException {
        Console.clear();
        this.displayWelcomeSequence();
        String userPlayerName = promptForPlayerName();
        // sets the one user
        players.get(0).setName(userPlayerName);
        deck.distributeCards(players);  //distribute cards evenly amongst players
        playGame();
    }

    private void displayWelcomeSequence() throws IOException {
        List<String> welcomeImages = loadWelcomeImages();
        for (int i = 0; i < welcomeImages.size(); i++) {
            System.out.println(welcomeImages.get(i)); // Display the image

            if (i < welcomeImages.size() - 1) {
                Console.pause(500); // Pause for 0.5 seconds for all images except the last
                Console.clear();
            } else {
                Console.pause(5000); //longer pause for the last image with 5 seconds
            }
        }
        Console.clear();
    }

    private List<String> loadWelcomeImages() throws IOException {
        List<String> images = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            String file = "resources/images/welcome-" + i + ".txt";
            images.add(Files.readString(Path.of(file)));
        }
        return images;
    }

    private String promptForPlayerName() {
        String playerName = "";
        while (playerName == null || playerName.trim().isEmpty()) {
            playerName = prompter.prompt("Please enter your name: ");
            if (playerName == null || playerName.trim().isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            }
        }
        return playerName;
    }


    public void playGame() {
        Console.clear();
        gameWon = false;
        int wordIndex = 0; // Index to keep track of the current word to be said

        while (!gameWon) {
            Player currentPlayer = players.get(currentPlayerIndex); // Get the current player
            //Then currentPlayer takes turn.
            Card flippedCard = currentPlayer.takeTurn(pile, wordIndex); // Get the card
            String playerStates = currentPlayer.playerSays(wordIndex); // Get said based on

            //boardView displays the ascii art of the card
            boardView.displayTurnInfo(currentPlayer.getName(), playerStates,
                    Deck.getAsciiCardType(flippedCard.getType()));

            // Check if a handle slap is needed
            if (hasMatch(flippedCard, wordIndex)) {
                System.out.println("Match found! Players prepare to slap!");
                Console.pause(2000); // 2 seconds pause
                handleSlap();
                wordIndex = 0; // Reset word index after a match
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); // Move to next player
                promptContinue(); //Prompt to continue the game
                continue;
                // This should skip the rest of the loop, start a new round with wordIndex = 0
            }

            // Checking if the current player has won
            if (currentPlayer.getPlayerHand().isEmpty()) {
                gameWon = true;
                winner = currentPlayer;
                break;
            }

            // Move to the next word
            wordIndex = (wordIndex + 1) % CardType.values().length;
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); // Move to next player
        }
        // This is used to keep track of the current word to be said.  Previously, everything was
        // tied to current player index. previously causing disconntect as the players were
        // cycling through. For instance, if the current player is the last player in the
        // 'players' list, then the current player index is the index of the first player in the
        // 'players' list.  This was the wrong outcome.  This was affecting what players
        // said and how comparisons were determined.  The fix was to have two sep indexes that
        //increments the current player position to the next player in the 'players' list,

        // Announce winner
        System.out.println("Game Over! The winner is " + winner.getName());
    }

    private void handleSlap() {

        // Collect slap times from each player and add them to List
        Map<Player, Long> playerSlapTimes = new HashMap<>();
        for (Player player : players) {
            long slapTime = player.playerSlaps();
            playerSlapTimes.put(player, slapTime);
        }

        int pileCardCount = pile.dequeToArrayList().size();
        Player lastToSlap = determineLastToSlap(playerSlapTimes);
        lastToSlap.addCardsToPlayerHand(pile.dequeToArrayList());
        pile.clearPile();

        // Display slap times and loser in BoardView
        boardView.displaySlapTimes(playerSlapTimes);
        boardView.showLoser(lastToSlap, pileCardCount);
    }

    private boolean hasMatch(Card flippedCard, int wordIndex) {
        if (flippedCard == null) {
            throw new IllegalArgumentException("Flipped card cannot be null");
        }

        String expectedCardLabel = CardType.findByPosition(wordIndex).getLabel();
        return flippedCard.getType().getLabel().equalsIgnoreCase(expectedCardLabel);
    }

    // Helper method to prompt the user to continue the game
    private void promptContinue() {
        System.out.println("Press enter to continue game...");
        prompter.prompt("");
        Console.clear();
    }

    private Player determineLastToSlap(Map<Player, Long> playerSlapTimes) {
        return Collections.max(playerSlapTimes.entrySet(), Map.Entry.comparingByValue()).getKey();
        //page 412-Digging Deeper Java.
    }
}

//To consider:
//#1
//TODO: add a method to playGame() that takes in a list of slap times and returns the players
// hashmap with the player's name as the key and the player's slap time as the value.
//#2
//TODO: add a method to determineLastToSlap() that takes in a list
// of slap times and returns the players hashmap with the player's name as the key and the'
// player's slap time as the value.
//#3
//Display in BoardView. then clear the console.
//#4
//prompter to resume game.
//#5
//use while loop and currentPlayerIndex to keep track of wordIndex for the next round
//it might be easier to use a while loop to keep track of wordIndex for the next round.
//easier to track current player index and then reset wordIndex to 0, when
//a match is found.


//#9
//TODO: consider linkedHashmap -from DSA doubly linked list- and add a method that takes in a list
// of slap times and returns the players hashmap with the player's name as the key and the
// player's slap time as the value.

//#10
//When there is a slap event... what if we had flash of ASCII images (like the snowman), where
// cards are flashed being (simulating players' slaps) slapped on top of each other.  This would
// have to be done in a separate method and added to playGame().