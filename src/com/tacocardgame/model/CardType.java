package com.tacocardgame.model;

public enum CardType {
    TACO(0, "Taco"),
    CAT(1, "Cat"),
    GOAT(2, "Goat"),
    CHEESE(3, "Cheese"),
    PIZZA(4, "Pizza");

    private final int position;
    private final String label;

    CardType(int position, String label) {
        this.position = position;
        this.label = label;
    }

    public int getPosition() {

        return position;
    }

    public String getLabel() {
        return label;
    }

    public static CardType findByPosition(int position) {
        for (CardType type : values()) {
            if (type.getPosition() == position) {
                return type;
            }
        }
        return null; // or throw an exception
    }
}