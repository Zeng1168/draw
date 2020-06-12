package view.drawPlatform;

import entity.module.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class DrawBroadPanel extends JScrollPane implements MouseMotionListener, MouseListener {

    private DrawBroadListener listener;
    private BufferedImage image;


    public DrawBroadPanel(BufferedImage image){
        this.image = image;

        // 画板初始化
        this.setBounds(0, 0, image.getWidth(), image.getHeight());
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


    class ImagePrinter implements Printable, ImageObserver {
        BufferedImage bImage;

        public int print(Graphics g, PageFormat pf, int pi) throws PrinterException {

            if (pi >= 1) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) g;
            //g2d.translate(pf.getImageableX(), pf.getImageableY());
            AffineTransform t2d = new AffineTransform();
            t2d.translate(pf.getImageableX(), pf.getImageableY());
            double xscale = pf.getImageableWidth() / (double) bImage.getWidth();
            double yscale = pf.getImageableHeight() / (double) bImage.getHeight();
            double scale = Math.min(xscale, yscale);
            t2d.scale(scale, scale);
            try {
                g2d.drawImage(bImage, t2d, this);
            } catch (Exception ex) {
                ex.printStackTrace();
                return Printable.NO_SUCH_PAGE;
            }
            return Printable.PAGE_EXISTS;
        }

        void print() {
            PrinterJob printJob = PrinterJob.getPrinterJob();
            PageFormat pageFormat = printJob.defaultPage();
            pageFormat.setOrientation(PageFormat.LANDSCAPE);
            pageFormat = printJob.validatePage(pageFormat);
            printJob.setPrintable(this, pageFormat);
            if (printJob.printDialog()) {
                try {
                    printJob.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        }

        public boolean imageUpdate(Image img, int infoflags, int x, int y,
                                   int width, int height) {
            return false;
        }

        ImagePrinter(BufferedImage bImage) {
            this.bImage = bImage;
        }
    }

        @Override
        public void paint(Graphics g) {
            g.drawImage(image, 0, 0,image.getWidth(),image.getHeight(),null);
        }


}
