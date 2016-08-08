package game;

/**
 *
 * @author Kai
 */
public class Cell {
    /*
     * Cell is used to represent one Junction on the board
     * Cell consists of:
     * - Coord pos: Own position coordinates on the grid
     * - Coord[] neighbors: List of all neighbor coordinates
     * - int stoneColor: 
        0: no stoneColor
        1: white
        2: black
     */

    private Cell[] neighbors;

    private Coord pos;

    private int stoneColor;

    public Cell(Coord pos){
        //Check if the provided position is a valid position for a Cell.
        if(pos.isValidPos()){
            this.pos = pos;
            this.stoneColor = 0;

            /* initalizing Vector of neighbors.
             * positions as following:
             * x 0 x
             * 1 X 2
             * x 3 x
             * 
             * every cell has 4 possible neighbors (0-3)
             */

            neighbors = new Cell[4];   
        }else{
            throw new IllegalArgumentException("Coordinate is invalid");
        }

    }

    /**
     * @return the neighbors
     */
    public Cell[] getNeighbors() {
        return neighbors;
    }

    /**
     * set Array of neighbors of the cell
     * @param neighbors is Cell[3] of neighbor cells, 0: north, 1:west, 2:east, 3:south 
     */
    public void setNeighbors(Cell[] neighbors) {
        this.neighbors = neighbors;
    }

    /**
     *
     * @param stoneColor
     * 0: no stoneColor
     * 1: white stoneColor
     * 2: black stoneColor
     */
    public void setStoneColor(int stoneColor) {
        this.stoneColor = stoneColor;
    }

    /**
     * @return the pos
     */
    public Coord getPos() {
        return pos;
    }

    /**
     *
     * @return the color of the stoneColor:
     * 0: free
     * 1: white stoneColor
     * 2: black stoneColor
     */
    public int getStoneColor() {
        return stoneColor;
    }

    @Override
    public String toString() {
        return "Cell{" + "pos=" + pos + ", stone=" + stoneColor + '}' +'@' + Integer.toHexString(hashCode());
    }

}
