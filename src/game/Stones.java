/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package game;

import java.util.ArrayList;

/**
 *
 * @author Kai
 * 
 */
public class Stones {
    /**
     * This class holds the information of all the stones of the game
     * it initializes 9 stones for each of the two player colors white&black
     * and keeps track about the status of each Stone
     */
    private ArrayList<Stone> w_stones;
    private ArrayList<Stone> w_set_stones;
    private ArrayList<Stone> w_notset_stones;
    private ArrayList<Stone> w_defeated_stones;
    private ArrayList<Stone> w_protected_stones;
    private ArrayList<Stone> b_stones;
    private ArrayList<Stone> b_set_stones;
    private ArrayList<Stone> b_notset_stones;
    private ArrayList<Stone> b_defeated_stones;
    private ArrayList<Stone> b_protected_stones;
    private ArrayList<Cell[]> list_possible_muehle;
    
    public Stones(Board board){
        //Initialize all stones and lists for player white
        System.out.println("initialize lists for player white");
        this.w_stones = new ArrayList<>();
            for(int i=0;i<9;i++){
                w_stones.add(new Stone(1));
            }
        this.w_set_stones = new ArrayList<>();
        this.w_notset_stones = w_stones;
        this.w_defeated_stones = new ArrayList<>();
        this.w_protected_stones = new ArrayList<>();  
        System.out.println("initialize lists for player black");
        //Initialize all stones and lists for player black
        this.b_stones = new ArrayList<>();
        for(int i=0;i<9;i++){
            b_stones.add(new Stone(2));
        }
        this.b_set_stones = new ArrayList<>();
        this.b_notset_stones = b_stones;      
        this.b_defeated_stones = new ArrayList<>();
        this.b_protected_stones = new ArrayList<>();  
        
        //get list with possible Muehle candidates
        this.list_possible_muehle = board.getPossibleMuehle();
        System.out.println("All stones initialized");
    }

    /**
     *
     * @param color to indicate from which player the next notset stone should be returned
     * @return next unset white stones
     */
    public Stone getNext_notset_stones(int color) {
        switch(color){
            case 1:
                return w_notset_stones.get(0);
            case 2:
                return b_notset_stones.get(0);
            default:
                throw new IllegalArgumentException("Invalid color!");
        }

    }

    /**
     *
     * @param color for which player the size should be returned
     * @return the size of the list of defeated stones
     * size == 9 indicates the player has lost
     */
    public int getSize_defeated_stones(int color){
        switch(color){
            case 1:
                System.out.println(w_defeated_stones);
                return w_defeated_stones.size();
            case 2:
                return b_defeated_stones.size();
            default:
                throw new IllegalArgumentException("Invalid color!");
        }
    }
    /**
     * Add to List of Stones on Board
     * @param stone is the stone which should be added to list of stones on board
     * also delete from list of notset_stones
     */
    public void set_stone(Stone stone) {
       int color = stone.getColor();
       switch(color){
            case 1:
                this.w_set_stones.add(stone);
                this.w_notset_stones.remove(stone);
                break;
            case 2:
                this.b_set_stones.add(stone);
                this.b_notset_stones.remove(stone);
                break;
            default:
                System.err.println("Error for Stone "+stone+ " - invalid color: " + color);
                throw new IllegalArgumentException("Invalid player-color!");
        }
    }
    /**
     * Add to List of defeated Stones
     * remove from list of set Stones
     * @param stone is the stone which should be added to list of defeated stones
     */
    public void defeat_stone(Stone stone) {
        int color = stone.getColor();
        switch(color){
            case 1:
                this.w_defeated_stones.add(stone);
                this.w_set_stones.remove(stone);
                break;
            case 2:
                this.b_defeated_stones.add(stone);
                this.b_set_stones.remove(stone);
                break;
            default:
                throw new IllegalArgumentException("Invalid player-color!");
                
        }
    }
   
    /**
     * returns wether a particular
     * @param stone to be checked for protection
     * is protected
     * @return true if stone is protected by a muehle
     */
    public boolean isStoneProtected(Stone stone){
        //first check if Stone is within list of protected stones of player white:
        int color = stone.getColor();
        switch(color){
            case 1: //stone of player white
                for(int i=0;i<w_protected_stones.size();i++){
                    if(w_protected_stones.get(i)==stone){
                        return true;
                    }
                }
            case 2: //stone of player black
                for(int i=0;i<b_protected_stones.size();i++){
                    if(b_protected_stones.get(i)==stone){
                        return true;
                    }
                }              
            default:
                break;
        }
        return false;
    }
    
    /** 
     * recaclulates the list of protected stones for player black and white
     */
    public void recalculateProtected(){
        //recalculate the protected stones for white
        this.w_protected_stones = new ArrayList<>();
        for(int i=0;i<w_set_stones.size();i++){
            //System.out.println("Nächster Stein wird überprüft...");
            Stone check_stone = w_set_stones.get(i);
            if(checkIfStonePartOfMuehle(check_stone)){
                w_protected_stones.add(check_stone);
            }
        }  
        //recalculate the protected stones for blacl
        this.b_protected_stones = new ArrayList<>();
        for(int i=0;i<b_set_stones.size();i++){
            //System.out.println("Nächster Stein wird überprüft...");
            Stone check_stone = b_set_stones.get(i);
            if(checkIfStonePartOfMuehle(check_stone)){
                b_protected_stones.add(check_stone);
            }
        }  
    }
    /**
     * Method getPossibleMuehle 
     * @return a List with all possible Muehle for the 
     * @param stone position. 
     */
    private ArrayList<Cell[]> getPossibleMuehle(Stone stone){
        Cell cell = stone.getPos();
        ArrayList<Cell[]> possible_muehle_stone = new ArrayList<>();
        for(int i=0;i<list_possible_muehle.size();i++){
            Cell[] muehle_candidate = list_possible_muehle.get(i);
            if(cellArrayContainsCell(muehle_candidate,cell)){
                possible_muehle_stone.add(muehle_candidate);
            }
        }
        return possible_muehle_stone;
    }
    
     /**
     * checks if the 
     * @param stone
     * is now part of a muehle
     * 
     * @return true if stone is part of a muehle
     * false if not part of a muehle
     */
    public boolean checkIfStonePartOfMuehle(Stone stone){
        int color = stone.getColor();
        ArrayList<Cell[]> candidates = getPossibleMuehle(stone);
        //System.out.println("Kandidat: ");
        //System.out.println(candidate[0] + "-"+candidate[1] + "-"+candidate[2] + "-");
        //System.out.println("Geschützter Stein gefunden: ");
        return candidates.stream().anyMatch((candidate) -> ((candidate[0].getStoneColor()==color)&
                (candidate[1].getStoneColor()==color)&
                (candidate[2].getStoneColor()==color)));
    }
    
    public Stone getStoneOnPos(Cell cell) {
        int color = cell.getStoneColor();
        switch(color){
            case 0: //no Stone on cell
                System.out.println("No Stone on Cell "+cell+ " found.");
                return null;
            case 1: //search white stones
                for(int i=0;i<w_set_stones.size();i++){
                    if(w_set_stones.get(i).getPos() == cell){
                        return w_set_stones.get(i);
                    }
                }
            case 2: //search black stones
                for(int i=0;i<b_set_stones.size();i++){
                    if(b_set_stones.get(i).getPos() == cell){
                        return b_set_stones.get(i);
                    }
                }
            default:
                throw new IllegalStateException("Cell has invalid color information");            
        }
    }

    public ArrayList<Stone> getStones(int color) {
        switch(color){
            case 1:
                return w_stones;
            case 2:
                return b_stones;
            default:
                throw new IllegalArgumentException("Invalid player-color!");
        }
    }

    public ArrayList<Stone> getSet_stones(int color) {
        switch(color){
            case 1:
                return w_set_stones;
            case 2:
                return b_set_stones;
            default:
                throw new IllegalArgumentException("Invalid player-color!");
        }
    }

    public ArrayList<Stone> getNotset_stones(int color) {
        switch(color){
            case 1:
                return w_notset_stones;
            case 2:
                return b_notset_stones;
            default:
                throw new IllegalArgumentException("Invalid player-color!");
        }
    }

    public ArrayList<Stone> getDefeated_stones(int color) {
        switch(color){
            case 1:
                return w_defeated_stones;
            case 2:
                return b_defeated_stones;
            default:
                throw new IllegalArgumentException("Invalid player-color!");
        }
    }

    public ArrayList<Stone> getProtected_stones(int color) {
        switch(color){
            case 1:
                return w_protected_stones;
            case 2:
                return b_protected_stones;
            default:
                throw new IllegalArgumentException("Invalid player-color!");
        }
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
