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
     * Checks if the coord is Valid
     * @return true if position is valid
     */
    public boolean isValidPos() {
        if((x>=0)&(y>=0)&(x<7)&(y<7)){
            //check boundaries
            if(((y==0)|(y==6))&(x%3==0)){
                //outer circle, horizontal 
                //(0|0) (3|0) ...
                return true;
            }else if(((x==0)|(x==6))&(y%3==0)){
                //outer circle, vertical
                //(0|3)...
                return true;
            }else if(((y==1)|(y==5))&((x+1)%2==0)){
                //middle circle, horizontal
                //(1|1) (3|1) (5|1)
                return true;
            }else if(((x==1)|(x==5))&((y+1)%2==0)){
                //middle circle, vertical
                //(1|1) (1|3) (1|5)
                return true;
            }else if(((y==2)|(y==4))&((x>=2)&(x<=4))){
                //inner circle, horizontal
                //(2|2) (3|2) (4|2)
                return true;
            }else if(((x==2)|(x==4))&(y==3)){
                //missing cells inner circle, vertical
                return true;
            }     
        }
        return false;
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
