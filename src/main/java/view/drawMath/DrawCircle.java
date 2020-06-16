package view.drawMath;

import controller.drawMath.CircleController;
import controller.drawMath.TriangleController;
import entity.ShapeCircle;
import entity.ShapeTriangle;
import utils.AlertUtil;
import utils.DataCheck;
import utils.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawCircle extends JPanel implements IDraw{
    private CircleController controller;  // 持有控制器

    private ImagePanel imagePanel;  // 绘图区容器
    private BufferedImage image;    // 绘制的图片

    // 绘图区大小、边距
    private int sizeX = 560, sizeY = 480;   // 坐标系的大小
    private int margin = 20;    // 边距

    // 标题
    private String title = "圆形绘制";

    // 参数输入区
    private JTextField x1,y1;   // 圆心
    private JTextField radius;    // 半径长度
    private JButton startDrawBtn1;   // 绘制按钮

    // 信息区
    private JLabel name;    // 图形名称
    private JLabel dotO;    // 圆心点坐标

    private JLabel labelRadius;   // AB长度
    private JLabel perimeter;   // 周长
    private JLabel area;    // 面积

    public DrawCircle() {
        /* 初始化阶段     */
        this.initComponent();   // 初始化窗口组件
        this.setListener(); // 为按钮设置监听
        /* 初始化完成    */

        // 与控制层和监听器关联
        controller = new CircleController(this);

        this.setVisible(true);
    }

    /**  初始化组件 */
    private void initComponent(){
        /* 111111111111111   以下为顶部标题、输入框组件   111111111111111 */
        // 创建各对象
        x1 = new JTextField("");
        y1 = new JTextField("");
        radius= new JTextField();
        startDrawBtn1 = new JButton("绘制");


        /**   第一行输入参数组   输入三点绘制  */
        Box paramBox1 = Box.createHorizontalBox();
        paramBox1.add(Box.createHorizontalStrut(20));   // 左边距

        // 输入圆心
        Box box1A = Box.createHorizontalBox();
        box1A.add(new JLabel("圆心 X:"));
        box1A.add(x1);
        box1A.add(new JLabel("Y:"));
        box1A.add(y1);
        box1A.add(Box.createHorizontalStrut(10));
        Box boxR=Box.createHorizontalBox();
        boxR.add(new JLabel("半径："));
        boxR.add(radius);
        paramBox1.add(box1A);
        paramBox1.add(boxR);



        // 开始绘制按钮
        paramBox1.add(startDrawBtn1);

        paramBox1.add(Box.createHorizontalStrut(20));   // 右边距




        /**  主容器 */
        Box topBox = Box.createVerticalBox();
        topBox.add(Box.createVerticalStrut(10));    // 上边距
        topBox.add(new JLabel(title));  // 放入标题
        topBox.add(Box.createVerticalStrut(10));    // 加一个间距
        topBox.add(paramBox1);  // 放入第一行输入框组
        topBox.add(Box.createVerticalStrut(10));    // 加一个间距
        topBox.add(Box.createVerticalStrut(10));    // 下边距
        /* 11111111111111111111111111111111*/


        /* 2222222222   以下为中部绘图区组件  222222222 */
        image = ImageUtil.createBlankImage(sizeX, sizeY);
        imagePanel = new ImagePanel();
        /* 22222222222222222222222222222222 */


        /* 3333333333   以下为右侧信息区组件   33333333333333 */
        // 创建各对象
        name = new JLabel();    // 图形名称
        dotO = new JLabel();    // 圆心坐标

        labelRadius = new JLabel();   // 半径长度
        perimeter = new JLabel();   // 周长
        area = new JLabel();    // 面积

        Box rightInfoBox = Box.createHorizontalBox();
        Box infoBox = Box.createVerticalBox();

        int marginInfo = 10;
        infoBox.add(name);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(dotO);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(labelRadius);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(perimeter);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(area);

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
        startDrawBtn1.addActionListener(e -> {
            // 获取输入参数
            String strX1 = x1.getText();
            String strY1 = y1.getText();
            String strRadius=radius.getText();


            // 参数合法性校验
            if(!DataCheck.isNumber(strX1)) AlertUtil.warningDialog("请正确输入A点X坐标！");
            else if(!DataCheck.isNumber(strY1)) AlertUtil.warningDialog("请正确输入A点Y坐标！");
            else if(!DataCheck.isNumber(strRadius)) AlertUtil.warningDialog("请输入正确半径！");
            else {  // 校验通过
                System.out.println(Integer.valueOf(strX1)+" "+Integer.valueOf(strY1)+" "+Integer.valueOf(strRadius));
               // controller.onDraw1(Integer.valueOf(strX1), Integer.valueOf(strY1), Integer.valueOf(strRadius));

                controller.onDraw1(Integer.parseInt(strX1),Integer.parseInt(strY1),Integer.parseInt(strRadius));
            }

        });


    }


    /** 坐标轴  */
   private void coordinateDraw(Graphics2D g2){
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
    private void drawAuxiliaryLine(Graphics2D g2, Color color, String str,int x, int y, int px, int py){
        g2.setColor(color);
        g2.drawString(str, px-10, py-10);
        g2.drawLine(px, py, px, sizeY-margin );
        g2.drawString(String.valueOf(x), px-3, sizeY-margin+15);
        g2.drawLine(px, py, margin, py );
        g2.drawString(String.valueOf(y), margin-15, py+3);
    }

    /** 画图形 */
    public void drawShape(ShapeCircle shapeCircle){
        int x1 = shapeCircle.getX1();
        int y1 = shapeCircle.getY1();
        int radius=shapeCircle.getRadius();
        int x2=x1-radius;
        int y2=y1;
        int x3=x1+radius;
        int y3=y1;
        int x0=x1-radius;
        int y0=y1+radius;


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
        maxScale = maxScale>y1?maxScale:y1;
        maxScale = maxScale>x1+radius?maxScale:x1+radius;
        maxScale = maxScale>y1+radius?maxScale:y1+radius;


        // 如果数字太大，则提示错误
        if(maxScale>sizeY-margin*3){
            AlertUtil.errorDialog("坐标数值大于" + (sizeY-margin*3) + ",无法进行绘制！");
            return;
        }

        mS = (sizeY-margin*3)/(maxScale);

        // 将三点坐标转换成实际像素坐标点
        int px1 = x1*mS + margin;
        int py1 = sizeY - margin - y1*mS;
        int px2 = x2*mS + margin;
        int py2 = sizeY - margin - y2*mS;
        int px3= x3*mS + margin;
        int py3 = sizeY - margin - y3*mS;
        int px0= x0*mS + margin;
        int py0 = sizeY - margin - y0*mS;
        int rl=radius*mS;



        /**
         * 按照先画辅助线，再画坐标轴、图形的顺序，可以避免辅助线覆盖图形和坐标轴
         */
        // 画三点辅助线
       // drawAuxiliaryLine(g2, Color.ORANGE, "Q", x0, y0, px0, py0);
        drawAuxiliaryLine(g2, Color.GREEN, "O", x1, y1, px1, py1);
        drawAuxiliaryLine(g2, Color.BLUE, "A", x2, y2, px2, py2);
        drawAuxiliaryLine(g2, Color.RED, "B", x3, y3, px3, py3);
        g2.setColor(Color.BLACK);   // 恢复黑色

        // 画坐标轴
        coordinateDraw(g2);

        // 画圆
        g2.setStroke(new BasicStroke(2f));
        g2.drawOval(px0,py0,rl*2,rl*2);

        imagePanel.repaint();
    }


    /**  更新信息区  */
    public void updateInfoArea(ShapeCircle shapeCircle){
        name.setText("名称：" + shapeCircle.getName());
        dotO.setText("圆心(" + shapeCircle.getX1() + "," + shapeCircle.getY1() + ")");

        labelRadius.setText("半径：" + shapeCircle.getRadius());
        perimeter.setText("周长：" + String.format("%.2f",shapeCircle.getPerimeter()));
        area.setText("面积：" + String.format("%.2f", shapeCircle.getArea()));   // 保留两位小数输出面积
    }

    @Override
    public void clean() {
        x1.setText("");
        y1.setText("");


        radius.setText("");
    }

    @Override
    public void saveToDataBase() {

    }

    @Override
    public void openDataBase() {

    }

    private class ImagePanel extends JPanel{
        @Override
        public void paint(Graphics g) {
            this.setSize(image.getWidth(), image.getHeight());
            g.drawImage(image, 0, 0,image.getWidth(),image.getHeight(),null);
        }
    }

    // 自定义监听器
    public interface Listener{
        void onDraw1(int x1, int y1, int radius);

    }
}
