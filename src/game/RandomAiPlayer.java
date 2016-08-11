/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package game;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Kai
 */
public class RandomAiPlayer implements IPlayerHandler{
    private Muehle muehleGame;
    private int color;
    
    public RandomAiPlayer(Muehle muehleGame){
        this.muehleGame = muehleGame;
        //AiPlayer is black player
        this.color = 2;
    }
    
    @Override
    public Move getMove() {
        MoveValidator moveValidator = muehleGame.getMoveValidator();
        int type = getTypeOfMove();
        Move move = null;
        Stone stone = null;
        switch(type){
            case 0:
                //setting a stone on board
                stone = muehleGame.getStones().getNextNotsetStone(color);
                //searching randomly for a free cell
                Cell cell = randomFreeCell();
                //creating move to set stone on free cell
                move = new Move(stone,cell);
                break;
            case 1:
                //moving a stone on the board
                Random rnd = new Random();
                do{
                    int rndInt = rnd.nextInt(muehleGame.getStones().getSetStones(color).size());
                    Stone rndStone = muehleGame.getStones().getSetStones(color).get(rndInt);
                    Cell cellOfRndStone = rndStone.getPos();
                    Cell target = null;
                    int rndIntNeighbor = rnd.nextInt(4);
                    Cell neighborCell = null;
                    neighborCell = cellOfRndStone.getNeighbors()[rndIntNeighbor];
                    //get a rndNeighborCell for the rndCell
                    if(neighborCell!=null){
                        //check if its free
                        if(neighborCell.getStoneColor()==0){
                            target = neighborCell;
                        }
                    }
                    //if a free neighbor cell was found check if move is valid
                    if(target!=null){
                        move = new Move(rndStone,target);
                        if(!moveValidator.isValidMove(move)){
                            //if not valid set move to null
                            move=null;
                        }
                    }
                }while(move==null);
                break;
            case 2:
                //defeat an enemy stone
                stone = getRandomNotProtectedEnemyStone();
                if(stone!=null){
                    move = new Move(stone,null);
                }
                break;
        }
        return move;
    }
    
    /**
     * detemernise which type of move has to be made:
     * - set a stone
     * - move a stone
     * - defeat a stone
     * @return int (0=set, 1=move, 2=defeat)
     */
    private int getTypeOfMove(){
        if(muehleGame.getStones().getNotsetStones(color).size()>0){
            //if Size of notsetStones is >0 stones have to be set on the board
            return 0;
        }else if(muehleGame.isSecond_move()){
            //if second_move is true, an enemy stone has to be defeated
            return 2;
        }else{
            //all other cases, a stone has to be moved
            return 1;
        }        
    }
    
    /**
     * searches all cells randomly for a free cell
     * @return free cell
     */
    private Cell randomFreeCell(){
        Random rnd = new Random();
        Cell cell = null;
        boolean freeCellFound = false;
        do{
            int rnd_i = rnd.nextInt(24);
            if(muehleGame.getBoard().getListAllCells().get(rnd_i).getStoneColor()==0){
                cell = muehleGame.getBoard().getListAllCells().get(rnd_i);
                freeCellFound = true;
            }
        }while(!freeCellFound);
        return cell;
    }

    /**
     * searches enemy stones for a not protected
     * @return 
     */
    private Stone getRandomNotProtectedEnemyStone() {
        Random rnd = new Random();
        ArrayList<Stone> notProtected = muehleGame.getStones().getNotProtectedStones(color);
        if(notProtected.size()==0){
            System.out.println("No stone not protected");
            return null;
        }else{
            int rnd_int = rnd.nextInt(notProtected.size());
            return notProtected.get(rnd_int);    
        }

    }
}
