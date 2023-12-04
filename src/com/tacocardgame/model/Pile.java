package com.tacocardgame.model;

import java.util.*;

public class Pile {

    private final Deque<Card> pile = new ArrayDeque<>();

    public void addToPile(Card card) {
        pile.push(card);
    }

    public Card showTopOfPile() {
        return pile.peekFirst();
    }

    public ArrayList<Card> dequeToArrayList() {
        ArrayList<Card> iteratedArrayList = new ArrayList<>();
        while (!pile.isEmpty()) {
            Card card = pile.getFirst();
            pile.remove();
            iteratedArrayList.add(card);
        }
        return iteratedArrayList;
    }

//    public Deque pileToDeque(Pile pile) {
//        ArrayDeque<Card> arrayDequePile = new ArrayDeque<Card>(pile);
//
//    }

    public int getSize() {
        return pile.size();
    }

    public void clearPile() {
        pile.clear();
    }



}