/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package gui;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Kai
 */
public class JBoard extends JComponent{
    private ArrayList<int[]> cellXY;
    
    public JBoard(ArrayList<int[]> cellXY){
        this.cellXY = cellXY;
        setPreferredSize(new Dimension(800, 800));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Drawing Board...");
        int WIDTH = 20;
        int HEIGHT = 20;
        int STROKE = 5;
        int CENTER_COLUMN = cellXY.get(1)[0]; //cell1 is at center column
        int CENTER_ROW = cellXY.get(3)[1]; //cell3 is at center row
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(STROKE));
        for(int i=0;i<cellXY.size();i++){
            g2.drawOval(cellXY.get(i)[0]-(WIDTH/2), cellXY.get(i)[1]-(HEIGHT/2), WIDTH, HEIGHT);
        }

        //HorizontalLines
        int OFFSET_X = WIDTH/2;
        int OFFSET_Y = 0;
                

        for(int i=0;i<cellXY.size();i++){
            int[] source_cell = cellXY.get(i);
            for(int j=i+1;j<cellXY.size();j++){
                int[] target_cell = cellXY.get(j);
                if(source_cell[1]==target_cell[1]){
                    //exclude center row
                    if(source_cell[1]!=CENTER_ROW){
                        drawConnectionLine(g2,i,j,OFFSET_X,OFFSET_Y);
                        break; 
                    }
                }
            }
        }
        
        //Line from 3 - 11
        int source = 3;
        int target = 11;
        drawConnectionLine(g2,source,target,OFFSET_X,OFFSET_Y);
        //Line from 11 - 19
        source = 11;
        target = 19;
        drawConnectionLine(g2,source,target,OFFSET_X,OFFSET_Y);
        //Line from 20 - 11
        source = 20;
        target = 12;
        drawConnectionLine(g2,source,target,OFFSET_X,OFFSET_Y);
        //Line from 12 - 4
        source = 12;
        target = 4;
        drawConnectionLine(g2,source,target,OFFSET_X,OFFSET_Y);
        
        //VerticalLines
        OFFSET_X = 0;
        OFFSET_Y = HEIGHT/2;
        
        for(int i=0;i<cellXY.size();i++){
            int[] source_cell = cellXY.get(i);
            for(int j=i+1;j<cellXY.size();j++){
                int[] target_cell = cellXY.get(j);
                if(source_cell[0]==target_cell[0]){
                    //exclude center column
                    if(source_cell[0]!=CENTER_COLUMN){
                        drawConnectionLine(g2,i,j,OFFSET_X,OFFSET_Y);
                        break; 
                    }
                }
            }
        }
        
    }

    private void drawConnectionLine(Graphics2D g2, int source, int target,int OFFSET_X,int OFFSET_Y) {
        g2.drawLine(cellXY.get(source)[0]+OFFSET_X, cellXY.get(source)[1]+OFFSET_Y, cellXY.get(target)[0]-OFFSET_X, cellXY.get(target)[1]-OFFSET_Y);
    }
}
