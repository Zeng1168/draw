package view.drawMath;

import utils.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MathDrawBroad extends JScrollPane {
    BufferedImage image;


   public MathDrawBroad(){
       image = ImageUtil.createBlankImage(680, 460);


       this.setBounds(0, 0,680,460);
       this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       this.setBackground(Color.white);
       this.setVisible(true);

    }

    public void paintImage(BufferedImage image){
        this.image = image;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0,image.getWidth(),image.getHeight(),null);
    }

    public void castTo3d(){

    }

    public void castTo2d(){

    }
}
