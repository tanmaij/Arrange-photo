/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagetoimage;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class Screen  extends JFrame{
    public static final int WIDTH_SCREEN=720;
    public static final int HEIGHT_SCREEN=420;
    public static final int SCALE=2;
    
    
    public App app;
    public  Screen(){
     
        app=new App();
        this.add(app);
       
        super.setSize(WIDTH_SCREEN*SCALE,HEIGHT_SCREEN*SCALE);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addnotify();
        this.setPositionCenter();
        super.setVisible(true);
       
        super.setLayout(new CardLayout());
       
        
        
        
    }
    private void addnotify(){
        super.addNotify();
    }
    private void setPositionCenter()
    {
        Dimension SIZE=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(SIZE.width)/2-(WIDTH_SCREEN*SCALE)/2;
        int y=(SIZE.height)/2-(HEIGHT_SCREEN*SCALE)/2;
        super.setLocation(x, y);
    }
    }

