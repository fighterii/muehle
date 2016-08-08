/*
 * Copyright 2016 by Kai Braunias
 * 
 */
package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author Kai
 */
public class JBackground extends JComponent{
    private Image image;
    public JBackground() throws IOException{
        URL urlBackgroundImg = getClass().getResource("/img/bg.jpg");
	this.image = new ImageIcon(urlBackgroundImg).getImage();
        //File input = new File("img/bg.jpg");
        //this.image = ImageIO.read(input);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
