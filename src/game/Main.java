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
        JGame humanPlayer = new JGame(muehleGame);
        //Console humanPlayer = new Console(muehleGame);
        
        RandomAiPlayer aiPlayer = new RandomAiPlayer(muehleGame);

        // then we attach the clients/players to the game
        muehleGame.setPlayer(1, humanPlayer);
        muehleGame.setPlayer(2, aiPlayer);

        // in the end we start the game
        humanPlayer.setVisible(true);
        new Thread(muehleGame).start();
	}    
}
