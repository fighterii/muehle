/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package gui;

import game.Cell;
import game.IPlayerHandler;
import game.Move;
import game.MoveValidator;
import game.Muehle;
import game.Stone;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author Kai
 */
public class JGame extends javax.swing.JFrame implements IPlayerHandler {
    private Muehle muehleGame;
    private ArrayList<int[]> cellXY;
    private JComponent board;
    private JLayeredPane layeredPane;
    private JLabel infoTextPlayer;
    private ArrayList<JStone> jStonesWhite;
    private ArrayList<JStone> jStonesBlack;
    private int STONESIZE;
    
    private boolean stoneClicked;
    private Stone stoneSelected;
    private boolean cellClicked;
    private Cell cellSelected;
    
    private MoveValidator moveValidator;
    public int OFFSET_Y;
    public int OFFSET_X;
    
    /**
     * Creates new form JGame
     * @param muehleGame the Muehle instance which should be displayed
     */
    public JGame(Muehle muehleGame) {
        this.muehleGame = muehleGame;
        ArrayList<Stone> whiteStones = muehleGame.getStones().getStones(1);
        ArrayList<Stone> blackStones = muehleGame.getStones().getStones(2);
        OFFSET_X=150;
        OFFSET_Y=50;
        cellXY = new ArrayList<>();
        setCellXY();
        initComponents();
        
        //initialize Lists of JStone
        this.jStonesWhite = new ArrayList();
        this.jStonesBlack = new ArrayList();
               
        //create and add Stones for white Player on the left of the board
        for(int i=0;i<whiteStones.size();i++){
            JStone jStone = new JStone(whiteStones.get(i));
            jStonesWhite.add(jStone);
        }
        //create and add Stones for white Player on the left of the board

        for(int i=0;i<blackStones.size();i++){
            JStone jStone = new JStone(blackStones.get(i));
            jStonesBlack.add(jStone);
        }
        //creating a layered Pane where we layout the board and all stones
        layeredPane = new JLayeredPaneGame(jStonesWhite,jStonesBlack,this);
        //System.out.println(layeredPane.getComponentCountInLayer(5));
        //creating the layout for the board and add it at the bottom of the layered pane
          
        //InfoText
        infoTextPlayer = new JLabel();
        layeredPane.add(infoTextPlayer,new Integer(10));
        infoTextPlayer.setBounds(400,0,300,50);
        infoTextPlayer.setText("Spiel bereit.");
        this.add(layeredPane);
        
        layeredPane.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                System.out.println("Mouse clicked at: "+mouseX+" "+mouseY);
                //Check if a stone is already selected
                //If not, search for stone at the mousepos
                if(!stoneClicked){
                    stoneSelected = getStoneAtMouse(mouseX,mouseY);
                    System.out.println("Stone at mouseClick: "+stoneSelected);
                    infoTextPlayer.setText("test");
                    if(stoneSelected != null){
                        stoneClicked = true;
                    }
                }else{
                    //If stone is selected, get cell at mouse click
                    cellSelected = getCellAtMouse(mouseX,mouseY);
                    System.out.println("Cell at mouseClick: "+cellSelected);
                    if(cellSelected != null){
                        cellClicked = true;
                    }
                }
                    
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
        System.out.println("GUI initialized.");
        //this.pack();
        //this.revalidate();
        //this.repaint();
    }
    private Stone getStoneAtMouse(int mouseX, int mouseY){
        //initialize List to List of JStones for WhitePlayer
        ArrayList<JStone> jStones = jStonesWhite;
        //in case of secondMove (defeating enemy stone)
        //the method should return an enemy stone
        //therefore the list jStones is set to List of JStones for BlackPlayer
        if(muehleGame.isSecond_move()){
            jStones = jStonesBlack;
        }    
        //search list of JStones and compute the difference between the actual mouse pos 
        //and the pos of the stone
        //if the mouse click was within the boundaries of a stone
        //return this stone
        
        int x = mouseX;
        int y = mouseY;
        for(int i = 0;i<jStones.size();i++){
            int stoneX = jStones.get(i).getX();
            int stoneY = jStones.get(i).getY();
            int maxDelta = jStones.get(i).getBounds().height;
            int deltaX = Math.abs(stoneX-x);
            int deltaY = Math.abs(stoneY-y);
            if((deltaX<maxDelta)&(deltaY<maxDelta)){
                return jStones.get(i).getStone();
            }
        }
        return null;
    }
    private Cell getCellAtMouse(int mouseX,int mouseY){
        int x = mouseX;
        int y = mouseY;
        for(int i=0;i<cellXY.size();i++){
            //get X and Y of examined cell
            int cellX = cellXY.get(i)[0];
            int cellY = cellXY.get(i)[1];
            int maxDelta = 50;
            int deltaX = Math.abs(cellX-x);
            int deltaY = Math.abs(cellY-y);
            System.out.println("deltaX "+deltaX+ " - deltaY "+deltaY);
            if((deltaX<maxDelta)&(deltaY<maxDelta)){
                return muehleGame.getBoard().getListAllCells().get(i);
            }
        }
        return null;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 850));
        setResizable(false);
        getContentPane().setLayout(new java.awt.FlowLayout());

        jMenu1.setText("Datei");

        jMenuItem1.setText("Beenden");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Info");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    @Override
    public Move getMove() {
        display();
        moveValidator = muehleGame.getMoveValidator();
        Move move = null;
        //first check if its a defeating move
        if(muehleGame.isSecond_move()){
            infoTextPlayer.setText("Klicke auf einen feindlichen Stein um ihn zu besiegen");
            //check if the player has clicked a stone
            if(stoneClicked){
                //create move
                move = new Move(stoneSelected,null);
                //check if its a valid move
                if(moveValidator.isValidMove(move)){
                    return move;
                }else{
                    infoTextPlayer.setText("Ungültiger Zug");
                    stoneClicked = false;
                    stoneSelected = null;
                    move = null;
                }
            }
        }else if(muehleGame.getStones().getNotsetStones(1).size()>0){
            //set phase
            stoneSelected = muehleGame.getStones().getNextNotsetStone(1);
            stoneClicked = true;
            infoTextPlayer.setText("Klicke auf ein leeres Feld um Stein zu platzieren");
            //check if the player has clicked a cell
            if(cellClicked){
                //create move
                move = new Move(stoneSelected,cellSelected);
                //check if move is Valid
                if(moveValidator.isValidMove(move)){
                    return move;
                }else{
                    infoTextPlayer.setText("Ungültiger Zug");
                    cellClicked = false;
                    cellSelected = null;
                    move = null;
                }
            }
        }
        return move;
        //TODO
    }
    
    /**
     * is used to set the X and Y pixel coordinates where each cell sits
     *
     */
    private void setCellXY(){
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                Cell cell = muehleGame.getBoard().getAllCells()[i][j];
                if(cell!=null){
                    int[] coord = new int[2]; 
                    int column = cell.getPos().getX();
                    int row = cell.getPos().getY();
                    coord[0] = columnToX(column);
                    coord[1] = rowToY(row);
                    cellXY.add(coord);
                }
            }
        }    
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables

    private int columnToX(int column){
        int x = (column*100)+OFFSET_X;
        return x;
    }
    private int rowToY(int row){
        int y = (row*100)+OFFSET_Y;
        return y;
    }
    
    private void display() {
        ArrayList<JStone> jStones = jStonesWhite;
        int x=0;
        int y=0;
        for(int i=0;i<muehleGame.getStones().getSetStones(1).size();i++){
            Stone stone = muehleGame.getStones().getSetStones(1).get(i);
            for(int j=0;j<muehleGame.getBoard().getListAllCells().size();j++){
                if(muehleGame.getBoard().getListAllCells().get(j)==stone.getPos()){
                    x=cellXY.get(j)[0];
                    y=cellXY.get(j)[1];
                    break;
                }
            }
            for(int j=0;j<jStones.size();j++){
                if(jStones.get(j).getStone()==stone){
                    jStones.get(j).setX(x);
                    jStones.get(j).setY(y);
                    //System.out.println("Stone "+stone+" x:"+x+" y:"+y);
                    break;
                }
            }
        }
        jStones = jStonesBlack;
        for(int i=0;i<muehleGame.getStones().getSetStones(2).size();i++){
            Stone stone = muehleGame.getStones().getSetStones(2).get(i);
            for(int j=0;j<muehleGame.getBoard().getListAllCells().size();j++){
                if(muehleGame.getBoard().getListAllCells().get(j)==stone.getPos()){
                    x=cellXY.get(j)[0];
                    y=cellXY.get(j)[1];
                    break;
                }
            }
            for(int j=0;j<jStones.size();j++){
                if(jStones.get(j).getStone()==stone){
                    jStones.get(j).setX(x);
                    jStones.get(j).setY(y);
                    break;
                }
            }
        }


    }
    public ArrayList<int[]> getCellXY(){
        return cellXY;
    }
}
