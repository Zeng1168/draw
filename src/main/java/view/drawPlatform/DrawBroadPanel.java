package view.drawPlatform;

import entity.module.Point;
import utils.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DrawBroadPanel extends JScrollPane implements MouseMotionListener, MouseListener {
    private DrawBroadListener listener;
    private BufferedImage image;


    public DrawBroadPanel(){

        // 画板初始化
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        this.setViewportView(new DrawPanel());

//            setSize(400, 400);

        this.addMouseMotionListener(this);  // 设置鼠标监听
        this.addMouseListener(this);
        this.setVisible(true);  // 设置可见性

    }




    public void paintImage(BufferedImage image){
        this.image = image;
        this.repaint();
    }


    /** 鼠标事件监听  */
    // 监听鼠标拖动
    @Override
    public void mouseDragged(MouseEvent e) {
        if(listener != null){
            listener.mouseDragged(new Point(e.getX(), e.getY()));
        }
    }

    // 鼠标移动
    @Override
    public void mouseMoved(MouseEvent e) {
        if(listener != null){
            listener.mouseMoved(new Point(e.getX(), e.getY()));
        }
    }

    // 鼠标点击
    @Override
    public void mouseClicked(MouseEvent e) {
        if(listener != null){
            listener.mouseClicked(new Point(e.getX(), e.getY()));
        }
    }

    // 鼠标按下
    @Override
    public void mousePressed(MouseEvent e) {
        if(listener != null){
            listener.mousePressed(new Point(e.getX(), e.getY()));
        }
    }

    // 鼠标松开
    @Override
    public void mouseReleased(MouseEvent e) {
        if(listener != null){
            listener.mouseReleased(new Point(e.getX(), e.getY()));
        }
    }

    // 鼠标进入绘图区域
    @Override
    public void mouseEntered(MouseEvent e) {
        if(listener != null){
            listener.mouseEntered(new Point(e.getX(), e.getY()));
        }
    }

    // 鼠标离开绘图区域
    @Override
    public void mouseExited(MouseEvent e) {
        if(listener != null){
            listener.mouseExited(new Point(e.getX(), e.getY()));
        }
    }

    // 设置监听
    public void setDrawBroadListener(DrawBroadListener listener){
        this.listener = listener;
    }

    // 自定义监听器
    public interface DrawBroadListener{
        void mouseDragged(Point p);
        void mouseMoved(Point p);
        void mouseClicked(Point p);
        void mousePressed(Point p);
        void mouseReleased(Point p);
        void mouseEntered(Point p);
        void mouseExited(Point p);
    }


    @Override
    public void paint(Graphics g) {
        this.setSize(image.getWidth(), image.getHeight());
        g.drawImage(image, 0, 0,image.getWidth(),image.getHeight(),null);
    }


}
