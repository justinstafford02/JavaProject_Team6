package com.tacocardgame.model;

import com.apps.util.Prompter;

import java.time.Duration;
import java.time.Instant;
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
        Instant startTime = Instant.now();
        Scanner scanner = new Scanner(System.in);
        System.out.println("SlapTime! Press Enter to slap!");

        scanner.nextLine();
        Instant endTime = Instant.now();
        long responseTime = Duration.between(startTime, endTime).toMillis();

        responseTime = Math.min(responseTime, 3000); // Limit to max 3 seconds
        System.out.println("You slapped in " + responseTime + " milliseconds!");
        return responseTime;
    }

}
