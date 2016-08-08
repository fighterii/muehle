/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package game;

/**
 *
 * @author Kai
 */
public class Move {
    public Stone stone;
    public Cell target;
    /**
     * class Move describes a stone to be moved from source to target
     * @param stone is a Stone obj which holds the information about the current (source) cell
     * @param target is a Cell obj which indicates the target location of the move (null: defeat)
     */
    public Move(Stone stone, Cell target){
        this.stone = stone;
        this.target = target;
    }
    
}
