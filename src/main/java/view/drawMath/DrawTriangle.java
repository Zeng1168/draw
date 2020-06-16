package view.drawMath;

import controller.drawMath.TriangleController;
import entity.ShapeTriangle;
import utils.AlertUtil;
import utils.DataCheck;
import utils.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 绘制三角形
 */
public class DrawTriangle extends JPanel implements IDraw {
    private TriangleController controller;  // 持有控制器

    private ImagePanel imagePanel;  // 绘图区容器
    private BufferedImage image;    // 绘制的图片

    // 绘图区大小、边距
    private int sizeX = 560, sizeY = 480;   // 坐标系的大小
    private int margin = 20;    // 边距

    // 标题
    private String title = "三角形绘制";

    // 参数输入区
    private JTextField x1,y1,x2,y2,x3,y3;   // 三点
    private JTextField lengthAB, lengthBC, lengthCA;    // 三条线段长度
    private JButton startDrawBtn1, startDrawBtn2;   // 绘制按钮

    // 信息区
    private JLabel name;    // 图形名称
    private JLabel dotA;    // A点坐标
    private JLabel dotB;    // B点坐标
    private JLabel dotC;    // C点坐标
    private JLabel lengthLabelAB;   // AB长度
    private JLabel lengthLabelBC;   // BC长度
    private JLabel lengthLabelCA;   // CA长度
    private JLabel perimeter;   // 周长
    private JLabel area;    // 面积

    public DrawTriangle() {
        /* 初始化阶段     */
        this.initComponent();   // 初始化窗口组件
        this.setListener(); // 为按钮设置监听
        /* 初始化完成    */

        // 与控制层和监听器关联
        controller = new TriangleController(this);

        this.setVisible(true);
    }

    /**  初始化组件 */
    private void initComponent(){
        /* 111111111111111   以下为顶部标题、输入框组件   111111111111111 */
        // 创建各对象
        x1 = new JTextField("");
        x2 = new JTextField("");
        x3 = new JTextField("");
        y1 = new JTextField("");
        y2 = new JTextField("");
        y3 = new JTextField("");
        lengthAB = new JTextField();
        lengthBC = new JTextField();
        lengthCA = new JTextField();
        startDrawBtn1 = new JButton("绘制");
        startDrawBtn2 = new JButton("绘制");

        /**   第一行输入参数组   输入三点绘制  */
        Box paramBox1 = Box.createHorizontalBox();
        paramBox1.add(Box.createHorizontalStrut(20));   // 左边距

        // 输入A点
        Box box1A = Box.createHorizontalBox();
        box1A.add(new JLabel("A点 X:"));
        box1A.add(x1);
        box1A.add(new JLabel("Y:"));
        box1A.add(y1);
        box1A.add(Box.createHorizontalStrut(10));
        paramBox1.add(box1A);

        // 输入B点
        Box box1B=Box.createHorizontalBox();
        box1B.add(new JLabel("B点 X:"));
        box1B.add(x2);
        box1B.add(new JLabel("Y:"));
        box1B.add(y2);
        box1B.add(Box.createHorizontalStrut(10));
        paramBox1.add(box1B);

        // 输入C点
        Box box1C = Box.createHorizontalBox();
        box1C.add(new JLabel("C点 X:"));
        box1C.add(x3);
        box1C.add(new JLabel("Y:"));
        box1C.add(y3);
        box1C.add(Box.createHorizontalStrut(10));
        paramBox1.add(box1C);

        // 开始绘制按钮
        paramBox1.add(startDrawBtn1);

        paramBox1.add(Box.createHorizontalStrut(20));   // 右边距


        /**  第二行输入参数组  输入三条线段长度绘制（A点为坐标原点，AB边在X轴）  */
        Box paramBox2 = Box.createHorizontalBox();
        paramBox2.add(Box.createHorizontalStrut(20));   // 左边距

        // AB长度
        Box box2AB = Box.createHorizontalBox();
        box2AB.add(new JLabel("AB长度："));
        box2AB.add(lengthAB);
        box2AB.add(Box.createHorizontalStrut(20));
        paramBox2.add(box2AB);

        // BC长度
        Box box2BC = Box.createHorizontalBox();
        box2BC.add(new JLabel("BC长度："));
        box2BC.add(lengthBC);
        box2BC.add(Box.createHorizontalStrut(20));
        paramBox2.add(box2BC);

        // CA长度
        Box box2CA = Box.createHorizontalBox();
        box2CA.add(new JLabel("CA长度："));
        box2CA.add(lengthCA);
        box2CA.add(Box.createHorizontalStrut(20));
        paramBox2.add(box2CA);


        // 开始绘制按钮
        paramBox2.add(startDrawBtn2);

        paramBox2.add(Box.createHorizontalStrut(20));   // 右边距


        /**  主容器 */
        Box topBox = Box.createVerticalBox();
        topBox.add(Box.createVerticalStrut(10));    // 上边距
        topBox.add(new JLabel(title));  // 放入标题
        topBox.add(Box.createVerticalStrut(10));    // 加一个间距
        topBox.add(paramBox1);  // 放入第一行输入框组
        topBox.add(Box.createVerticalStrut(10));    // 加一个间距
        topBox.add(paramBox2);  // 放入第二行输入框组
        topBox.add(Box.createVerticalStrut(10));    // 下边距
        /* 11111111111111111111111111111111*/


        /* 2222222222   以下为中部绘图区组件  222222222 */
        image = ImageUtil.createBlankImage(sizeX, sizeY);
        imagePanel = new ImagePanel();
        /* 22222222222222222222222222222222 */


        /* 3333333333   以下为右侧信息区组件   33333333333333 */
        // 创建各对象
        name = new JLabel();    // 图形名称
        dotA = new JLabel();    // A点坐标
        dotB = new JLabel();    // B点坐标
        dotC = new JLabel();    // C点坐标
        lengthLabelAB = new JLabel();   // AB长度
        lengthLabelBC = new JLabel();   // BC长度
        lengthLabelCA = new JLabel();   // CA长度
        perimeter = new JLabel();   // 周长
        area = new JLabel();    // 面积

        Box rightInfoBox = Box.createHorizontalBox();
        Box infoBox = Box.createVerticalBox();

        int marginInfo = 10;
        infoBox.add(name);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(dotA);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(dotB);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(dotC);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(lengthLabelAB);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(lengthLabelBC);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(lengthLabelCA);
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
            String strX2 = x2.getText();
            String strY2 = y2.getText();
            String strX3 = x3.getText();
            String strY3 = y3.getText();

            // 参数合法性校验
            if(!DataCheck.isNumber(strX1)) AlertUtil.warningDialog("请正确输入A点X坐标！");
            else if(!DataCheck.isNumber(strY1)) AlertUtil.warningDialog("请正确输入A点Y坐标！");
            else if(!DataCheck.isNumber(strX2)) AlertUtil.warningDialog("请正确输入B点X坐标！");
            else if(!DataCheck.isNumber(strY2)) AlertUtil.warningDialog("请正确输入B点Y坐标！");
            else if(!DataCheck.isNumber(strX3)) AlertUtil.warningDialog("请正确输入C点X坐标！");
            else if(!DataCheck.isNumber(strY3)) AlertUtil.warningDialog("请正确输入C点Y坐标！");
            else {  // 校验通过
                controller.onDraw1(Integer.valueOf(strX1), Integer.valueOf(strY1), Integer.valueOf(strX2), Integer.valueOf(strY2), Integer.valueOf(strX3), Integer.valueOf(strY3));
            }

        });

        startDrawBtn2.addActionListener(e -> {
           //  获取输入参数
            String strLengthAB = lengthAB.getText();
            String strLengthBC = lengthBC.getText();
            String strLengthCA = lengthCA.getText();

            // 参数合法性校验
            if(!DataCheck.isNumber(strLengthAB)) AlertUtil.warningDialog("请正确输入AB长度！");
            else if(!DataCheck.isNumber(strLengthBC)) AlertUtil.warningDialog("请正确输入BC长度！");
            else if(!DataCheck.isNumber(strLengthCA)) AlertUtil.warningDialog("请正确输入CA长度！");
            else {
                // 参数转数字
                int Lab = Integer.valueOf(strLengthAB);
                int Lbc = Integer.valueOf(strLengthBC);
                int Lca = Integer.valueOf(strLengthCA);
                // 参数合理性校验
                if(Lab > Lbc + Lca) AlertUtil.warningDialog("BC、CA边长度之和小于AB边长度，无法构成三角形！");
                else if(Lbc > Lab + Lca) AlertUtil.warningDialog("AB、CA边长度之和小于BC边长度，无法构成三角形！");
                else if(Lca > Lab + Lbc) AlertUtil.warningDialog("AB、BC边长度之和小于CA边长度，无法构成三角形！");
                else {  // 校验通过
                    controller.onDraw2(Lab, Lbc, Lca);
                }
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
        g2.setColor(color); // 设置画笔颜色
        g2.drawString(str, px-10, py-10);   // 在点（px, py）旁边做标记，如‘A’点
        g2.drawLine(px, py, px, sizeY-margin ); // 画从点（px, py）到X轴的辅助线
        g2.drawString(String.valueOf(x), px-3, sizeY-margin+15);    // 标明X轴的刻度
        g2.drawLine(px, py, margin, py );   // 画从点（px, py）到Y轴的辅助线
        g2.drawString(String.valueOf(y), margin-15, py+3);  // 标明Y轴的刻度
    }

    /** 画图形 */
    public void drawShape(ShapeTriangle shapeTriangle){
        int x1 = shapeTriangle.getX1();
        int y1 = shapeTriangle.getY1();
        int x2 = shapeTriangle.getX2();
        int y2 = shapeTriangle.getY2();
        int x3 = shapeTriangle.getX3();
        int y3 = shapeTriangle.getY3();
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


        /**
         * 按照先花辅助线，再画坐标轴、图形的顺序，可以避免辅助线覆盖图形和坐标轴
         */
        // 画三点辅助线
        drawAuxiliaryLine(g2, Color.GREEN, "A", x1, y1, px1, py1);
        drawAuxiliaryLine(g2, Color.BLUE, "B", x2, y2, px2, py2);
        drawAuxiliaryLine(g2, Color.RED, "C", x3, y3, px3, py3);
        g2.setColor(Color.BLACK);   // 恢复黑色

        // 画坐标轴
        coordinateDraw(g2);

        // 画三边
        g2.setStroke(new BasicStroke(2f));
        g2.drawLine(px1, py1, px2, py2);
        g2.drawLine(px2, py2, px3, py3);
        g2.drawLine(px3, py3, px1, py1);

        imagePanel.repaint();
    }

    /**  更新ABC三点坐标、距离到文本框  */
    public void updateABC(ShapeTriangle shapeTriangle){
        x1.setText(String.valueOf(shapeTriangle.getX1()));
        y1.setText(String.valueOf(shapeTriangle.getY1()));
        x2.setText(String.valueOf(shapeTriangle.getX2()));
        y2.setText(String.valueOf(shapeTriangle.getY2()));
        x3.setText(String.valueOf(shapeTriangle.getX3()));
        y3.setText(String.valueOf(shapeTriangle.getY3()));

        lengthAB.setText(String.valueOf(shapeTriangle.getLengthAB()));
        lengthBC.setText(String.valueOf(shapeTriangle.getLengthBC()));
        lengthCA.setText(String.valueOf(shapeTriangle.getLengthCA()));
    }

    /**  更新信息区  */
    public void updateInfoArea(ShapeTriangle shapeTriangle){
        name.setText("名称：" + shapeTriangle.getName());
        dotA.setText("A(" + shapeTriangle.getX1() + "," + shapeTriangle.getY1() + ")");
        dotB.setText("B(" + shapeTriangle.getX2() + "," + shapeTriangle.getY2() + ")");
        dotC.setText("C(" + shapeTriangle.getX3() + "," + shapeTriangle.getY3() + ")");
        lengthLabelAB.setText("AB边长度：" + shapeTriangle.getLengthAB());
        lengthLabelBC.setText("BC边长度：" + shapeTriangle.getLengthBC());
        lengthLabelCA.setText("CA边长度：" + shapeTriangle.getLengthCA());
        perimeter.setText("周长：" + shapeTriangle.getPerimeter());
        area.setText("面积：" + String.format("%.2f", shapeTriangle.getArea()));   // 保留两位小数输出面积
    }

    @Override
    public void clean() {
        x1.setText("");
        y1.setText("");
        x2.setText("");
        y2.setText("");
        x3.setText("");
        y3.setText("");

        lengthAB.setText("");
        lengthBC.setText("");
        lengthCA.setText("");
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
        void onDraw1(int x1, int y1, int x2, int y2, int x3, int y3);
        void onDraw2(int ab, int bc, int ca);
    }
}
