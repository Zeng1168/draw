package coms;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

/**
 * 绘图区域
 *
 * 使用自定义监听器，监听各个工具栏动作
 */
public class DrawBroad extends JScrollPane implements MouseMotionListener, MouseListener, TopTools.TopToolListener, LeftTools.LeftToolListener, TopMenu.TopMenuListener{
    // 绘图参数
    Params params;

    // 绘图所需工具
    private BufferedImage image;
    private Graphics g;
    private Graphics2D g2;
    private DrawPanel drawPanel;

    // 内部变量
    private MousePoint oldPoint;
    private BufferedImage oldImage;
    private MousePoint pressedPoint;    // 鼠标刚按下的坐标
    private BufferedImage pressedImage; // 鼠标刚按下的图像
    private Stack recordStack;   // image历史记录
    private Stack cancelStack;   // image撤销记录

    public DrawBroad(Params params){
        this.params = params;

        // 初始化工具
        imageChange(new BufferedImage(params.getGroundSizeX(), params.getGroundSizeY(),BufferedImage.TYPE_INT_RGB));
        g.setColor(params.getBackgroundColor());
        g.fillRect(0,0,image.getWidth(),image.getHeight());
        g.setColor(params.getPenColor());
        this.drawPanel = new DrawPanel();

        // 画板初始化
        this.setBounds(0, 0, params.getGroundSizeX(), params.getGroundSizeY());
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setViewportView(drawPanel);
        this.addMouseMotionListener(this);  // 设置鼠标监听
        this.addMouseListener(this);
        this.setVisible(true);  // 设置可见性

        // 初始化变量
        this.oldPoint = new MousePoint(-1, -1);
        this.recordStack = new Stack<BufferedImage>();
        this.cancelStack = new Stack<BufferedImage>();
    }

    /** 鼠标事件监听  */

    // 监听鼠标拖动
    @Override
    public void mouseDragged(MouseEvent e) {
    	System.out.println(params.getDrawMode());
        switch(params.getDrawMode()) {
            case PEN :{
                if(oldPoint.getX()>0 && oldPoint.getY()>0) {
                    g2.drawLine(oldPoint.getX(), oldPoint.getY(),e.getX(), e.getY());
                }
                oldPoint.setPoint(e.getX(), e.getY());
                this.repaint();
            }break;
            case RUBBER :{
                g.setColor(params.getBackgroundColor());
                Integer radius = params.getRubberSize();
                g.fillOval(e.getX() - (radius/2),e.getY() - (radius/2), radius, radius);
                g.setColor(params.getPenColor());
                this.repaint();
            }break;
            case FILL_RECTANGEL: {
                /**
                 * 例子 绘制实心矩形
                 * 1.在鼠标按下监听中，先把图像和按下时的坐标点，保存在pressedPoint、pressedImage中
                 * 2.鼠标拖动时，把原来保存的图像复制一份，在复制的图像上根据按下的点和当前点绘制形状
                 */
                imageChange(imageCopy(pressedImage));
                Integer X1 , X2, Y1, Y2;
                if(pressedPoint.X > e.getX() ){
                    X1 = e.getX(); X2 = pressedPoint.X;
                }else{
                    X2 = e.getX(); X1 = pressedPoint.X;
                }
                if(pressedPoint.getY() > e.getY()){
                    Y1 = e.getY(); Y2 = pressedPoint.Y;
                }else{
                    Y2 = e.getY(); Y1 = pressedPoint.Y;
                }
                g2.fill3DRect(X1, Y1, X2 - X1, Y2 - Y1, false);
                this.repaint();
            }break;
            case RECTANGEL:{
            	imageChange(imageCopy(pressedImage));
            	Integer X1,X2,Y1,Y2;
            	if(pressedPoint.X>e.getX()) {
            		X1=e.getX(); X2=pressedPoint.X;
            	}else {
            		X1=pressedPoint.X; X2=e.getX();
            	}
            	if(pressedPoint.Y>e.getY()) {
            		Y1=e.getY(); Y2=pressedPoint.Y;
            	}else {
            		Y1=pressedPoint.Y; Y2=e.getY();
            	}
            	g2.setColor(params.getPenColor());
            	g2.setStroke(new BasicStroke(params.getLineStroke()));
            	g2.drawRect(X1, Y1, X2-X1, Y2-Y1);
           
            	this.repaint();
            }break;
            case CIRCLE:{
            	imageChange(imageCopy(pressedImage));
            	Integer X1,X2,Y1,Y2;
            	if(pressedPoint.X>e.getX()) {
            		X1=e.getX(); X2=pressedPoint.X;
            	}else {
            		X1=pressedPoint.X; X2=e.getX();
            	}
            	if(pressedPoint.Y>e.getY()) {
            		Y1=e.getY(); Y2=pressedPoint.Y;
            	}else {
            		Y1=pressedPoint.Y; Y2=e.getY();
            	}
            	g2.setColor(params.getPenColor());
            	g2.setStroke(new BasicStroke(params.getLineStroke()));
            	g2.drawOval(X1, Y1, X2-X1, X2-X1);
            	
            	this.repaint();
            	
            }break;
            case LINE :{
                imageChange(imageCopy(pressedImage));
                g2.setStroke(new BasicStroke(params.getLineStroke()));
                g2.drawLine(pressedPoint.X,pressedPoint.Y,e.getX(),e.getY());
                this.repaint();
            }break;
            case TRIANGLE :{
                imageChange(imageCopy(pressedImage));
                Integer X1 , X2, Y1, Y2;
                g2.setStroke(new BasicStroke(params.getLineStroke()));
                if(pressedPoint.X > e.getX() ){
                    X1 = e.getX(); X2 = pressedPoint.X;
                }else{
                    X2 = e.getX(); X1 = pressedPoint.X;
                }
                if(pressedPoint.getY() > e.getY()){
                    Y1 = e.getY(); Y2 = pressedPoint.Y;
                }else{
                    Y2 = e.getY(); Y1 = pressedPoint.Y;
                }
                g2.drawLine( (X2+X1)/2, Y1,X1, Y2);
                g2.drawLine(X1, Y2, X2, Y2);
                g2.drawLine((X2+X1)/2, Y1, X2, Y2);
                this.repaint();
            }break;
            default : break;
        }
    }

    // 鼠标移动
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    // 鼠标点击
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    // 鼠标按下
    @Override
    public void mousePressed(MouseEvent e) {
        this.pressedPoint = new MousePoint(e.getX(), e.getY());
        this.pressedImage = image;
        if(params.getDrawMode() != DrawMode.MOUSE){
            recordStack.push(imageCopy(image));
        }
    }

    // 鼠标松开
    @Override
    public void mouseReleased(MouseEvent e) {
        this.oldPoint.setPoint(-1, -1);
    }

    // 鼠标进入绘图区域
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    // 鼠标离开绘图区域
    @Override
    public void mouseExited(MouseEvent e) {
    }


    private  void imageChange(BufferedImage image2){
        g = image2.getGraphics();
        g.setColor(params.getPenColor());
        g2 = (Graphics2D)g;
        image = image2;
    }

    private BufferedImage imageCopy(BufferedImage image){
        BufferedImage copy =  new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
        copy.getGraphics().drawImage(image, 0, 0, image.getWidth(), image.getHeight(),null);
        return copy;
    }

    /**  自定义组件事件监听  */

    // 画笔颜色改变
    @Override
    public void onPenColorChanged(Color color) {
        params.setPenColor(color);
        g2.setColor(color);
    }

    // 画布颜色改变
    @Override
    public void onBackColorChanged(Color color) {
        params.setBackgroundColor(color);
        g.setColor(color);
        g.fillRect(0,0,this.image.getWidth(),this.image.getHeight());
        g.setColor(params.getPenColor());
        this.repaint();
    }

    // 画笔粗细改变
    @Override
    public void onLineStrockChanged(Float lineStroke) {
        params.setLineStroke(lineStroke);
        g2.setStroke(new BasicStroke(lineStroke));
    }

    // 画布尺寸改变
    @Override
    public void onSizeChanged(Integer x, Integer y) {
        params.setGroundSize(x, y);
        oldImage = image;
        imageChange(new BufferedImage(x,y,BufferedImage.TYPE_INT_RGB));
        g.setColor(params.getBackgroundColor());
        g.fillRect(0,0,x, y);
        g.setColor(params.getPenColor());
        g.drawImage(oldImage, 0, 0, oldImage.getWidth(), oldImage.getHeight(),null);
        repaint();
    }

    // 鼠绘图模式改变
    @Override
    public void onModeChanged(DrawMode drawMode) {
        params.setDrawMode(drawMode);
        this.setCursor(drawMode.getCursor());   // 改变鼠标形状
    }

    // 存为文件
    @Override
    public void onSaveFile() {

    }

    // 撤销
    @Override
    public void onCancleEdit() {
        if( !recordStack.empty()){
            cancelStack.push(imageCopy(image));    // 把当前图片放入撤销记录栈
            imageChange((BufferedImage) recordStack.pop()); // 从历史记录栈取出
            repaint();
        }
    }

    // 重做
    @Override
    public void onRedoEdit() {
        if( !cancelStack.empty()){
            recordStack.push(imageCopy(image));
            imageChange((BufferedImage) cancelStack.pop());
            repaint();
        }
    }
    //清空
    @Override
    public void onClearEdit() {
    	params.setBackgroundColor(Color.WHITE);
        g.setColor(Color.WHITE);
        g.fillRect(0,0,this.image.getWidth(),this.image.getHeight());
        g.setColor(params.getPenColor());
        this.repaint();
    }

    // 存为文件
    public void saveToFile(String fileName,String fileSrc) {
        try {
            ImageIO.write(this.image, "jpeg", new File(fileSrc+fileName+".jpeg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    // 画图容器 内部类
    class DrawPanel extends JPanel {
        // 将图形绘制到组件上
        @Override
        public void paint(Graphics g) {
            g.drawImage(image, 0, 0,image.getWidth(),image.getHeight(),null);
        }
    }

    // 鼠标指针 内部类
    class MousePoint {
        private int X;
        private int Y;

        public MousePoint(int x, int y) {
            this.setPoint(x, y);
        }

        public void setPoint(int x, int y) {
            X = x;
            Y = y;
        }

        public int getX() {
            return X;
        }

        public int getY() {
            return Y;
        }
    }

}
