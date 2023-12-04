package com.tacocardgame.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Card {

    private final CardType type;
    private String image;

    public Card(CardType type) {
        this.type = type;
    }

    public Card(CardType type, String image) {
        this.type = type;
        this.image = image;
    }

    public void show() {

        System.out.println(image);
    }

    public CardType getType() {

        return type;
    }
}