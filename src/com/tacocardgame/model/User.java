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
                long responseTime = Math.min(currentTime - startTime, maxDuration);
                System.out.println("You slapped in " + responseTime + " milliseconds!");
                return responseTime;
            }
            if (currentTime - startTime >= maxDuration) {
                System.out.println("Time's up! 3 seconds have passed.");
                return maxDuration;
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
