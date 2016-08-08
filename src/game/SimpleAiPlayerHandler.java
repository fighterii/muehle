/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package game;

/**
 *
 * @author Kai
 */
class SimpleAiPlayerHandler implements IPlayerHandler {
    private Muehle muehleGame;
    
    public SimpleAiPlayerHandler(Muehle muehleGame){
        this.muehleGame = muehleGame;
    }

    @Override
    public Move getMove() {
        return null;
    }
    
}
