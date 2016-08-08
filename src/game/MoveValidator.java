/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package game;

/**
 *
 * @author Kai
 */
public class MoveValidator {
    private final Muehle muehleGame;
    private final IPlayerHandler whitePlayer;
    private final IPlayerHandler blackPlayer;
    
    public MoveValidator(Muehle muehleGame) {
        this.muehleGame = muehleGame;
        this.whitePlayer = this.muehleGame.getWhitePlayer();
        this.blackPlayer = this.muehleGame.getBlackPlayer();
    }

    /**
     * Is used to determine if a move is valid
     * @param move Move object which describes the Stone to be moved and the target Cell
     * @return false if invalid, true if valid
     */
    public boolean isValidMove(Move move){
        boolean second_move = muehleGame.isSecond_move();
        IPlayerHandler activePlayer = muehleGame.getActivePlayer();
        Stone stone = move.stone;
        Cell source = stone.getPos();
        int stone_color = stone.getColor();
        Cell target = move.target;
        int player_color;
        System.out.println("ActivePlayer isValidMove:" + activePlayer);
        System.out.println("blackPlayer isValidMove:" + blackPlayer);
        if(activePlayer==this.whitePlayer){
            player_color=1;
        }else if(activePlayer==this.blackPlayer){
            player_color=2;
        }else{
            throw new IllegalStateException("Unknown activePlayer");
        }
        
        if(second_move){
            //defeating an enemy stone
            //move has to have an enemy stone and target null
            if(stone_color!=player_color){
                if(target==null){
                    return true;
                }else{
                    System.out.println("Unknown target for defeating a stone");
                }
            }else{
                System.out.println("You have to defeat an enemy stone!");
            }
            
        }else{
            //moving an own stown
            //move has to be an own stone and a valid target
            //valid target has to be an empty field
            //if the stone was already on the board (source != null) 
            //the target field has to be within the neighborhood of the current position
            
            //own stone?
            if(stone_color==player_color){
                //target empty?
                if(target.getStoneColor()==0){
                    //source not empty?
                    if(source != null){
                        Cell[] neighbors = source.getNeighbors();
                        //target in the neighborhood?
                        if(cellArrayContainsCell(neighbors,target)){
                            return true;
                        }else{
                            System.out.println("Target field is not in the neighborhood of the current field");
                        }                       
                    }else{
                        //source empty, target empty, colors_match
                        return true;
                    }
 
                }else{
                    //Target field is not empty
                    System.out.println("Target field is not empty!");
                }
            }else{
                //stone_color & player_color don't match
                System.out.println("You can only move your own stones!");
            }
        }
        return false;
    }

    /*
    * simple method to loop over array of cells
    * to check if it contains a specific cell 
    * return true if Cell item is contained within Cell[] arr
    */
    private static boolean cellArrayContainsCell(Cell[] arr, Cell item) {
      for (Cell n : arr) {
         if (item == n) {
            return true;
         }
      }
      return false;
   }
}
