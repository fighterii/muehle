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
public class MoveValidatorTest {
    private Board board;
    private ArrayList<Cell> listAllCells;
    private Cell cell0;
    private Cell cell1;
    private Cell cell2;
    private Stone wStone1;
    private Stone bStone1;  
    private Muehle muehleGame;
    private Stones stones;
    private MoveValidator moveValidator;
    private Field activePlayerField;
    public MoveValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("MoveValidatorTest");
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
        //creating two players and attach them to the game
        TestPlayer ai1 = new TestPlayer(muehleGame,1);
        TestPlayer ai2 = new TestPlayer(muehleGame,2);            
        // then we attach the clients/players to the game
        muehleGame.setPlayer(1, ai1);
        muehleGame.setPlayer(2, ai2);
        this.moveValidator = new MoveValidator(muehleGame);
        activePlayerField = Muehle.class.getDeclaredField("activePlayer");
        activePlayerField.setAccessible(true);
        activePlayerField.set(muehleGame, muehleGame.getWhitePlayer());
        // muehleGame set starting player to white
        this.listAllCells = board.getListAllCells();
        this.stones = muehleGame.getStones();
        cell0 = listAllCells.get(0); 
        cell1 = listAllCells.get(1);
        cell2 = listAllCells.get(2);
        wStone1 = stones.getNextNotsetStone(1);
        bStone1 = stones.getNextNotsetStone(2);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isValidMove method, of class MoveValidator.
     * setting stone on a free position is a valid move.
     */
    @Test
    public void testIsValidMove_SetStoneOnFreePosition() {
        System.out.println("isValidMove setStoneOnFreePosition");
        Move move = new Move(wStone1,cell1);
        boolean expResult = true;
        boolean result = moveValidator.isValidMove(move);
        assertEquals("If i set stone on free position, move should be valid",expResult, result);
    }
    /**
     * Test of isValidMove method, of class MoveValidator.
     * setting stone on a occupied position is an invalid move.
     */
    @Test
    public void testIsValidMove_SetStoneOnOccupiedPosition() {
        System.out.println("isValidMove setStoneOnOccupiedPosition");
        //setting stoneColor of cell1 to black -> this cell is occupied
        cell1.setStoneColor(2);
        Move move = new Move(wStone1,cell1);
        boolean expResult = false;
        boolean result = moveValidator.isValidMove(move);
        assertEquals("If i set stone on occupied position, move should be invalid",expResult, result);
    }
    /**
     * Test of isValidMove method, of class MoveValidator.
     * moving stone of opposite player is an invalid move.
     */
    @Test
    public void testIsValidMove_MovingStoneOfEnemy() {
        System.out.println("isValidMove moveStoneOfEnemy");
        //setting black stone on cell0
        bStone1.setPos(cell0);
        //trying to move black_stone
        //but activeplayer is white
        Move move = new Move(bStone1,cell1);
        boolean expResult = false;
        boolean result = moveValidator.isValidMove(move);
        assertEquals("If i move black stone as white player, move should be invalid",expResult, result);
    }
    /**
     * Test of isValidMove method, of class MoveValidator.
     * moving stone to neighbor cell is valid move
     */
    @Test
    public void testIsValidMove_MovingStoneToNeighborCell() {
        System.out.println("isValidMove moveStoneToNeighbor");
        //setting white stone on cell0
        wStone1.setPos(cell0);
        //trying to move white stone to cell1
        //cell1 is neighbor of cell0
        Move move = new Move(wStone1,cell1);
        boolean expResult = true;
        boolean result = moveValidator.isValidMove(move);
        assertEquals("If i move stone from cell0 to cell1, move should be valid",expResult, result);
    }
    /**
     * Test of isValidMove method, of class MoveValidator.
     * moving stone to not neighbor cell is invalid move
     */
    @Test
    public void testIsValidMove_MovingStoneToNotNeighborCell() {
        System.out.println("isValidMove moveStoneToNotNeighbor");
        //setting white stone on cell0
        wStone1.setPos(cell0);
        //trying to move white stone to cell2
        //cell2 is not neighbor of cell0
        Move move = new Move(wStone1,cell2);
        boolean expResult = false;
        boolean result = moveValidator.isValidMove(move);
        assertEquals("If i move stone from cell0 to cell2, move should be invalid",expResult, result);
    }    
    /**
     * Test of isValidMove method, of class MoveValidator.
     * defeating stone of opposite player is valid if i am allowed to defeat.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testIsValidMove_DefeatingStone() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("isValidMove defeatingStone");
        //setting black stone on cell0
        bStone1.setPos(cell0);
        cell0.setStoneColor(2);
         //manipulating second_move var
        Field field;
        field = Muehle.class.getDeclaredField("second_move");
        field.setAccessible(true);
        field.set(muehleGame, true);
        //trying to defeat black stone on cell0
        Move move = new Move(bStone1,null);
        boolean expResult = true;
        boolean result = moveValidator.isValidMove(move);
        assertEquals("If i defeat enemy stone and second_move is true, move should be valid",expResult, result);
    }
    /**
     * Test of isValidMove method, of class MoveValidator.
     * defeating stone of opposite player is invalid if i am not allowed to defeat.
     */
    @Test
    public void testIsValidMove_DefeatingStoneNotAllowed(){
        System.out.println("isValidMove defeatingStoneNotAllowed");
        //setting black stone on cell0
        bStone1.setPos(cell0);
        cell0.setStoneColor(2);
        //trying to defeat black stone on cell0
        Move move = new Move(bStone1,null);
        boolean expResult = false;
        boolean result = moveValidator.isValidMove(move);
        assertEquals("If i defeat enemy stone and second_move is false, move should be invalid",expResult, result);
    }
    /**
     * Test of isValidMove method, of class MoveValidator.
     * defeating own stone  is invalid.
     */
    @Test
    public void testIsValidMove_DefeatingOwnStone() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("isValidMove defeatingOwnStone");
        //setting black stone on cell0
        wStone1.setPos(cell0);
        cell0.setStoneColor(2);
        //manipulating second_move var
        Field field;
        field = Muehle.class.getDeclaredField("second_move");
        field.setAccessible(true);
        field.set(muehleGame, true);
        //trying to defeat white stone on cell0
        Move move = new Move(wStone1,null);
        boolean expResult = false;
        boolean result = moveValidator.isValidMove(move);
        assertEquals("If i defeat my own stone and second_move is true, move should be invalid",expResult, result);
    }
     /**
     * Test of isValidMove method, of class MoveValidator.
     * target dest of defeating should be null
     */
    @Test
    public void testIsValidMove_TargetDestDefeating() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("isValidMove targetDestDefeating");
        //setting black stone on cell0
        bStone1.setPos(cell0);
        cell0.setStoneColor(2);
        //manipulating second_move var
        Field field;
        field = Muehle.class.getDeclaredField("second_move");
        field.setAccessible(true);
        field.set(muehleGame, true);
        //trying to defeat black stone on cell0
        //invalid target pos
        Move move = new Move(bStone1,cell1);
        boolean expResult = false;
        boolean result = moveValidator.isValidMove(move);
        assertEquals("If i defeat with a target cell other than null, move should be invalid",expResult, result);
    }
     /**
     * Test of isValidMove method, of class MoveValidator.
     * setting stone for black player, sad path
     */
    @Test
    public void testIsValidMove_BlackPlayerSad() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        System.out.println("isValidMove blackPlayerSad");
        //activePlayer = white
        Move move = new Move(bStone1,cell1);
        boolean expResult = false;
        boolean result = moveValidator.isValidMove(move);
        assertEquals("If i try to set black stone when activePlayer is white, move should be invalid",expResult, result);    
        
    }
    /**
     * Test of isValidMove method, of class MoveValidator.
     * setting stone for black player, happy path
     */
    @Test
    public void testIsValidMove_BlackPlayerHappy() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        System.out.println("isValidMove blackPlayerHappy");
        //setting Black Player to Active
        activePlayerField.set(muehleGame, muehleGame.getBlackPlayer());
        System.out.println("BlackPlayer:"+muehleGame.getBlackPlayer());
        System.out.println("ActivePlayer:"+muehleGame.getActivePlayer());
        
        Move move = new Move(bStone1,cell1);
        boolean expResult = true;
        boolean result = moveValidator.isValidMove(move);
        assertEquals("If i try to set black stone when activePlayer is black, move should be valid",expResult, result);              
        
    }
}
