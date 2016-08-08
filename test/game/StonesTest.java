/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package game;

import java.util.ArrayList;
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
public class StonesTest {
    
    public StonesTest() {
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
     * Test of getNext_notset_stones method, of class Stones.
     */
    @Test
    public void testGetNext_notset_stones() {
        System.out.println("getNext_notset_stones");
        int color = 0;
        Stones instance = null;
        Stone expResult = null;
        Stone result = instance.getNext_notset_stones(color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSize_defeated_stones method, of class Stones.
     */
    @Test
    public void testGetSize_defeated_stones() {
        System.out.println("getSize_defeated_stones");
        int color = 0;
        Stones instance = null;
        int expResult = 0;
        int result = instance.getSize_defeated_stones(color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_stone method, of class Stones.
     */
    @Test
    public void testSet_stone() {
        System.out.println("set_stone");
        Stone stone = null;
        Stones instance = null;
        instance.set_stone(stone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of defeat_stone method, of class Stones.
     */
    @Test
    public void testDefeat_stone() {
        System.out.println("defeat_stone");
        Stone stone = null;
        Stones instance = null;
        instance.defeat_stone(stone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isStoneProtected method, of class Stones.
     */
    @Test
    public void testIsStoneProtected() {
        System.out.println("isStoneProtected");
        Stone stone = null;
        Stones instance = null;
        boolean expResult = false;
        boolean result = instance.isStoneProtected(stone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recalculateProtected method, of class Stones.
     */
    @Test
    public void testRecalculateProtected() {
        System.out.println("recalculateProtected");
        Stones instance = null;
        instance.recalculateProtected();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIfStonePartOfMuehle method, of class Stones.
     */
    @Test
    public void testCheckIfStonePartOfMuehle() {
        System.out.println("checkIfStonePartOfMuehle");
        Stone stone = null;
        Stones instance = null;
        boolean expResult = false;
        boolean result = instance.checkIfStonePartOfMuehle(stone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStoneOnPos method, of class Stones.
     */
    @Test
    public void testGetStoneOnPos() {
        System.out.println("getStoneOnPos");
        Cell cell = null;
        Stones instance = null;
        Stone expResult = null;
        Stone result = instance.getStoneOnPos(cell);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStones method, of class Stones.
     */
    @Test
    public void testGetStones() {
        System.out.println("getStones");
        int color = 0;
        Stones instance = null;
        ArrayList<Stone> expResult = null;
        ArrayList<Stone> result = instance.getStones(color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSet_stones method, of class Stones.
     */
    @Test
    public void testGetSet_stones() {
        System.out.println("getSet_stones");
        int color = 0;
        Stones instance = null;
        ArrayList<Stone> expResult = null;
        ArrayList<Stone> result = instance.getSet_stones(color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNotset_stones method, of class Stones.
     */
    @Test
    public void testGetNotset_stones() {
        System.out.println("getNotset_stones");
        int color = 0;
        Stones instance = null;
        ArrayList<Stone> expResult = null;
        ArrayList<Stone> result = instance.getNotset_stones(color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefeated_stones method, of class Stones.
     */
    @Test
    public void testGetDefeated_stones() {
        System.out.println("getDefeated_stones");
        int color = 0;
        Stones instance = null;
        ArrayList<Stone> expResult = null;
        ArrayList<Stone> result = instance.getDefeated_stones(color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProtected_stones method, of class Stones.
     */
    @Test
    public void testGetProtected_stones() {
        System.out.println("getProtected_stones");
        int color = 0;
        Stones instance = null;
        ArrayList<Stone> expResult = null;
        ArrayList<Stone> result = instance.getProtected_stones(color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
