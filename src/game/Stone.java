/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package game;

/**
 *
 * @author Kai
 */
public class Stone {
    /**
     * An Instance of this Class represents a single Stone obj of the game
     * it is either white (color=1) or black(color=2)
     * and holds information about its position on the board
     * (null indicates not on the board, either defeated or notset yet)
     */
    private Cell pos;
    private final int color;

    /**
     * Constructor for a Stone Object
     * 
     * @param color: 1 - white; 2 - black
     */
    public Stone(int color){
        if((color!=1) | (color!=2)){
            this.color = color;           
        }else{
            throw new IllegalArgumentException("Stone must be assigned to player white(1) or player black(2)");
        }
        this.pos = null;
    }

    /**
     *
     * @return current position of the stone
     */
    public Cell getPos() {
        return pos;
    }

    /**
     *
     * @return color of the stone
     * 1: white
     * 2: black
     */
    public int getColor() {
        return color;
    }

    /**
     * Set the position
     * @param pos
     * of the Stone
     */
    public void setPos(Cell pos) {
        this.pos = pos;
        //if pos is a cell on board, the color is set to the pos
        //if pos is null(defeated), no color is set
        if(pos!=null){
            pos.setStoneColor(color);    
        }
    }

    @Override
    public String toString() {
        return "Stone{" + "pos=" + pos + ", color=" + color + '}';
    }
    
    
    
}
