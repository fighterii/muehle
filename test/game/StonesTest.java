/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package game;

import java.lang.reflect.Field;
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
    
    private Stones stones;
    private Board board;
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
        board = new Board();
        stones = new Stones(board);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getNextNotsetStone method, of class Stones.
     */
    @Test
    public void testGetNextNotsetStone() {
        System.out.println("getNext_notset_stones");
        int color = 1;
        Stone stone = stones.getNextNotsetStone(color);
        assertEquals("Returned stone should have color 1",1, stone.getColor());
        assertEquals("Returned stone should have no position",null, stone.getPos());
    }

    /**
     * Test of getSizeDefeatedStones method, of class Stones.
     */
    @Test
    public void testGetSizeDefeatedStones() {
        System.out.println("getSize_defeated_stones");
        int color = 1;
        int expResult = 0;
        int result = stones.getSizeDefeatedStones(color);
        assertEquals("After initialization, 0 stones should be defeated",expResult, result);
    }

    /**
     * Test of setStone method, of class Stones.
     */
    @Test
    public void testSetStone() {
        System.out.println("set_stone");
        int whiteColor = 1;
        int blackColor = 2;
        Stone whiteStone = stones.getNextNotsetStone(whiteColor);
        Stone blackStone = stones.getNextNotsetStone(blackColor);
        
        int oldSizeNotSetWhite = stones.getNotsetStones(whiteColor).size();
        int oldSizeSetWhite = stones.getSetStones(whiteColor).size();
        int oldSizeNotProtectedWhite = stones.getNotProtectedStones(whiteColor).size();
        
        int oldSizeNotSetBlack = stones.getNotsetStones(blackColor).size();
        int oldSizeSetBlack = stones.getSetStones(blackColor).size();
        int oldSizeNotProtectedBlack = stones.getNotProtectedStones(blackColor).size();
        //set a stone (meaning remove them from the list of notset and adding to the list of set)
        //pos is set within a different method
        //this method is only about handling the lists correctly
        stones.setStone(whiteStone);
        stones.setStone(blackStone);
        
        int resultWhite = stones.getSetStones(whiteColor).size();
        int expResultWhite = oldSizeSetWhite +1;   
        int resultBlack = stones.getSetStones(blackColor).size();
        int expResultBlack = oldSizeSetBlack +1;
        assertEquals("After setting a white stone, list of white stones should have size oldSizeSet+1",expResultWhite,resultWhite);
        assertEquals("After setting a black stone, list of black stones should have size oldSizeSet+1",expResultBlack,resultBlack);
        
        resultWhite = stones.getNotsetStones(whiteColor).size();
        expResultWhite = oldSizeNotSetWhite -1;
        resultBlack = stones.getNotsetStones(blackColor).size();
        expResultBlack = oldSizeNotSetBlack -1;
        assertEquals("After setting a white stone, list of white stones should have oldSizeNotSet-1",expResultWhite,resultWhite);       
        assertEquals("After setting a black stone, list of black stones should have oldSizeNotSet-1",expResultBlack,resultBlack); 
        
        resultWhite = stones.getNotProtectedStones(whiteColor).size();
        expResultWhite = oldSizeNotProtectedWhite + 1;
        resultBlack = stones.getNotProtectedStones(blackColor).size();
        expResultBlack = oldSizeNotProtectedBlack + 1;
        assertEquals("After setting a white stone, list of white not protected stones should have oldSizeNotProtected+1",expResultWhite,resultWhite);       
        assertEquals("After setting a black stone, list of black not protected stones should have oldSizeNotProtected+1",expResultBlack,resultBlack); 
    }

    /**
     * Test of defeatStone method, of class Stones.
     */
    @Test
    public void testDefeatStone() {
        System.out.println("defeat_stone");
        int color = 1;
        Stone stone = stones.getNextNotsetStone(color);
        stones.setStone(stone);
        int oldSizeDefeated = stones.getSizeDefeatedStones(color);
        int oldSizeSet = stones.getSetStones(color).size();
        int oldSizeNotProtected = stones.getNotProtectedStones(color).size();
        
        //this method only handles the lists
        //should remove the stone from the list of setStones and add it to list of defeated
        stones.defeatStone(stone);

        int result = stones.getSetStones(color).size();
        int expResult = oldSizeSet -1;
        assertEquals("After defeating a stone, list of stones should have size oldSizeSet-1",expResult,result);
        
        result = stones.getSizeDefeatedStones(color);
        expResult = oldSizeDefeated +1;
        assertEquals("After defeating a stone, list of defeatedStones should have oldSizeDefeated+1",expResult,result);       
        
        result = stones.getNotProtectedStones(color).size();
        expResult = oldSizeNotProtected -1;
        assertEquals("After defeating a stone, list of notProtectedStones should have oldSizeNotProtected-1",expResult,result);    
    }

    /**
     * Test of isStoneProtected method, of class Stones.
     */
    @Test
    public void testIsStoneProtected_False() {
        System.out.println("isStoneProtected");
        int color = 1;
        Stone stone = stones.getNextNotsetStone(color);
        stone.setPos(board.getAllCells().get(0));
        stones.setStone(stone);
        boolean expResult = false;
        boolean result = stones.isStoneProtected(stone);
        assertEquals("If stone is not on List of protectedStones, it should return false",expResult, result);
    }
    /**
     * Test of isStoneProtected method, of class Stones.
     */
    @Test
    public void testIsStoneProtected_True() throws Exception {
        System.out.println("isStoneProtected");
        int color = 1;
        //setting 3 stones of player white in a line -> should be protected
        Stone stone = stones.getNextNotsetStone(color);
        stone.setPos(board.getAllCells().get(0));
        stones.setStone(stone);
        Field protectedStonesField = Stones.class.getDeclaredField("wProtectedStones");
        protectedStonesField.setAccessible(true);
        ArrayList<Stone> protectedStones = stones.getProtectedStones(color);
        protectedStones.add(stone);
        protectedStonesField.set(stones, protectedStones);
        boolean expResult = true;
        boolean result = stones.isStoneProtected(stone);
        assertEquals("If stone is on List of protectedStones, it should return true",expResult, result);
    }

    /**
     * Test of iSStonePartOfMuehle method, of class Stones.
     */
    @Test
    public void testIsStonePartOfMuehle_False() {
        System.out.println("checkIfStonePartOfMuehle");
        int color = 1;
        Stone stone = stones.getNextNotsetStone(color);
        stone.setPos(board.getAllCells().get(0));
        stones.setStone(stone);
        boolean expResult = false;
        boolean result = stones.iSStonePartOfMuehle(stone);
        assertEquals("If stone is not part of a Muehle, method should return false",expResult, result);
    }
 
    /**
     * Test of iSStonePartOfMuehle method, of class Stones.
     */   
    @Test
    public void testIsStonePartOfMuehle_True() {
        System.out.println("isStoneProtected");
        int color = 1;
        //setting 3 stones of player white in a line -> should be protected
        Stone stone = set3StonesWhite();
        
        boolean expResult = true;
        boolean result = stones.iSStonePartOfMuehle(stone);
        assertEquals("If stone is part of a Muehle, it should return true",expResult, result);
    }

    /**
     * Test of getStoneOnPos method, of class Stones.
     */
    @Test
    public void testGetStoneOnPos_White() {
        System.out.println("getStoneOnPos");
        Cell cell = board.getAllCells().get(0);
        Stone stone = stones.getNextNotsetStone(1);
        stone.setPos(cell);
        stones.setStone(stone);
        Stone expResult = stone;
        Stone result = stones.getStoneOnPos(cell);
        assertEquals("If Stone of Player white is on Cell0, it should return this stone",expResult, result);
    }
        /**
     * Test of getStoneOnPos method, of class Stones.
     */
    @Test
    public void testGetStoneOnPos_Black() {
        System.out.println("getStoneOnPos");
        Cell cell = board.getAllCells().get(0);
        Stone stone = stones.getNextNotsetStone(2);
        stone.setPos(cell);
        stones.setStone(stone);
        Stone expResult = stone;
        Stone result = stones.getStoneOnPos(cell);
        assertEquals("If Stone of Player black is on Cell0, it should return this stone",expResult, result);
    }
     /**
     * Test of getStoneOnPos method, of class Stones.
     * cell0 is empty
     */
    @Test
    public void testGetStoneOnPos_NoStone() {
        System.out.println("getStoneOnPos");
        Cell cell = board.getAllCells().get(0);
        Stone expResult = null;
        Stone result = stones.getStoneOnPos(cell);
        assertEquals("If cell0 is empty, it should return null",expResult, result);
    }

    @Test
    public void testRecalculateProtected(){
        System.out.println("recalculate protected");
        //first set 3 stones on 0,1,2
        set3StonesWhite();
        //then set 2 stones on 3,5
        //this creates 2 muehle, with the stone on 0 double
        Stone stone = stones.getNextNotsetStone(1);
        stone.setPos(board.getAllCells().get(3));
        stones.setStone(stone);
        
        stone = stones.getNextNotsetStone(1);
        stone.setPos(board.getAllCells().get(5));
        stones.setStone(stone);
        
        //also set one black stone
        stone = stones.getNextNotsetStone(2);
        stone.setPos(board.getAllCells().get(6));
        stones.setStone(stone);
        
        stones.recalculateProtected();
        //expect list of protected stones have size 5 for white and 0 for black
        //even if stone on cell0 is within two muehle, it should only be counted once
        int expResultWhite = 5;
        int expResultBlack = 0;
        int resultWhite = stones.getProtectedStones(1).size();
        int resultBlack = stones.getProtectedStones(2).size();
        assertEquals("List of protected stones should have size 5 for white",expResultWhite,resultWhite);
        assertEquals("List of protected stones should have size 0 for black",expResultBlack,resultBlack);
        //expect list of notProtected stones have size 0 for white and 1 for black
        expResultWhite = 0;
        expResultBlack = 1;
        resultWhite = stones.getNotProtectedStones(1).size();
        resultBlack = stones.getNotProtectedStones(2).size();
        assertEquals("List of notProtected stones should have size 0 for white",expResultWhite,resultWhite);
        assertEquals("List of notProtected stones should have size 1 for black",expResultBlack,resultBlack);
    }
    
    /**
     * sets 3 stones in a row on cell 0,1,2
     * 
     * @return last stone on cell2
     */
    private Stone set3StonesWhite(){
        System.out.println("set3StonesWhite in a row");
        int color = 1;
        //setting 3 stones of player white in a line -> should be protected
        Stone stone = stones.getNextNotsetStone(color);
        stone.setPos(board.getAllCells().get(0));
        stones.setStone(stone);
        
        stone = stones.getNextNotsetStone(color);
        stone.setPos(board.getAllCells().get(1));
        stones.setStone(stone);
        
        stone = stones.getNextNotsetStone(color);
        stone.setPos(board.getAllCells().get(2));
        stones.setStone(stone);
        return stone;
    }
}
