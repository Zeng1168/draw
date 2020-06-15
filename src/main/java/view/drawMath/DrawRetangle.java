package view.drawMath;

import utils.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawRetangle extends JPanel {
    int sizeX = 600, sizeY = 440;   // 坐标系的大小
    int margin = 20;    // 边距

    String title = "三角形绘制";

    JTextField x1,y1,x2,y2,x3,y3;   // 三点
    JTextField lengthAB, lengthBC, lengthCA;    // 三条线段长度
    JButton startDrawBtn1, startDrawBtn2;
    ImagePanel imagePanel;
    BufferedImage image;

    public DrawRetangle() {
        Box topBox = Box.createVerticalBox();


        // 标题
        JLabel label = new JLabel(title);
        topBox.add(Box.createVerticalStrut(10));
        topBox.add(label);
        topBox.add(Box.createVerticalStrut(10));

        x1 = new JTextField("5");
        x2 = new JTextField("10");
        x3 = new JTextField("60");
        y1 = new JTextField("20");
        y2 = new JTextField("20");
        y3 = new JTextField("70");
        lengthAB = new JTextField();
        lengthBC = new JTextField();
        lengthCA = new JTextField();
        startDrawBtn1 = new JButton("绘制");
        startDrawBtn2 = new JButton("绘制");

        //  输入三点绘制
        Box paramBox1 = Box.createHorizontalBox();
        paramBox1.add(Box.createHorizontalStrut(20));

        Box box1A=Box.createHorizontalBox();
        box1A.add(new JLabel("A点 X:"));
        box1A.add(x1);
        box1A.add(new JLabel("Y:"));
        box1A.add(y1);
        box1A.add(Box.createHorizontalStrut(10));
        paramBox1.add(box1A);

        Box box2A=Box.createHorizontalBox();
        box2A.add(new JLabel("B点 X:"));
        box2A.add(x2);
        box2A.add(new JLabel("Y:"));
        box2A.add(y2);
        box2A.add(Box.createHorizontalStrut(10));
        paramBox1.add(box2A);

        Box box3A=Box.createHorizontalBox();
        box3A.add(new JLabel("C点 X:"));
        box3A.add(x3);
        box3A.add(new JLabel("Y:"));
        box3A.add(y3);
        box3A.add(Box.createHorizontalStrut(10));
        paramBox1.add(box3A);

        paramBox1.add(startDrawBtn1);



        // 输入三条线段长度绘制


        topBox.add(paramBox1);


        topBox.add(Box.createVerticalStrut(10));


        imagePanel = new ImagePanel();
        // 坐标系绘制
        drawShape(5,10,60,20,20,70);

        this.setLayout(new BorderLayout());
        this.add(topBox, BorderLayout.NORTH);
        this.add(imagePanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    /** 坐标轴  */
    public void coordinateDraw(Graphics2D g2){
        // X坐标轴
        g2.drawLine(margin, sizeY-margin, sizeX-margin, sizeY-margin);  // X轴线
        g2.drawLine(sizeX-margin, sizeY-margin, sizeX-margin-8, sizeY-margin-6);
        g2.drawLine(sizeX-margin, sizeY-margin, sizeX-margin-8, sizeY-margin+6);
        g2.drawString("x", sizeX-20,sizeY-5);

        // Y坐标轴
        g2.drawLine(margin, margin, margin, sizeY-margin);  // Y轴线
        g2.drawLine(margin, margin, margin-6, margin+8);
        g2.drawLine(margin, margin, margin+6, margin+8);
        g2.drawString("y", 10,20);

        // 0点
        g2.drawString("0", 10,sizeY-5);

        imagePanel.repaint();
    }

    /** 画辅助线  */
    private void drawAuxiliaryLine(Graphics2D g2, Color color, String str, int x, int y, int mS){
        g2.setColor(color);
        g2.drawString(str, x*mS+margin-10, sizeY-y*mS+margin-10);
        g2.drawLine(x*mS+margin, sizeY-y*mS+margin,x*mS+margin, sizeY-margin );
        g2.drawString(String.valueOf(x), x*mS+margin-3, sizeY-margin+15);
        g2.drawLine(x*mS+margin, sizeY-y*mS+margin,margin, sizeY-y*mS+margin );
        g2.drawString(String.valueOf(y), margin-15, sizeY-y*mS+margin+3);
    }

    /** 画图形 */
    public void drawShape(int x1, int y1, int x2, int y2, int x3, int y3){
        imagePanel.setSize(sizeX, sizeY);
        image = ImageUtil.createBlankImage(sizeX, sizeY);
        Graphics2D g2 = image.createGraphics();
        //消除文字锯齿
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //消除画图锯齿
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);

        // 计算刻度倍数
        int maxScale, mS;
        // 找出x轴、y轴最大坐标
        maxScale = x1;
        maxScale = maxScale>x2?maxScale:x2;
        maxScale = maxScale>x3?maxScale:x3;
        maxScale = maxScale>y1?maxScale:y1;
        maxScale = maxScale>y2?maxScale:y2;
        maxScale = maxScale>y3?maxScale:y3;
        mS = (sizeY-margin*3)/(maxScale);

        // 画坐标轴
        coordinateDraw(g2);

        // 画三边
        g2.drawLine(x1*mS+margin, sizeY-y1*mS+margin, x2*mS+margin, sizeY-y2*mS+margin);
        g2.drawLine(x2*mS+margin, sizeY-y2*mS+margin, x3*mS+margin, sizeY-y3*mS+margin);
        g2.drawLine(x3*mS+margin, sizeY-y3*mS+margin, x1*mS+margin, sizeY-y1*mS+margin);

        // 画三点辅助线
        drawAuxiliaryLine(g2, Color.GREEN, "A", x1, y1, mS);
        drawAuxiliaryLine(g2, Color.BLUE, "B", x2, y2, mS);
        drawAuxiliaryLine(g2, Color.RED, "B", x3, y3, mS);

        imagePanel.repaint();
    }

    class ImagePanel extends JPanel{
        @Override
        public void paint(Graphics g) {
            this.setSize(image.getWidth(), image.getHeight());
            g.drawImage(image, 0, 0,image.getWidth(),image.getHeight(),null);
        }
    }
}
