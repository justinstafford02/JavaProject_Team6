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
        // creates a simulated delay for NPC reaction time.

        long delay = (long)(1000 + Math.random() * 1000); // Delay between 1 to 2 seconds
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            // Handle exception
        }
        return System.currentTimeMillis(); // Return the current time after the delay
    }

    public void setName(String name) {
        this.name = name;
    }

}