/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package game;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 *
 * @author Kai
 */
public class MuehleTest {
    private Board board;
    private Muehle muehleGame;
    private Field currentMoveField;
    private TestPlayer whitePlayer;
    private TestPlayer blackPlayer;
    private Stones stones;
    public MuehleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("MuehleTest");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        this.muehleGame = new Muehle();
        //creating the board
        this.board = muehleGame.getBoard();
        System.out.println(muehleGame);
        System.out.println(board);
        this.stones = muehleGame.getStones();
        //creating two players and attach them to the game
        whitePlayer = new TestPlayer(muehleGame,1);
        blackPlayer = new TestPlayer(muehleGame,2); 
        
        currentMoveField = Muehle.class.getDeclaredField("current_move");
        currentMoveField.setAccessible(true);
        // then we attach the clients/players to the game
        muehleGame.setPlayer(1, whitePlayer);
        muehleGame.setPlayer(2, blackPlayer);
        
        //call stop game to prevent game loop from being exceuted
        muehleGame.stopGame();
        //call start game to initialize vars but because we called stop game before
        //the game loop waiting for the moves is not executed
        muehleGame.startGame();
    }
    
    @After
    public void tearDown(){
    }

   
    /**
     * Test of getMoveValidator method, of class Muehle.
     */
    @Test
    public void testGetMoveValidator() {
        System.out.println("getMoveValidator");
        MoveValidator notExpResult = null;
        MoveValidator result = muehleGame.getMoveValidator();
        assertNotEquals("When initialized, moveValidator should not be null",notExpResult, result);
    }
    /**
     * Test of getStep method, of class Muehle.
     */
    @Test
    public void testGetStep() {
        System.out.println("getStep");
        int expResult = 0;
        int result = muehleGame.getStep();
        assertEquals("When initialized, step should be 0",expResult, result);
    }

    /**
     * Test of changeGameState method, of class Muehle.
     */
    @Test
    public void testChangeGameState() throws IllegalAccessException{
        System.out.println("changeGameState");
        
        IPlayerHandler expActivePlayer = muehleGame.getWhitePlayer();
        IPlayerHandler activePlayer = muehleGame.getActivePlayer();
        assertEquals("After initialization activePlayer should be whitePlayer",expActivePlayer,activePlayer);
        
        muehleGame.changeGameState();
        expActivePlayer = muehleGame.getBlackPlayer();
        activePlayer = muehleGame.getActivePlayer();
        assertEquals("After changeGameState activePlayer should be blackPlayer",expActivePlayer,activePlayer);
        int expResult = 1;
        int result = muehleGame.getStep();
        assertEquals("After changeGameState, step should be increased by 1",expResult,result);
        
        muehleGame.changeGameState();
        expActivePlayer = muehleGame.getWhitePlayer();
        activePlayer = muehleGame.getActivePlayer();
        assertEquals("After calling changeGameState the second time activePlayer should be whitePlayer",expActivePlayer,activePlayer);       
        
    }

    /**
     * Test of getWhitePlayer method, of class Muehle.
     */
    @Test
    public void testGetWhitePlayer() {
        System.out.println("getWhitePlayer");
        IPlayerHandler expResult = whitePlayer;
        IPlayerHandler result = muehleGame.getWhitePlayer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBlackPlayer method, of class Muehle.
     */
    @Test
    public void testGetBlackPlayer() {
        System.out.println("getBlackPlayer");
        IPlayerHandler expResult = blackPlayer;
        IPlayerHandler result = muehleGame.getBlackPlayer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBoard method, of class Muehle.
     */
    @Test
    public void testGetBoard() {
        System.out.println("getBoard");
        Board expResult = board;
        Board result = muehleGame.getBoard();
        assertEquals(expResult, result);
    }

    /**
     * Test of isSecond_move method, of class Muehle.
     */
    @Test
    public void testIsSecond_move() {
        System.out.println("isSecond_move");
        boolean expResult = false;
        boolean result = muehleGame.isSecond_move();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of waitForMove method, of class Muehle.
     * method is private and called within game loop
     * gets a valid move from TestPlayer (set stone to free pos)
     * moves stone
     */
    @Test
    public void testWaitForMove_ValidMove() throws Exception{
        System.out.println("testWaitForMove_ValidMove");
        Cell cell0 = board.getAllCells().get(0);
        whitePlayer.move = new Move(stones.getNextNotsetStone(1),cell0);
        
        Method method_waitForMove = Muehle.class.getDeclaredMethod("waitForMove", (Class<?>[]) null);
        method_waitForMove.setAccessible(true);
        method_waitForMove.invoke(muehleGame, null);
        
        int expResult = 8;
        int result = stones.getNotsetStones(1).size();
        assertEquals("After setting one stone, size of notset_stones for Player 1 should be 8",expResult,result);
    }
        /**
     * Test of waitForMove method, of class Muehle.
     * method is private and called within game loop
     * gets a invalid move from TestPlayer (set stone to null pos)
     * moves stone
     */
    @Test(expected=InvocationTargetException.class)
    public void testWaitForMove_InvalidMove() throws Exception{
        System.out.println("testWaitForMove_InvalidMove");
        Cell cell0 = board.getAllCells().get(0);
        whitePlayer.move = new Move(stones.getNextNotsetStone(1),null);
        
        Method method_waitForMove = Muehle.class.getDeclaredMethod("waitForMove", (Class<?>[]) null);
        method_waitForMove.setAccessible(true);
        method_waitForMove.invoke(muehleGame, null);

    }
    
}
