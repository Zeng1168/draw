package view.drawMath;

import controller.drawMath.RectangleController;
import controller.drawMath.RoseController;
import entity.ShapeRectangle;
import entity.ShapeRose;
import utils.AlertUtil;
import utils.DataCheck;
import utils.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawRose extends JPanel implements IDraw{
    private RoseController roseController;
    private ImagePanel imagePanel;  // 绘图区容器
    private BufferedImage image;    // 绘制的图片
   private BufferedImage bufferedImage;

    // 绘图区大小、边距
    private int sizeX = 560, sizeY = 480;   // 坐标系的大小
    private int margin = 20;    // 边距
    // 标题
    private String title = "玫瑰花绘制";
    int	h = -250;
    int x, y, z, zBufferIndex;

    // 参数输入区
    private JTextField size_rose; // 玫瑰花的大小
    private JButton startDrawBtn;   // 绘制按钮
    private  Integer s;
    // 信息区
    private JLabel name;    // 图形名称
    private JLabel size;    // 图形大小
    public DrawRose() {
        /* 初始化阶段     */
        this.initComponent();   // 初始化窗口组件
        this.setListener(); // 为按钮设置监听
        /* 初始化完成    */

        // 与控制层和监听器关联
        roseController = new RoseController(this);
        this.setVisible(true);
    }

    /**
     * 初始化组件
     */
    private void initComponent() {
        /* 111111111111111   以下为顶部标题、输入框组件   111111111111111 */
        // 创建各对象
        size_rose = new JTextField("5");

        startDrawBtn = new JButton("绘制");

        /**   第一行输入参数组   输入玫瑰花的大小绘制  */
        Box paramBox = Box.createHorizontalBox();
        paramBox.add(Box.createHorizontalStrut(20));   // 左边距
        // 输入A点
        Box boxA = Box.createHorizontalBox();
        boxA.add(new JLabel("玫瑰花的大小:"));
        boxA.add(size_rose);
        boxA.add(Box.createHorizontalStrut(10));
        paramBox.add(boxA);

        // 开始绘制按钮
        paramBox.add(startDrawBtn);

        paramBox.add(Box.createHorizontalStrut(20));   // 右边距

        /**  主容器 */
        Box topBox = Box.createVerticalBox();
        topBox.add(Box.createVerticalStrut(10));    // 上边距
        topBox.add(new JLabel(title));  // 放入标题
        topBox.add(Box.createVerticalStrut(10));    // 加一个间距
        topBox.add(paramBox);  // 放入第一行输入框组

        /* 2222222222   以下为中部绘图区组件  222222222 */
        image = ImageUtil.createBlankImage(sizeX, sizeY);
        imagePanel = new ImagePanel();
        /* 22222222222222222222222222222222 */

        /* 3333333333   以下为右侧信息区组件   33333333333333 */
        // 创建各对象
        name = new JLabel();    // 图形名称
        size = new JLabel();    // 玫瑰花的大小
        Box rightInfoBox = Box.createHorizontalBox();
        Box infoBox = Box.createVerticalBox();

        int marginInfo = 10;
        infoBox.add(name);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(size);

        rightInfoBox.add(infoBox);
        rightInfoBox.add(Box.createHorizontalStrut(30));
        /* 3333333333333333333333333333333333 */
        this.setLayout(new BorderLayout());
        this.add(topBox, BorderLayout.NORTH);
        this.add(imagePanel, BorderLayout.CENTER);
        this.add(rightInfoBox, BorderLayout.EAST);

    }

    /** 设置监听器 */
    private void setListener() {
        // 三点绘制
        startDrawBtn.addActionListener(e -> {
            // 获取输入参数
            String strSize = size_rose.getText();

            if ( !DataCheck.isNumber(strSize)) AlertUtil.warningDialog("请正确输入玫瑰花大小！");
            else if (Integer.parseInt(strSize)>500) AlertUtil.warningDialog("玫瑰花的大小超过500！");
            else if ( Integer.parseInt(strSize)<300) AlertUtil.warningDialog("玫瑰花的大小低于300！");
            else {
                roseController.onDraw(Integer.valueOf(strSize));
            }
        });

    }


    class Dot {
        private double x;
        private double y;
        private double z;
        private double r;	//rgb
        private double g;	//

        public double getX() {
            return x;
        }
        public void setX(double x) {
            this.x = x;
        }
        public double getY() {
            return y;
        }
        public void setY(double y) {
            this.y = y;
        }
        public double getZ() {
            return z;
        }
        public void setZ(double z) {
            this.z = z;
        }
        public double getR() {
            return r;
        }
        public void setR(double r) {
            this.r = r;
        }
        public double getG() {
            return g;
        }
        public void setG(double g) {
            this.g = g;
        }

    }
    Dot dot=new Dot();
    public boolean calc(double a, double b, double c, Dot d)
    {
        double j=0, n, o, w, z;
        boolean J=false;
        if(c > 60)	 //花柄
        {
            d.setX(Math.sin(a * 7) * (13 + 5 / (0.2 + Math.pow(b * 4, 4))) - Math.sin(b) * 50);
            d.setY( b * s + 50);
            d.setZ( 625 + Math.cos(a * 7) * (13 + 5 / (0.2 + Math.pow(b * 4, 4))) + b * 400);
            d.setR( a * 1 - b / 2);
            d.setG(a);
            return true;
        }

        double A = a * 2 - 1;
        double B = b * 2 - 1;
        if(A * A + B * B < 1)
        {
            if(c > 37)	 //叶
            {
                if((int)c%2==1)
                {  J=true;j=1;}
                n = J ? 6 : 4;
                o = 0.5 / (a + 0.01) + Math.cos(b * 125) * 3 - a * 300;
                w = b * h;

                d.setX( o * Math.cos(n) + w * Math.sin(n) + j * 610 - 390);
                d.setY( o *Math.sin(n) - w * Math.cos(n) + 550 - j * 350);
                d.setZ( 1180 + Math.cos(B + A) * 99 - j * 300);
                d.setR( 0.4 - a * 0.1 + Math.pow(1 - B * B, -h * 6) * 0.15 - a * b * 0.4 + Math.cos(a + b) / 5 + Math.pow(Math.cos((o * (a + 1) + (B > 0 ? w : -w)) / 25), 30) * 0.1 * (1 - B * B));
                d.setG(o / 1000 + 0.7 - o * w * 0.000003);
                return true;
            }
            if(c > 32)	 // 花萼
            {
                c = c * 1.16 - 0.15;
                o = a * 45 - 20;
                w = b * b * h;
                z = o * Math.sin(c) + w * Math.cos(c) + 620;

                d.setX(o * Math.cos(c) - w * Math.sin(c));
                d.setY( 28 + Math.cos(B * 0.5) * 99 - b * b * b * 60 - z / 2 - h);
                d.setZ(z);
                d.setR( (b * b * 0.3 + Math.pow((1 - (A * A)), 7) * 0.15 + 0.3) * b);
                d.setG( b * 0.7);
                return true;
            }

            // 花
            o = A * (2 - b) * (80 - c * 2);
            w = 99 - Math.cos(A) * 120 - Math.cos(b) * (-h - c * 4.9) + Math.cos(Math.pow(1 - b, 7)) * 50 + c * 2;
            z = o * Math.sin(c) + w * Math.cos(c) + 700;

            d.x = o * Math.cos(c) - w * Math.sin(c);
            d.y = B * 99 - Math.cos(Math.pow(b, 7)) * 50 - c / 3 - z / 1.35 + 450;
            d.z = z;
            d.r = (1 - b / 1.2) * 0.9 + a * 0.1;
            d.g = Math.pow((1 - b), 20) / 4 + 0.05;
            return true;
        }
        return false;
    }
    public void drawShape(ShapeRose shapeRose){
        this.s=shapeRose.getSize();
         bufferedImage=draw(image);
        imagePanel.setSize(sizeX, sizeY);

        System.out.println("绘制");
    }

    // 绘制图形在image上
    public BufferedImage draw(BufferedImage image){
        // 复制一份用于绘制
        BufferedImage drawImage = ImageUtil.imageCopy(image);
        Graphics2D g2 = (Graphics2D)drawImage.getGraphics();
        g2.fillRect(0, 0, drawImage.getWidth(),drawImage.getHeight());//先设背景颜色为白色
        short zBuffer[]=new short[s * s];
        for(int j = 0;j<zBuffer.length-1;j++){
            zBuffer[j]=0;
        }
        Random random=new Random();
        for(int j = 0; j < 2000 ; j++)	//// 按任意键退出
        {
            for(int i = 0; i < 10000; i++)	 //
                if(calc(Math.random(), Math.random(),(random.nextInt(10000) )% 46 / 0.74, dot))
                {
                    z = (int) (dot.getZ() + 0.5);
                    x = (int)(dot.getX() * s / z - h + 0.5);
                    y = (int)(dot.getY() * s / z - h + 0.5);
                    if (y >= s) continue;

                    zBufferIndex = y * s + x;

                    if((zBuffer[zBufferIndex]==0) || (zBuffer[zBufferIndex] > z))
                    { zBuffer[zBufferIndex] = (short) z;
                        int r = ~(int)(dot.getR() * h);
                        if (r < 0) r = 0;	if (r > 255) r = 255;
                        int g = ~(int)((dot.getG() * h));
                        if (g < 0) g = 0;	if (g > 255) g = 255;
                        int b = ~(int)((dot.getR() * dot.getR() * -80));
                        if (b < 0) b = 0;	if (b > 255) b = 255;

                        g2.setColor(new Color(r, g, b));

                        g2.drawLine(x-25, y-25, x-25, y-25);

                    }
                }
        }
        return drawImage;
    }
    class ImagePanel extends JPanel{
        @Override
        public void paint(Graphics g) {
            this.setSize(bufferedImage.getWidth(), bufferedImage.getHeight());
            g.drawImage(bufferedImage, 0, 0,bufferedImage.getWidth(),bufferedImage.getHeight(),null);
        }
    }
    /**  更新信息区  */
    public void updateInfoArea(ShapeRose shapeRose){
        name.setText("名称：" + shapeRose.getName());
        size.setText("玫瑰花的大小为" + shapeRose.getSize());

    }

    // 自定义监听器
    public interface Listener{
        void onDraw(int s);
    }
    @Override
    public void clean() {

    }

    @Override
    public void saveToDataBase() {

    }

    @Override
    public void openDataBase() {

    }

}
