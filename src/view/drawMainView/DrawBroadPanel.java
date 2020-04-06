package view.drawMainView;

import entity.MousePoint;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * 绘图区域
 *
 * 使用自定义监听器，监听各个工具栏动作
 */
public class DrawBroadPanel extends JScrollPane implements MouseMotionListener, MouseListener{
    private DrawBroadListener listener;
    private BufferedImage image;

    public DrawBroadPanel(BufferedImage image){
        this.image = image;

        // 画板初始化
        this.setBounds(0, 0, image.getWidth(), image.getHeight());
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setViewportView(new DrawPanel());
        this.addMouseMotionListener(this);  // 设置鼠标监听
        this.addMouseListener(this);
        this.setVisible(true);  // 设置可见性
    }

    // 重新绘制图像
    public void paintImage(BufferedImage image){
        this.image = image;
        this.repaint();
    }

    /** 鼠标事件监听  */
    // 监听鼠标拖动
    @Override
    public void mouseDragged(MouseEvent e) {
        if(listener != null){
            listener.mouseDragged(new MousePoint(e.getX(), e.getY()));
        }
    }

    // 鼠标移动
    @Override
    public void mouseMoved(MouseEvent e) {
        if(listener != null){
            listener.mouseMoved(new MousePoint(e.getX(), e.getY()));
        }
    }

    // 鼠标点击
    @Override
    public void mouseClicked(MouseEvent e) {
        if(listener != null){
            listener.mouseClicked(new MousePoint(e.getX(), e.getY()));
        }
    }

    // 鼠标按下
    @Override
    public void mousePressed(MouseEvent e) {
        if(listener != null){
            listener.mousePressed(new MousePoint(e.getX(), e.getY()));
        }
    }

    // 鼠标松开
    @Override
    public void mouseReleased(MouseEvent e) {
        if(listener != null){
            listener.mouseReleased(new MousePoint(e.getX(), e.getY()));
        }
    }

    // 鼠标进入绘图区域
    @Override
    public void mouseEntered(MouseEvent e) {
        if(listener != null){
            listener.mouseEntered(new MousePoint(e.getX(), e.getY()));
        }
    }

    // 鼠标离开绘图区域
    @Override
    public void mouseExited(MouseEvent e) {
        if(listener != null){
            listener.mouseExited(new MousePoint(e.getX(), e.getY()));
        }
    }

    // 设置监听
    public void setDrawBroadListener(DrawBroadListener listener){
        this.listener = listener;
    }

    // 自定义监听器
    public interface DrawBroadListener{
        void mouseDragged(MousePoint p);
        void mouseMoved(MousePoint p);
        void mouseClicked(MousePoint p);
        void mousePressed(MousePoint p);
        void mouseReleased(MousePoint p);
        void mouseEntered(MousePoint p);
        void mouseExited(MousePoint p);
    }

    // 画图容器 内部类
    private class DrawPanel extends JPanel {
        // 将图形绘制到组件上
        @Override
        public void paint(Graphics g) {
            g.drawImage(image, 0, 0,image.getWidth(),image.getHeight(),null);
        }
    }
}
