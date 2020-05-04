package view;

import entity.Point;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.RotationInterpolator;
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
public class DrawBroadView extends JScrollPane implements MouseMotionListener, MouseListener{
    private DrawBroadListener listener;
    private BufferedImage image;
    BranchGroup sceneBranchGroup = null;
    RotationInterpolator rotator=null;


    /**
     * an offscreen Canvas3D that is used to perform screen captures
     */
    private Canvas3D offScreenCanvas3D = null;

    /**
     * the image that is attached to the offscreen Canvas3D and contains the
     * results of screen captures
     */
    private ImageComponent2D imageComponent = null;

    /**
     * the width of the offscreen Canvas3D
     */
    private static final int offScreenWidth = 640;

    /**
     * the height of the offscreen Canvas3D
     */
    private static final int offScreenHeight = 480;


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

    // 画图容器 内部类
    private class DrawPanel extends JPanel {
        // 将图形绘制到组件上
        @Override
        public void paint(Graphics g) {
            g.drawImage(image, 0, 0,image.getWidth(),image.getHeight(),null);
        }
    }
}
