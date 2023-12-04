package com.tacocardgame.model;

import com.apps.util.Console;

import java.util.*;

public abstract class Player {

    // TODO: (NEED TO DISCUSS) make sure this resets to 0 when a player loses a round
    private List<Card> playerHand = new ArrayList<>();
    private String name;
    private int playerId;

    // CONSTRUCTOR

    public Player(String name) {
        this.name = name;

    }

    public Player(String name, int playerId) {
        this.name = name;
        this.playerId = playerId;
    }

    public Player(String name, int playerId, ArrayList<Card> playerHand) {
        this.name = name;
        this.playerId = playerId;
        this.playerHand = playerHand;
    }

    // METHODS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {

        this.playerId = playerId;

    }

    public void addCardsToPlayerHand(List<Card> cards) {
        this.playerHand.addAll(cards);
    }

    public Card takeTurn(Pile pile, int wordIndex) {
        Card cardFlipped = playerFlipsCard(pile);
        CardType cardType = cardFlipped.getType();
        String asciiArt = Deck.getAsciiCardType(cardType);
        String playerStatement = playerSays(wordIndex);
        Console.pause(2000); // 2 seconds pause
//        System.out.println(getName() + " flipped " + cardFlipped.getType() + " and says "
//                + playerStatement);
//        System.out.println(asciiArt);
        return cardFlipped;
    }

    public Card playerFlipsCard(Pile pile) {
        if (playerHand.isEmpty()) {
            throw new NoSuchElementException("No cards are left to flip");
        }
        Card card = playerHand.remove(0); // removes the first card from playerHand
        pile.addToPile(card);  // adds this instance of card to the pile.  This is why instances are super important.
        return card;
    }

    public String playerSays(int position) {
        CardType findPosition = CardType.findByPosition(position);
        return findPosition != null ? findPosition.getLabel() : "Illegal card position";
    }

    public abstract Long playerSlaps();

}
