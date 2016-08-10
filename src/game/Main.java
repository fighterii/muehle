/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package game;

import gui.Console;
import gui.JGame;

/**
 *
 * @author Kai
 */
public class Main {
    public static void main(String[] args) {

        // first we create the game
        Muehle muehleGame = new Muehle();

        // then we create the clients/players
        //JGame muehleGui = new JGame(muehleGame);
        Console consoleGui = new Console(muehleGame);
        
        RandomAiPlayer ai1 = new RandomAiPlayer(muehleGame);

        // then we attach the clients/players to the game
        muehleGame.setPlayer(1, consoleGui);
        muehleGame.setPlayer(2, ai1);

        // in the end we start the game
        new Thread(muehleGame).start();
	}    
}
