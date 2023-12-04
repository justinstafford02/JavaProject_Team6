package com.tacocardgame.model;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Npc extends Player {

    private String name;

    public Npc(String name, int playerId) {
        super(name, playerId); // Calls ctor of superclass: Player
    }

    public Npc(String name, int playerId, ArrayList<Card> playerHand) {
        super(name, playerId, playerHand); // Calls ctor of superclass: Player
    }

    @Override
    public Long playerSlaps() {
        long delay;
        //trying switch statement, because previous version had player 5 losing every time.
        //handleSlap() in GameController will use its for-loop to iterate through this swtich
        // statement./ I haven't accounted for ties yet, so I'm not sure if this is the best way to do it.'
        switch (getPlayerId()) {
            case 2:
                // NPC player 2, range 500 to 1500 milliseconds
                delay = 500 + (long) (Math.random() * 1000);
                break;
            case 3:
                // NPC player 3, range 750 to 1750 milliseconds
                delay = 750 + (long) (Math.random() * 1000);
                break;
            case 4:
                // NPC player 4, range 900 to 2000 milliseconds
                delay = 900 + (long) (Math.random() * 1100);
                break;
            case 5:
                // NPC player 5, range 250 to 3000 milliseconds
                delay = 250 + (long) (Math.random() * 2750);
                break;
            default:
                // Default delay for any other NPCs
                delay = 1000 + (long) (Math.random() * 1000);
                break;
        }

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return System.currentTimeMillis(); // Return the current time after the delay
    }

    public void setName(String name) {
        this.name = name;
    }

}