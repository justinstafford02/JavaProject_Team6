package com.tacocardgame.view;

import com.tacocardgame.model.CardType;
import com.tacocardgame.model.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Board {
    private final Map<CardType, String> cardImages = new EnumMap<>(CardType.class);


    public Board() {
        // Load card images
        cardImages.putAll(loadCardImages());
    }


    private Map<CardType, String> loadCardImages() {
        Map<CardType, String> images = new EnumMap<>(CardType.class);
        try {
            images.put(CardType.TACO, Files.readString(Path.of("resources/images/card_taco.txt")));
            images.put(CardType.CAT, Files.readString(Path.of("resources/images/card_cat.txt")));
            images.put(CardType.GOAT, Files.readString(Path.of("resources/images/card_goat.txt")));
            images.put(CardType.CHEESE, Files.readString(Path.of("resources/images/cheese.txt")));
            images.put(CardType.PIZZA,Files.readString(Path.of("resources/images/card_pizza.txt")));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }

    public String getCardImage(CardType type) {
        return cardImages.get(type);
    }
}

