package com.tacocardgame.model;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private static List<Integer> targetPosition = new ArrayList<>();
    private static List<Integer> stopPosition = new ArrayList<>();

    static {
        targetPosition.add(1); // Initial game target position set to 1
    }

    // Method to update stopPosition
    public static void updateStopPosition(int position) {
        stopPosition.add(position);
        updateTargetPosition();
    }

    // Method to update targetPosition from last stopPosition

    private static void updateTargetPosition() {
        int lastStopPosition = stopPosition.get(stopPosition.size() - 1);
        targetPosition.add(lastStopPosition + 1);
    }

    // Getter methods for targetPosition and stopPosition

    public static String getPlayerSays(int position) {
        CardType cardType = CardType.findByPosition(position);
        return cardType != null ? cardType.getLabel() : "Somethings wrong here";
    }

    public static int getCurrentTargetPosition() {
        return targetPosition.get(targetPosition.size() - 1);
    }

    public static int getLastStopPosition() {
        return stopPosition.get(stopPosition.size() - 1);
    }
}