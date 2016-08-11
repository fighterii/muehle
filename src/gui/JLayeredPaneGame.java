/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JLayeredPane;

/**
 *
 * @author Kai
 */
public class JLayeredPaneGame extends JLayeredPane{
    private ArrayList<JStone> jStonesWhite;
    private ArrayList<JStone> jStonesBlack;
    int STONESIZE;
    int OFFSET;
    private final JGame gameGui;
    public JLayeredPaneGame(ArrayList<JStone> jStonesWhite,ArrayList<JStone> jStonesBlack,JGame gameGui){
        this.jStonesWhite = jStonesWhite;
        this.jStonesBlack = jStonesBlack;
        this.gameGui = gameGui;
        this.setPreferredSize(new Dimension(900, 900));
        //OFFSET because size of layeredPaneGame is 900x900, size of board 800x800
        this.OFFSET = 50;
        this.setOpaque(true);
        this.setLayout(null);
        STONESIZE = 52;
        int globalx = 70;
        int globaly = 50;
        //create and add Stones for white Player on the left of the board
        for(int i=0;i<jStonesWhite.size();i++){
            //set X and Y coord of stones
            jStonesWhite.get(i).setX(globalx);
            jStonesWhite.get(i).setY(globaly);
            globaly = globaly + STONESIZE/2;
            this.add(jStonesWhite.get(i), new Integer(3));
        } 
        //create and add Stones for white Player on the left of the board if not already placed
        globalx = 770;
        globaly = 50;
        for(int i=0;i<jStonesBlack.size();i++){
            //set X and Y coord of stones
            jStonesBlack.get(i).setX(globalx);
            jStonesBlack.get(i).setY(globaly);
            System.out.println(globalx + " - " + globaly);
            globaly = globaly + STONESIZE/2;
            this.add(jStonesBlack.get(i), new Integer(5));
        }
        JBoard board = new JBoard(gameGui.getCellXY());
        board.setBounds(50, 0, 800, 800);       
        this.add(board,new Integer(0));
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //paint white stones
        //.jStonesWhite = gameGui.getJStonesWhite();
        //this.jStonesBlack = gameGui.getJStonesBlack();
        for(int i=0;i<jStonesWhite.size();i++){
            int X = jStonesWhite.get(i).getX()+OFFSET-STONESIZE/2; 
            int Y = jStonesWhite.get(i).getY()-STONESIZE/2;
            System.out.println("Stone on: "+X+" - "+Y);
            jStonesWhite.get(i).setBounds(X, Y, STONESIZE, STONESIZE);         
        }
        //paint black stones
        for(int i=0;i<jStonesBlack.size();i++){
            int X = jStonesBlack.get(i).getX()+OFFSET-STONESIZE/2;
            int Y = jStonesBlack.get(i).getY()-STONESIZE/2;
            jStonesBlack.get(i).setBounds(X, Y, STONESIZE, STONESIZE);
        }
    }
}
