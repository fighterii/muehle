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
    private final ArrayList<Cell[]> listPossibleMuehle;
    private final Cell[][] allCellsGrid;
    private final ArrayList<Cell> listAllCells;

    public Board(){
        allCellsGrid = new Cell[7][7];
        listPossibleMuehle = new ArrayList();
        listAllCells = new ArrayList();;
        allCells();
        initializeNeighbors();
        setPossibleMuehle();
        System.out.println("Board initialized");
    }

    /**
     * allCells lists all cells according to the following pattern
     * (outer circle - middle circle - inner circle)
     */
    private void allCells(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Coord coord = new Coord(i,j);           
                if(coord.isValidPos()){
                    Cell cell = new Cell(coord);
                    allCellsGrid[i][j] = cell;
                    //System.out.println(cell+" "+listAllCells.size()+" added to grid and list");
                    listAllCells.add(cell);
                    
                }
            }
        }
    }
    /**
     * searches the GridArray of Cells for a neighbor in the north
     * @param column of the actual cell
     * @param row row of the actual cell
     * @return neighbor in the north, null if no neighbor
     */
    private Cell findNeighborNorth(int column, int row){
        Cell cell = null;
        for(int i=row-1;i>=0;i--){
            if(allCellsGrid[column][i]!=null){
                //System.out.println("Neighbor in the north found");
                return allCellsGrid[column][i];
            }
        }
        //System.out.println("No Neighbor in the north found");
        return cell;
    }
     /**
     * searches the GridArray of Cells for a neighbor in the south
     * @param column of the actual cell
     * @param row row of the actual cell
     * @return neighbor in the south, null if no neighbor
     */
    private Cell findNeighborSouth(int column, int row){
        Cell cell = null;
        for(int i=row+1;i<7;i++){
            if(allCellsGrid[column][i]!=null){
                return allCellsGrid[column][i];
            }
        }
        return cell;
    }
     /**
     * searches the GridArray of Cells for a neighbor in the east
     * @param column of the actual cell
     * @param row row of the actual cell
     * @return neighbor in the east, null if no neighbor
     */
    private Cell findNeighborEast(int column, int row){
        Cell cell = null;
        for(int i=column-1;i>=0;i--){
            if(allCellsGrid[i][row]!=null){
                return allCellsGrid[i][row];
            }
        }
        return cell;
    }
    /**
     * searches the GridArray of Cells for a neighbor in the west
     * @param column of the actual cell
     * @param row row of the actual cell
     * @return neighbor in the west, null if no neighbor
     */
    private Cell findNeighborWest(int column, int row){
        Cell cell = null;
        for(int i=column+1;i<7;i++){
            if(allCellsGrid[i][row]!=null){
                return allCellsGrid[i][row];
            }
        }
        return cell;
    }
    private void initializeNeighbors(){
        /*initalizing Vector of neighbors.
             * positions as following:
             * x 0 x
             * 1 X 2
             * x 3 x
             * 
             * every cell has 4 possible neighbors (0-3)
        */
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                Cell cell = allCellsGrid[i][j];
                if(cell!=null){
                    Cell[] neighbors = new Cell[4];
                    neighbors[0] = findNeighborNorth(i,j);
                    neighbors[1] = findNeighborEast(i,j);
                    neighbors[2] = findNeighborWest(i,j);
                    neighbors[3] = findNeighborSouth(i,j);
                    
                    cell.setNeighbors(neighbors);  
                }
            }
        }
    }
      
    /**
     *
     * @return an Array of all cells
     * first index = column
     * second index = row
     * 
     */
    public Cell[][] getAllCells(){
        return allCellsGrid;
    }
    
    /**
     * @return list of allCells
     *   allcells:
     *	0   1  2  3  4   5   6
     *0 00--------09--------21
     *1 |  03-----10-----18  |
     *2 |  |  06--11--15  |  |
     *3 01-04-07      16-19-22
     *4 |  |  08--12--17  |  |       
     *5 |  05-----13-----20  | 
     *6 02--------14--------23        
     */
    public ArrayList<Cell> getListAllCells(){
        return listAllCells;
    }

    /**
     * sets all possible muehle
     * ugly to do it manually, but hard to automate
     */
    private void setPossibleMuehle() {
        Cell[] possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[0][0];
        possible_muehle[1] = allCellsGrid[3][0];
        possible_muehle[2] = allCellsGrid[6][0];
        listPossibleMuehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[1][1];
        possible_muehle[1] = allCellsGrid[3][1];
        possible_muehle[2] = allCellsGrid[5][1];
        listPossibleMuehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[2][2];
        possible_muehle[1] = allCellsGrid[3][2];
        possible_muehle[2] = allCellsGrid[4][2];
        listPossibleMuehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[0][3];
        possible_muehle[1] = allCellsGrid[1][3];
        possible_muehle[2] = allCellsGrid[2][3];
        listPossibleMuehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[4][3];
        possible_muehle[1] = allCellsGrid[5][3];
        possible_muehle[2] = allCellsGrid[6][3];
        listPossibleMuehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[2][4];
        possible_muehle[1] = allCellsGrid[3][4];
        possible_muehle[2] = allCellsGrid[4][4];
        listPossibleMuehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[1][5];
        possible_muehle[1] = allCellsGrid[3][5];
        possible_muehle[2] = allCellsGrid[5][5];
        listPossibleMuehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[0][6];
        possible_muehle[1] = allCellsGrid[3][6];
        possible_muehle[2] = allCellsGrid[6][6];
        listPossibleMuehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[0][0];
        possible_muehle[1] = allCellsGrid[0][3];
        possible_muehle[2] = allCellsGrid[0][6];
        listPossibleMuehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[1][1];
        possible_muehle[1] = allCellsGrid[1][3];
        possible_muehle[2] = allCellsGrid[1][5];
        listPossibleMuehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[2][2];
        possible_muehle[1] = allCellsGrid[2][3];
        possible_muehle[2] = allCellsGrid[2][4];
        listPossibleMuehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[3][0];
        possible_muehle[1] = allCellsGrid[3][1];
        possible_muehle[2] = allCellsGrid[3][2];
        listPossibleMuehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[3][4];
        possible_muehle[1] = allCellsGrid[3][5];
        possible_muehle[2] = allCellsGrid[3][6];
        listPossibleMuehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[4][2];
        possible_muehle[1] = allCellsGrid[4][3];
        possible_muehle[2] = allCellsGrid[4][4];
        listPossibleMuehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[5][1];
        possible_muehle[1] = allCellsGrid[5][3];
        possible_muehle[2] = allCellsGrid[5][5];
        listPossibleMuehle.add(possible_muehle);
        
        possible_muehle = new Cell[3];
        possible_muehle[0] = allCellsGrid[6][0];
        possible_muehle[1] = allCellsGrid[6][3];
        possible_muehle[2] = allCellsGrid[6][6];
        listPossibleMuehle.add(possible_muehle);       
    }
    
    /**
     *
     * @return the List of all possible Muehle, each possiblity containing 3 cells
     */
    public ArrayList<Cell[]> getPossibleMuehle() {
        return listPossibleMuehle;
    }
    
    

}
