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
    private ArrayList<Stone> wStones;
    private ArrayList<Stone> wSetStones;
    private ArrayList<Stone> wNotsetStones;
    private ArrayList<Stone> wDefeatedStones;
    private ArrayList<Stone> wProtectedStones;
    private ArrayList<Stone> wNotProtectedStones;
    private ArrayList<Stone> bStones;
    private ArrayList<Stone> bSetStones;
    private ArrayList<Stone> bNotsetStones;
    private ArrayList<Stone> bDefeatedStones;
    private ArrayList<Stone> bProtectedStones;
    private ArrayList<Stone> bNotProtectedStones;
    private ArrayList<Cell[]> listPossibleMuehle;
    
    public Stones(Board board){
        //Initialize all stones and lists for player white
        System.out.println("initialize lists for player white");
        this.wStones = new ArrayList<>();
            for(int i=0;i<9;i++){
                wStones.add(new Stone(1));
            }
        this.wSetStones = new ArrayList<>();
        this.wNotsetStones = wStones;
        this.wDefeatedStones = new ArrayList<>();
        this.wProtectedStones = new ArrayList<>();  
        this.wNotProtectedStones = new ArrayList<>();
        System.out.println("initialize lists for player black");
        //Initialize all stones and lists for player black
        this.bStones = new ArrayList<>();
        for(int i=0;i<9;i++){
            bStones.add(new Stone(2));
        }
        this.bSetStones = new ArrayList<>();
        this.bNotsetStones = bStones;      
        this.bDefeatedStones = new ArrayList<>();
        this.bProtectedStones = new ArrayList<>();  
        this.bNotProtectedStones = new ArrayList<>();
        
        //get list with possible Muehle candidates
        this.listPossibleMuehle = board.getPossibleMuehle();
        System.out.println("All stones initialized");
    }

    /**
     *
     * @param color to indicate from which player the next notset stone should be returned
     * @return next unset white stones
     */
    public Stone getNextNotsetStone(int color) {
        switch(color){
            case 1:
                return wNotsetStones.get(0);
            case 2:
                return bNotsetStones.get(0);
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
    public int getSizeDefeatedStones(int color){
        switch(color){
            case 1:
                System.out.println(wDefeatedStones);
                return wDefeatedStones.size();
            case 2:
                return bDefeatedStones.size();
            default:
                throw new IllegalArgumentException("Invalid color!");
        }
    }
    /**
     * Add to List of Stones on Board
     * @param stone is the stone which should be added to list of stones on board
     * also delete from list of notSetStones and add to list of notProtectedStones
     */
    public void setStone(Stone stone) {
       int color = stone.getColor();
       //System.out.println("Setting stone "+stone+"");
       switch(color){
            case 1:
                this.wSetStones.add(stone);
                this.wNotsetStones.remove(stone);
                this.wNotProtectedStones.add(stone);
                break;
            case 2:
                this.bSetStones.add(stone);
                this.bNotsetStones.remove(stone);
                this.bNotProtectedStones.add(stone);
                break;
            default:
                System.err.println("Error for Stone "+stone+ " - invalid color: " + color);
                throw new IllegalArgumentException("Invalid player-color!");
        }
    }
    /**
     * Add to List of defeated Stones
     * remove from list of set Stones
     * remove from list of notProtected Stones
     * @param stone is the stone which should be added to list of defeated stones
     */
    public void defeatStone(Stone stone) {
        int color = stone.getColor();
        switch(color){
            case 1:
                this.wDefeatedStones.add(stone);
                this.wNotProtectedStones.remove(stone);
                this.wSetStones.remove(stone);
                break;
            case 2:
                this.bDefeatedStones.add(stone);
                this.bNotProtectedStones.remove(stone);
                this.bSetStones.remove(stone);
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
                for(int i=0;i<wProtectedStones.size();i++){
                    if(wProtectedStones.get(i)==stone){
                        return true;
                    }
                }
            case 2: //stone of player black
                for(int i=0;i<bProtectedStones.size();i++){
                    if(bProtectedStones.get(i)==stone){
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
        this.wProtectedStones = new ArrayList<>();
        this.wNotProtectedStones = new ArrayList<>();
        for(int i=0;i<wSetStones.size();i++){
            //System.out.println("Nächster Stein wird überprüft...");
            Stone check_stone = wSetStones.get(i);
            if(iSStonePartOfMuehle(check_stone)){
                wProtectedStones.add(check_stone);
            }else{
                wNotProtectedStones.add(check_stone);
            }
        }  
        //recalculate the protected stones for black
        this.bProtectedStones = new ArrayList<>();
        this.bNotProtectedStones = new ArrayList<>();
        for(int i=0;i<bSetStones.size();i++){
            //System.out.println("Nächster Stein wird überprüft...");
            Stone check_stone = bSetStones.get(i);
            if(iSStonePartOfMuehle(check_stone)){
                bProtectedStones.add(check_stone);
            }else{
                bNotProtectedStones.add(check_stone);
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
        for(int i=0;i<listPossibleMuehle.size();i++){
            Cell[] muehle_candidate = listPossibleMuehle.get(i);
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
    public boolean iSStonePartOfMuehle(Stone stone){
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
                for(int i=0;i<wSetStones.size();i++){
                    if(wSetStones.get(i).getPos() == cell){
                        return wSetStones.get(i);
                    }
                }
            case 2: //search black stones
                for(int i=0;i<bSetStones.size();i++){
                    if(bSetStones.get(i).getPos() == cell){
                        return bSetStones.get(i);
                    }
                }
            default:
                throw new IllegalStateException("Cell has invalid color information");            
        }
    }

    public ArrayList<Stone> getStones(int color) {
        switch(color){
            case 1:
                return wStones;
            case 2:
                return bStones;
            default:
                throw new IllegalArgumentException("Invalid player-color!");
        }
    }

    public ArrayList<Stone> getSetStones(int color) {
        switch(color){
            case 1:
                return wSetStones;
            case 2:
                return bSetStones;
            default:
                throw new IllegalArgumentException("Invalid player-color!");
        }
    }

    public ArrayList<Stone> getNotsetStones(int color) {
        switch(color){
            case 1:
                return wNotsetStones;
            case 2:
                return bNotsetStones;
            default:
                throw new IllegalArgumentException("Invalid player-color!");
        }
    }

    public ArrayList<Stone> getDefeatedStones(int color) {
        switch(color){
            case 1:
                return wDefeatedStones;
            case 2:
                return bDefeatedStones;
            default:
                throw new IllegalArgumentException("Invalid player-color!");
        }
    }

    public ArrayList<Stone> getProtectedStones(int color) {
        switch(color){
            case 1:
                return wProtectedStones;
            case 2:
                return bProtectedStones;
            default:
                throw new IllegalArgumentException("Invalid player-color!");
        }
    }
    
    public ArrayList<Stone> getNotProtectedStones(int color) {
        switch(color){
            case 1:
                return wNotProtectedStones;
            case 2:
                return bNotProtectedStones;
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
