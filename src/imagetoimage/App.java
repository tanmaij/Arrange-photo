/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagetoimage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class App extends JPanel{
    public BufferedImage img;
    BufferedImage[] srcPix;
    BufferedImage finalImage;
    int pixel=6;
    public  App() {
        
            super.setSize(700, 350);
            super.setVisible(true);
            super.setBackground(Color.red);
            BufferedImage src = null;
            srcPix=new BufferedImage[129];
            try {
              //  src = ImageIO.read(getClass().getResourceAsStream("/Source/hinhanh.jpg"));
                src = ImageIO.read(getClass().getResourceAsStream("/imagetoimage/Love/11.jpg"));
            } catch (IOException ex) {
                System.out.println("imagetoimage.App.<init>()");
            }
            
            for (int i = 0; i < 129; i++) {
                
                try{
                    try{
                        srcPix[i]=ImageIO.read(getClass().getResourceAsStream("/imagetoimage/Love/"+i+".jpg"));
                    }
                    catch(IOException ex){
                        
                        
                    }}catch(IllegalArgumentException io){
                        
                        try {
                            srcPix[i]=ImageIO.read(getClass().getResourceAsStream("/imagetoimage/Love/"+i+".jpeg"));
                        } catch (IOException e) {
                            System.out.println("imagetoimage.App.<init>()");
                        }
                    }
            }
   
      /*      for (int i = 0; i < srcPix.length; i++) {
                    File f = new File("D://Love2/"+i+".jpg");
                    try {
                    ImageIO.write(srcPix[i], "JPEG", f);
                    } catch (IOException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
                } */
            img=new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
            finalImage=new BufferedImage(src.getWidth()*4, src.getHeight()*4, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d=(Graphics2D) img.getGraphics();
            g2d.drawImage(src, 0, 0, null);
            byte[][] pixels=new byte[src.getWidth()][src.getHeight()];
            for( int i = 0; i < src.getWidth()/pixel; i++ ){
                for( int j = 0; j < src.getHeight()/pixel; j++ ){
                    int s=0;
                    for (int k = i*pixel; k < i*pixel+pixel; k++) {
                        for (int l = j*pixel; l < j*pixel+pixel; l++) {
                            s+=(byte)img.getRGB( k, l );
                        }
                        
                    }
                    
                    byte AVG=(byte) (s/(pixel*pixel));
                    
                    for (int k = i*pixel; k < i*pixel+pixel; k++) {
                        for (int l = j*pixel; l < j*pixel+pixel; l++) {
                            pixels[k][l]=AVG;
                        }
                        
                    }
                }}                    
            Graphics2D g9=(Graphics2D) finalImage.getGraphics();
            for( int i = 0; i < src.getWidth()/pixel; i++ ){
                
                for( int j = 0; j < src.getHeight()/pixel; j++ )
                {
                    g9.drawImage(srcPix[(pixels[i*pixel][j*pixel]+128)/2], i*pixel*4, j*pixel*4,pixel*4,pixel*4, null);
                   
                  //  g9.setColor(new Color(pixels[i*pixel][j*pixel]));
                  //  g9.fillRect(i*pixel*4, j*pixel*4,pixel*4,pixel*4);
                }

            }
          
            File f = new File("D://MyLoveeeeeeeeeeeeeeeeeee.jpg");
        try {
            ImageIO.write(finalImage, "JPEG", f);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
                
           
        
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2d=(Graphics2D) g;
        g2d.drawImage(img, 0, 0, null);
        g2d.drawImage(finalImage, finalImage.getWidth(), 0, null);
    }
    private boolean equalimg(BufferedImage img1,BufferedImage img2){
        
        BufferedImage imgequal1=new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        BufferedImage imgequal2=new BufferedImage(img2.getWidth(), img2.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d=(Graphics2D) imgequal1.getGraphics();
        g2d.drawImage(img1, 0, 0, null);
        g2d=(Graphics2D) imgequal2.getGraphics();
        g2d.drawImage(img2, 0, 0, null);
        int s1=0,s2=0;
        for (int i = 0; i < imgequal1.getWidth(); i++) {
            for (int j = 0; j < imgequal1.getHeight(); j++) {
                s1+=(byte)imgequal1.getRGB(i, j);
            }
        }
        for (int i = 0; i < imgequal2.getWidth(); i++) {
            for (int j = 0; j < imgequal2.getHeight(); j++) {
                s2+=(byte)imgequal2.getRGB(i, j);
            }
        }
        byte AVG1=(byte) (s1/(imgequal1.getHeight()*imgequal1.getWidth()));
        byte AVG2=(byte) (s2/(imgequal2.getHeight()*imgequal2.getWidth()));
        return AVG1>AVG2;
    }
    private void Sort(){
        for (int i = 0; i < srcPix.length; i++) {
            System.out.println(i);
            for (int j = i+1; j < srcPix.length; j++) {
                if(equalimg(srcPix[i], srcPix[j]))
                {
                    BufferedImage temp;
                    temp=srcPix[i];
                    srcPix[i]=srcPix[j];
                    srcPix[j]=temp;
                }
            }
        }
    }
}
