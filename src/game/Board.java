package game;

import java.util.ArrayList;

/**
 *
 * @author Kai
 */
public class Board {
    /**
     * This class initializes all 24 cells with its neighbors
     * and provides information about all possible combinations of 3 cells which
     * form a muehle
     */
    private final ArrayList<Cell[][]> circles;
    private final ArrayList<Cell[]> list_possible_muehle;
    private final ArrayList<Cell> list_allcells;

    public Board(){
        //3 circles, each with 3*3-1=8 positions
        circles = new ArrayList<>();
        createCircles();
        list_possible_muehle = new ArrayList<>();
        list_allcells = new ArrayList<>();
        allCells();
        setNeighbors();
        setPossibleMuehle();
        System.out.println("Board initialized");
    }

    //createCircle creates the 8 Cells for one Circle
    private void createCircles() {
        for(int i=0;i<3;i++){
            Cell[][] circle = new Cell[3][3];
            for(int x=0;x<3;x++){
                for(int y=0;y<3;y++){
                    if((x!=1)|(y!=1)){
                        circle[x][y] = new Cell(new Coord(x,y));
                    }
                }
            } 
            circles.add(circle);
        }
    }
    /**
     * allCells lists all cells according to the following pattern
     * (outer circle - middle circle - inner circle)
     */
    private void allCells(){
        /*
        allcells:
        00  .  .  01  .  .  02
        .  08  .  09  .  10  .
        .  .  16  17  18  .  .
        03 11 19  .   20 12 04
        .  .  21  22  23  .  .       
        .  13  .  14  .  15  . 
        05  .  .  06  .  .  07        
        */
        for(int i=0;i<3;i++){
            for(int y=0;y<3;y++){
                for(int x=0;x<3;x++){
                    if((x!=1)|(y!=1)){
                        list_allcells.add(circles.get(i)[x][y]);    
                    }
                }
            }
        }
    }
    /**
     * sets all neighbors for every cell
     * ugly to do it manually, but hard to automate
     */
    private void setNeighbors(){
        
        /*initalizing Vector of neighbors.
             * positions as following:
             * x 0 x
             * 1 X 2
             * x 3 x
             * 
             * every cell has 4 possible neighbors (0-3)
        */
        //Cell0
        Cell[] neighbors = new Cell[4];
        neighbors[2] = list_allcells.get(1);
        neighbors[3] = list_allcells.get(3);
        list_allcells.get(0).setNeighbors(neighbors);
        //Cell1
        neighbors = new Cell[4];
        neighbors[1] = list_allcells.get(0);
        neighbors[2] = list_allcells.get(2);
        neighbors[3] = list_allcells.get(9);
        list_allcells.get(1).setNeighbors(neighbors);
        //Cell2
        neighbors = new Cell[4];
        neighbors[1] = list_allcells.get(1);
        neighbors[3] = list_allcells.get(4);
        list_allcells.get(2).setNeighbors(neighbors);
        //Cell3
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(0);
        neighbors[2] = list_allcells.get(11);
        neighbors[3] = list_allcells.get(5);
        list_allcells.get(3).setNeighbors(neighbors);
        //Cell4
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(2);
        neighbors[1] = list_allcells.get(12);
        neighbors[3] = list_allcells.get(7);
        list_allcells.get(4).setNeighbors(neighbors);
        //Cell5
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(3);
        neighbors[2] = list_allcells.get(6);
        list_allcells.get(5).setNeighbors(neighbors);
        //Cell6
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(14);
        neighbors[1] = list_allcells.get(5);
        neighbors[2] = list_allcells.get(7);
        list_allcells.get(6).setNeighbors(neighbors);
        //Cell7
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(4);
        neighbors[1] = list_allcells.get(6);
        list_allcells.get(7).setNeighbors(neighbors);
        //Cell8
        neighbors = new Cell[4];
        neighbors[2] = list_allcells.get(9);
        neighbors[3] = list_allcells.get(11);
        list_allcells.get(8).setNeighbors(neighbors);
        //Cell9
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(1);
        neighbors[1] = list_allcells.get(8);
        neighbors[2] = list_allcells.get(10);
        neighbors[3] = list_allcells.get(17);
        list_allcells.get(9).setNeighbors(neighbors);
        //Cell10
        neighbors = new Cell[4];
        neighbors[1] = list_allcells.get(9);
        neighbors[3] = list_allcells.get(12);
        list_allcells.get(10).setNeighbors(neighbors);
        //Cell11
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(8);
        neighbors[1] = list_allcells.get(3);
        neighbors[2] = list_allcells.get(19);
        neighbors[3] = list_allcells.get(13);
        list_allcells.get(11).setNeighbors(neighbors);
        //Cell12
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(10);
        neighbors[1] = list_allcells.get(20);
        neighbors[2] = list_allcells.get(4);
        neighbors[3] = list_allcells.get(15);
        list_allcells.get(12).setNeighbors(neighbors);
        //Cell13
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(11);
        neighbors[2] = list_allcells.get(14);
        list_allcells.get(13).setNeighbors(neighbors);
        //Cell14
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(22);
        neighbors[1] = list_allcells.get(13);
        neighbors[2] = list_allcells.get(15);
        neighbors[3] = list_allcells.get(6);
        list_allcells.get(14).setNeighbors(neighbors);
        //Cell15
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(12);
        neighbors[1] = list_allcells.get(14);
        list_allcells.get(15).setNeighbors(neighbors);
        //Cell16
        neighbors = new Cell[4];
        neighbors[2] = list_allcells.get(17);
        neighbors[3] = list_allcells.get(19);
        list_allcells.get(16).setNeighbors(neighbors);
        //Cell17
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(9);
        neighbors[1] = list_allcells.get(16);
        neighbors[2] = list_allcells.get(18);
        list_allcells.get(17).setNeighbors(neighbors);
        //Cell18
        neighbors = new Cell[4];
        neighbors[1] = list_allcells.get(17);
        neighbors[3] = list_allcells.get(20);
        list_allcells.get(18).setNeighbors(neighbors);
        //Cell19
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(16);
        neighbors[1] = list_allcells.get(11);
        neighbors[3] = list_allcells.get(21);
        list_allcells.get(19).setNeighbors(neighbors);
        //Cell20
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(18);
        neighbors[2] = list_allcells.get(12);
        neighbors[3] = list_allcells.get(23);
        list_allcells.get(20).setNeighbors(neighbors);
        //Cell21
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(19);
        neighbors[2] = list_allcells.get(22);
        list_allcells.get(21).setNeighbors(neighbors);
        //Cell22
        neighbors = new Cell[4];
        neighbors[1] = list_allcells.get(21);
        neighbors[2] = list_allcells.get(23);
        neighbors[3] = list_allcells.get(14);
        list_allcells.get(22).setNeighbors(neighbors);
        //Cell23
        neighbors = new Cell[4];
        neighbors[0] = list_allcells.get(20);
        neighbors[1] = list_allcells.get(22);
        list_allcells.get(23).setNeighbors(neighbors);
        /*
        //Template: CellX
        neighbors = new Cell[4];
        neighbors[0] = allcells.get(X);
        neighbors[1] = allcells.get(X);
        neighbors[2] = allcells.get(X);
        neighbors[3] = allcells.get(X);
        allcells.get(X).setNeighbors(neighbors);
        */
    }
  
    /**
     *
     * @return a list of all cells
     * index as following:
     *  00  .  .  01  .  .  02
        .  08  .  09  .  10  .
        .  .  16  17  18  .  .
        03 11 19  .   20 12 04
        .  .  21  22  23  .  .       
        .  13  .  14  .  15  . 
        05  .  .  06  .  .  07      
     */
    public ArrayList<Cell> getAllCells(){
        return list_allcells;
    }

    /**
     * sets all possible muehle
     * ugly to do it manually, but hard to automate
     */
    private void setPossibleMuehle() {
        Cell[] possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(0);
        possible_muehle[1] = list_allcells.get(1);
        possible_muehle[2] = list_allcells.get(2);
        list_possible_muehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(8);
        possible_muehle[1] = list_allcells.get(9);
        possible_muehle[2] = list_allcells.get(10);
        list_possible_muehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(16);
        possible_muehle[1] = list_allcells.get(17);
        possible_muehle[2] = list_allcells.get(18);
        list_possible_muehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(3);
        possible_muehle[1] = list_allcells.get(11);
        possible_muehle[2] = list_allcells.get(19);
        list_possible_muehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(20);
        possible_muehle[1] = list_allcells.get(12);
        possible_muehle[2] = list_allcells.get(4);
        list_possible_muehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(21);
        possible_muehle[1] = list_allcells.get(22);
        possible_muehle[2] = list_allcells.get(23);
        list_possible_muehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(13);
        possible_muehle[1] = list_allcells.get(14);
        possible_muehle[2] = list_allcells.get(15);
        list_possible_muehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(5);
        possible_muehle[1] = list_allcells.get(6);
        possible_muehle[2] = list_allcells.get(7);
        list_possible_muehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(0);
        possible_muehle[1] = list_allcells.get(3);
        possible_muehle[2] = list_allcells.get(5);
        list_possible_muehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(8);
        possible_muehle[1] = list_allcells.get(11);
        possible_muehle[2] = list_allcells.get(13);
        list_possible_muehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(16);
        possible_muehle[1] = list_allcells.get(19);
        possible_muehle[2] = list_allcells.get(21);
        list_possible_muehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(1);
        possible_muehle[1] = list_allcells.get(9);
        possible_muehle[2] = list_allcells.get(17);
        list_possible_muehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(22);
        possible_muehle[1] = list_allcells.get(14);
        possible_muehle[2] = list_allcells.get(6);
        list_possible_muehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(18);
        possible_muehle[1] = list_allcells.get(20);
        possible_muehle[2] = list_allcells.get(23);
        list_possible_muehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(10);
        possible_muehle[1] = list_allcells.get(12);
        possible_muehle[2] = list_allcells.get(15);
        list_possible_muehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(2);
        possible_muehle[1] = list_allcells.get(4);
        possible_muehle[2] = list_allcells.get(7);
        list_possible_muehle.add(possible_muehle);
        /**
         * Template:
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = list_allcells.get(X);
        possible_muehle[1] = list_allcells.get(X);
        possible_muehle[2] = list_allcells.get(X);
        list_possible_muehle.add(possible_muehle);
         */

        
    }
    
    /**
     *
     * @return the List of all possible Muehle, each possiblity containing 3 cells
     */
    public ArrayList<Cell[]> getPossibleMuehle() {
        return list_possible_muehle;
    }
    
    

}
