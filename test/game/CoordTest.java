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
public class CoordTest {
    
    public CoordTest() {
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
     *Tests that the Class coord returns the correct X coordinate
     */
    @Test
    public void testGetX() {
        Coord coord1 = new Coord(1,2);
        assertEquals("X Coord should be 1",1,coord1.getX());
    }
    
     /**
     *Tests that the Class coord returns the correct Y coordinate
     */
    @Test
    public void testGetY() {
        Coord coord1 = new Coord(1,2);
        assertEquals("Y Coord should be 2",2,coord1.getY());
    }
    
    /**
    *Tests the Class valid positions
    * Valid positions are all pairs with each element greater than 0 and smaller than 3
    * (1,1) also invalid
    */   
    @Test
    public void testIsValidPos() {
        Coord coord1 = new Coord(0,0);
        assertTrue("Coord(0,0) should be valid position",coord1.isValidPos());
        Coord coord2 = new Coord(0,-1);
        assertFalse("Coord(0,-1) should be invalid position",coord2.isValidPos());
        Coord coord3 = new Coord(-1,0);
        assertFalse("Coord(-1,0) should be invalid position",coord3.isValidPos());
        Coord coord4 = new Coord(0,3);
        assertTrue("Coord(0,3) should be valid position",coord4.isValidPos());
        Coord coord5 = new Coord(2,0);
        assertFalse("Coord(2,0) should be invalid position",coord5.isValidPos());
    }

    /**
     * Test of equals method, of class Coord.
     */
    @Test
    public void testEqualsHappyPath() {
        System.out.println("equals");
        Coord obj = new Coord(0,0);
        Coord instance = new Coord(0,0);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals("Two Coord obj should be equal, if x and y coord are the same",expResult, result);
    }
        /**
     * Test of equals method, of class Coord.
     */
    @Test
    public void testEqualsSadPath() {
        System.out.println("equals");
        Coord obj = new Coord(0,0);
        Coord instance = new Coord(0,1);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertNotEquals("Two coord obj should not be equal, if x and y differ",expResult,result);
    }
    
}
