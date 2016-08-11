/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package gui;

import game.Stone;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author Kai
 */
public class JStone extends JComponent{
    private Stone stone;
    private Color color;
    private int SIZE;
    private int x;
    private int y;
    public JStone(Stone stone){
        this.stone = stone;
        this.SIZE = 50;
        this.x=0;
        this.y=0;
        if(stone.getColor()==1){
            color = Color.WHITE;
        }else{
            color = Color.BLACK;
        }
        setPreferredSize(new Dimension(800, 800));
        //System.out.println("Stone");
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //System.out.println("PAINTING!!");
        g.setColor(color);
        g.fillOval(0, 0, SIZE,SIZE);
        g.setColor(Color.GRAY);
        g.drawOval(0, 0, SIZE, SIZE);
    }
    
    public Stone getStone(){
        return stone;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
}
