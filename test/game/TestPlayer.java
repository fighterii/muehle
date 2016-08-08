/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package game;

import java.util.ArrayList;

/**
 *
 * @author Kai
 */
public class TestPlayer  implements IPlayerHandler {
    private Muehle muehleGame;
    private Board board;
    public Move move;
    
    public TestPlayer(Muehle muehleGame,int color){
        this.muehleGame = muehleGame;
        this.board = muehleGame.getBoard();
        this.move = null;
        
    }
    
    @Override
    public Move getMove() {
        return move;
    }
    
    
}
