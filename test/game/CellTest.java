package game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kai
 */
public class CellTest {
    
    public CellTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getNeighbors method, of class Cell.
     */
    @Test
    public void testGetNeighbors() {
        System.out.println("getNeighbors");
        Cell instance = new Cell(new Coord(0,0));
        Cell cell_east = new Cell(new Coord(3,0));
        Cell cell_south = new Cell(new Coord(0,3));
        Cell[] neighbors = new Cell[4];
        Cell[] expResult = neighbors;
        Cell[] result = instance.getNeighbors();
        assertArrayEquals("Should return empty array after creation",expResult, result);
        neighbors[2] = cell_east;
        neighbors[3] = cell_south;
        instance.setNeighbors(neighbors);
        expResult = neighbors;
        result = instance.getNeighbors();
        assertArrayEquals("Should return correct neighbors after they were set",expResult, result);
    }

    /**
     * Test of getPos method, of class Cell.
     */
    @Test
    public void testGetPos() {
        System.out.println("getPos");
        Coord upper_left_corner  = new Coord(0,0);
        Cell instance = new Cell(upper_left_corner);
        Coord expResult = upper_left_corner;
        Coord result = instance.getPos();
        assertEquals("Should return correct position",expResult, result);
    }

    /**
     * Test of getStoneColor method, of class Cell.
     */
    @Test
    public void testGetStoneColor() {
        System.out.println("getStone");
        Cell instance = new Cell(new Coord(0,0));
        int expResult = 0;
        int result = instance.getStoneColor();
        assertEquals("Should return 0 after creation",expResult, result);
        expResult = 1;
        instance.setStoneColor(1);
        result = instance.getStoneColor();
        assertEquals("Should return 1 after Stone has been set to 1",expResult, result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCoord(){
        Cell instance = new Cell(new Coord(-1,0));       
    }
}
