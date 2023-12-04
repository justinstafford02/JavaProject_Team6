package com.tacocardgame.model;

import com.apps.util.Prompter;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.*;


public class User extends Player {

    public User(String name) {
        super(name);
    }

    public User(String name, int playerId) {
        super(name, playerId);
    }

    public User(String name, int playerId, ArrayList<Card> playerHand) {
        super(name, playerId, playerHand);

    }

    @Override
    public Long playerSlaps() {
        long startTime = System.currentTimeMillis();
        long maxDuration = 3000; // 3 seconds in milliseconds

        Scanner scanner = new Scanner(System.in);
        System.out.println("SlapTime! Press Enter to slap!");

        while (true) {
            long currentTime = System.currentTimeMillis();
            if (scanner.hasNextLine()) {
                scanner.nextLine();
                return Math.min(currentTime - startTime, maxDuration);
                //currentTime is time in milliseconds
            }
            if (currentTime - startTime >= maxDuration) {
                return maxDuration;
                //if anything other than "Enter" is pushed or if output time is greater than 3
                // seconds,
                // user will be gifted a 3 second response.  This solves input errors. A spacebar entry
                // will get you 3 seconds, just like a "b" entry would.  We treat slow responses the
                // same as wrong responses.  Later, on we can add functionality to partition "outside
                // threshholds" responses in a separate thread.
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Reset interrupt flag if thread is interrupted
                e.printStackTrace();
            }
        }
    }
}
