package com.tacocardgame.client;

import com.tacocardgame.controller.GameController;

import java.io.IOException;

public class GameControllerMain {
    public static void main(String[] args) {
        try {
            GameController app = new GameController();
            app.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
