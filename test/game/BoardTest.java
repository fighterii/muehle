package game;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kai
 */
public class BoardTest {
    
    private Board board;
    private ArrayList<Cell> allcells;
    
    public BoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("BoardTest");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * DRYing out the creation of the Board and assigning allcells
     * because nearly every tests uses them
     */
    @Before
    public void setUp() {
        board = new Board();
        allcells = board.getAllCells();
    }
    
    @After
    public void tearDown() {
    }

  
    /**
     * Test if the neighbors are assigned correct
     *         allcells:
        00  .  .  01  .  .  02
        .  08  .  09  .  10  .
        .  .  16  17  18  .  .
        03 11 19  .   20 12 04
        .  .  21  22  23  .  .       
        .  13  .  14  .  15  . 
        05  .  .  06  .  .  07        
     * Checking for Neighbors of Cell 11 -> 8,3,19,13
     * Checking for Neighbors of Cell 8 -> -,-,9,11
     */
    @Test
    public void testNeighbors(){
        Cell cell3  = allcells.get(3);
        Cell cell8  = allcells.get(8);
        Cell cell9  = allcells.get(9);
        Cell cell11 = allcells.get(11);
        Cell cell13 = allcells.get(13);
        Cell cell19 = allcells.get(19);
        Cell[] neighbors11 = new Cell[4];
        neighbors11[0] = cell8;
        neighbors11[1] = cell3;
        neighbors11[2] = cell19;
        neighbors11[3] = cell13;
        Cell[] neighbors8 = new Cell[4];
        neighbors8[2] = cell9;
        neighbors8[3] = cell11;
        Assert.assertArrayEquals("Nachbarn der Zelle 11: 8,3,19,13",neighbors11,cell11.getNeighbors());
        Assert.assertArrayEquals("Nachbarn der Zelle 8: -,-,9,11",neighbors8,cell8.getNeighbors());
    }
  
    /**
     * Test if possible candidates for Muehle are assigned correctly
     */
    @Test
    public void testGetPossibleMuehle(){
        ArrayList<Cell[]> possible_muehle = board.getPossibleMuehle();
        Cell[] muehle1 = new Cell[3];
        muehle1[0] = allcells.get(3);
        muehle1[1] = allcells.get(11);
        muehle1[2] = allcells.get(19);
        Cell[] muehle2 = new Cell[3];
        muehle2[0] = allcells.get(2);
        muehle2[1] = allcells.get(4);
        muehle2[2] = allcells.get(7);
        Cell[] no_muehle = new Cell[3];
        no_muehle[0] = allcells.get(2);
        no_muehle[1] = allcells.get(3);
        no_muehle[2] = allcells.get(9);     
        boolean contains_muehle1 = listContainsCellArray(possible_muehle,muehle1);
        boolean contains_muehle2 = listContainsCellArray(possible_muehle,muehle2);
        boolean contains_no_muehle = listContainsCellArray(possible_muehle,no_muehle);
        assertTrue("List of possible Muehle should contain 3-11-19 (horizontal)",contains_muehle1);
        assertTrue("List of possible Muehle should contain 2-4-7 (vertical)",contains_muehle2);
        assertFalse("List of possible Muehle should not contain 2-3-9 (no Muehle)",contains_no_muehle);
        
    }
    
    private boolean listContainsCellArray(ArrayList<Cell[]> list, Cell[] arr){
        int listsize = list.size();
        for(int i=0;i<listsize;i++){
            Cell[] listelement = list.get(i);
            if(Arrays.equals(listelement, arr)){
                return true;
            }
        }
        return false;
    }
}
