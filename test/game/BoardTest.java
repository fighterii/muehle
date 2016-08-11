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
    private ArrayList<Cell> listAllCells;
    
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
        listAllCells = board.getListAllCells();
    }
    
    @After
    public void tearDown() {
    }

  
    /**
     * Test if the neighbors are assigned correct
     *         allcells:
	0   1  2  3   4  5   6
      0 00--------09--------21
      1 |  03-----10-----18  |
      2 |  |  06--11--15  |  |
      3 01-04-07      16-19-22
      4 |  |  08--12--17  |  | 
      5 |  05-----13-----20  | 
      6 02--------14--------23      
     * Checking for Neighbors of Cell 10 -> 9,3,18,11
     * Checking for Neighbors of Cell 0 -> -,-,9,1
     */
    @Test
    public void testNeighbors(){
        System.out.println("testNeighbors");
        Cell cell3  = listAllCells.get(3);
        Cell cell0  = listAllCells.get(0);
        Cell cell9  = listAllCells.get(9);
        Cell cell10 = listAllCells.get(10);
        Cell cell11 = listAllCells.get(11);
        Cell cell18 = listAllCells.get(18);
        Cell cell1 = listAllCells.get(1);
        Cell[] neighbors10 = new Cell[4];
        neighbors10[0] = cell9;
        neighbors10[1] = cell3;
        neighbors10[2] = cell18;
        neighbors10[3] = cell11;
        System.out.println("Cell 10"+cell10);
        System.out.println("Cell 9"+cell9);
        System.out.println("Cell 3"+cell3);
        System.out.println("Cell 18"+cell18);
        System.out.println("Cell 11"+cell11);
        Cell[] neighbors0 = new Cell[4];
        neighbors0[2] = cell9;
        neighbors0[3] = cell1;
        Assert.assertArrayEquals("Nachbarn der Zelle 10: 8,3,19,13",neighbors10,cell10.getNeighbors());
        Assert.assertArrayEquals("Nachbarn der Zelle 8: -,-,9,11",neighbors0,cell0.getNeighbors());
    }
  
    /**
     * Test if possible candidates for Muehle are assigned correctly
     */
    @Test
    public void testGetPossibleMuehle(){
        ArrayList<Cell[]> possible_muehle = board.getPossibleMuehle();
        Cell[] muehle1 = new Cell[3];
        muehle1[0] = listAllCells.get(6);
        muehle1[1] = listAllCells.get(11);
        muehle1[2] = listAllCells.get(15);
        Cell[] muehle2 = new Cell[3];
        muehle2[0] = listAllCells.get(12);
        muehle2[1] = listAllCells.get(13);
        muehle2[2] = listAllCells.get(14);
        Cell[] no_muehle = new Cell[3];
        no_muehle[0] = listAllCells.get(2);
        no_muehle[1] = listAllCells.get(3);
        no_muehle[2] = listAllCells.get(9);     
        boolean contains_muehle1 = listContainsCellArray(possible_muehle,muehle1);
        boolean contains_muehle2 = listContainsCellArray(possible_muehle,muehle2);
        boolean contains_no_muehle = listContainsCellArray(possible_muehle,no_muehle);
        assertTrue("List of possible Muehle should contain 6-11-15 (horizontal)",contains_muehle1);
        assertTrue("List of possible Muehle should contain 12-13-14 (vertical)",contains_muehle2);
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
