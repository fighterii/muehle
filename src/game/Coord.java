package game;

/**
 *
 * @author Kai
 */
public class Coord {
    /**
     * represents the Coordination of an object on the board, relative to its circle
     */
    private int x;
    private int y;

    
    public Coord(int x, int y) {
            // x is the column coordinate,
            // y is the row coordinate
            this.x = x;
            this.y = y;
    }

    /** 
     * Checks if the position is part of the circle 
     * 1 1 1 0
     * 1 0 1 0
     * 1 1 1 0
     * 0 0 0 0
     * 1 = valid
     * @return true if position is valid
     */
    public boolean isValidPos() {
        if ((x==1)&&(y==1)){
            return false;
        }else return !((x<0)|(y<0)|(x>2)|(y>2));
    }
    
   
    /**
     * @return the x
     */
    public int getX() {
            return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
            this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
            return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
            this.y = y;
    }    

    /**
     * this new equals methods makes sure, that only the x and y coordinates of 
     * a Coord obj matter (and not the two Coord obj have to be the same obj)
     * @param obj Coord to compare for equality
     * @return true if obj are equal (they have the same coord);
     */
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord other = (Coord) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.x;
        hash = 47 * hash + this.y;
        return hash;
    }

    @Override
    public String toString() {
        return "Coord{" + "x=" + x + ", y=" + y + '}';
    }
        
        
}
