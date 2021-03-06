package view.drawMath;

import controller.drawMath.BoxController;
import controller.drawMath.RectangleController;
import entity.ShapeRectangle;
import utils.AlertUtil;
import utils.DataCheck;
import utils.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawRectangle extends JPanel implements IDraw{
    private RectangleController rectangleController;
    private ImagePanel imagePanel;  // 绘图区容器
    private BufferedImage image;    // 绘制的图片

    // 绘图区大小、边距
    private int sizeX = 560, sizeY = 480;   // 坐标系的大小
    private int margin = 20;    // 边距
    // 标题
    private String title = "矩形绘制";

    // 参数输入区
    private JTextField x, y; // 左上角顶点A的坐标
    private JTextField length, width;    // 矩形的长和宽
    private JButton startDrawBtn;   // 绘制按钮

    // 信息区
    private JLabel name;    // 图形名称
    private JLabel dotA;    // A点坐标
    private JLabel len;   // 矩形的长
    private JLabel wid;   // 矩形的宽
    private JLabel perimeter;   // 周长
    private JLabel area;    // 面积

    public DrawRectangle(ShapeRectangle shapeRectangle) {
        /* 初始化阶段     */
        this.initComponent();   // 初始化窗口组件
        this.setListener(); // 为按钮设置监听
        /* 初始化完成    */

        // 与控制层和监听器关联
        if(shapeRectangle != null){
            rectangleController = new RectangleController(this, shapeRectangle);
            rectangleController.onDraw();
        }else {
            rectangleController = new RectangleController(this);
        }

        this.setVisible(true);
    }


    public DrawRectangle() {
        this(null);
    }

    /**
     * 初始化组件
     */
    private void initComponent() {
        /* 111111111111111   以下为顶部标题、输入框组件   111111111111111 */
        // 创建各对象
        x = new JTextField("");
        y = new JTextField("");
        length = new JTextField();
        width = new JTextField();
        startDrawBtn = new JButton("绘制");

        /**   第一行输入参数组   输入一个点和长和宽绘制  */
        Box paramBox = Box.createHorizontalBox();
        paramBox.add(Box.createHorizontalStrut(20));   // 左边距
        // 输入A点
        Box boxA = Box.createHorizontalBox();
        boxA.add(new JLabel("A点 X:"));
        boxA.add(x);
        boxA.add(new JLabel("Y:"));
        boxA.add(y);
        boxA.add(Box.createHorizontalStrut(10));
        paramBox.add(boxA);

        // 矩形长度
        Box boxl = Box.createHorizontalBox();
        boxl.add(new JLabel("矩形长度："));
        boxl.add(length);
        boxl.add(Box.createHorizontalStrut(20));
        paramBox.add(boxl);

        // 矩形的宽度
        Box boxw = Box.createHorizontalBox();
        boxw.add(new JLabel("矩形宽度："));
        boxw.add(width);
        boxw.add(Box.createHorizontalStrut(20));
        paramBox.add(boxw);

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
        dotA = new JLabel();    // A点坐标
        len = new JLabel();   // BC长度
        wid = new JLabel();   // CA长度
        perimeter = new JLabel();   // 周长
        area = new JLabel();    // 面积

        Box rightInfoBox = Box.createHorizontalBox();
        Box infoBox = Box.createVerticalBox();

        int marginInfo = 10;
        infoBox.add(name);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(dotA);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(len);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(wid);
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
        startDrawBtn.addActionListener(e -> {
            // 获取输入参数
            String strX = x.getText();
            String strY = y.getText();
            String widthText = width.getText();
            String lengthText = length.getText();

            if ( !DataCheck.isNumber(strX)) AlertUtil.warningDialog("请正确输入A点X坐标！");
            else if (!DataCheck.isNumber(strY)) AlertUtil.warningDialog("请正确输入A点Y坐标！");
            else if (  !DataCheck.isNumber(widthText)) AlertUtil.warningDialog("请正确输入矩形的长！");
            else if ( !DataCheck.isNumber(lengthText)) AlertUtil.warningDialog("请正确输入矩形的宽！");
            else {
                rectangleController.onDraw1(Integer.valueOf(strX), Integer.valueOf(strY), Integer.valueOf(lengthText), Integer.valueOf(widthText));
            }
        });

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
    private void drawAuxiliaryLine(Graphics2D g2, Color color, String str,int x, int y, int px, int py){
        g2.setColor(color); // 设置画笔颜色
        g2.drawString(str, px-10, py-10);   // 在点（px, py）旁边做标记，如‘A’点
        g2.drawLine(px, py, px, sizeY-margin ); // 画从点（px, py）到X轴的辅助线
        g2.drawString(String.valueOf(x), px-3, sizeY-margin+15);    // 标明X轴的刻度
        g2.drawLine(px, py, margin, py );   // 画从点（px, py）到Y轴的辅助线
        g2.drawString(String.valueOf(y), margin-15, py+3);  // 标明Y轴的刻度
    }

    /** 画图形 */
    public void drawShape(ShapeRectangle shapeRectangle){
        int x = shapeRectangle.getX();
        int y = shapeRectangle.getY();
        int w = shapeRectangle.getWidth();
        int l = shapeRectangle.getLength();

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
        maxScale = x;
        maxScale = maxScale>x?maxScale:x;
        maxScale = maxScale>y?maxScale:y;
        maxScale = maxScale>x+w?maxScale:x+w;
        maxScale = maxScale>y+l?maxScale:y+l;

        if(maxScale>sizeY-margin*3){
            AlertUtil.errorDialog("坐标数值大于" + (sizeY-margin*3) + ",无法进行绘制！");
            return;
        }


        mS = (sizeY-margin*3)/(maxScale);

        // 将三点坐标转换成实际像素坐标点
        int px1 = x*mS + margin;
        int py1 = sizeY - margin - y*mS;
        int width=w*mS;
        int height=l*mS;
        // 画坐标轴
        coordinateDraw(g2);

        // 画四条边
       g2.drawRect(px1,py1-height,width,height);
        // 画A点辅助线
        drawAuxiliaryLine(g2, Color.GREEN, "A", x, y, px1,py1);

        imagePanel.repaint();
    }
    /**  更新信息区  */
    public void updateInfoArea(ShapeRectangle shapeRectangle){
        name.setText("名称：" + shapeRectangle.getName());
        dotA.setText("A(" + shapeRectangle.getX() + "," + shapeRectangle.getY() + ")");
        len.setText("矩形的长：" +shapeRectangle.getLength());
        wid.setText("矩形的宽：" + shapeRectangle.getWidth());
        perimeter.setText("周长：" + shapeRectangle.getPerimeter());
        area.setText("面积：" + shapeRectangle.getArea());   // 保留两位小数输出面积
    }


    public void setInput(ShapeRectangle shapeRectangle) {
        x.setText(shapeRectangle.getX() + "");
        y.setText(shapeRectangle.getY() + "");
        length.setText(shapeRectangle.getLength() + "");
        width.setText(shapeRectangle.getWidth() + "");
    }

    @Override
    public void clean() {
        x.setText("");
        y.setText("");
        length.setText("");
        width.setText("");
    }

    @Override
    public void saveToDataBase() {
        rectangleController.saveToDataBase();
    }

    @Override
    public void openDataBase() {

    }

    @Override
    public void saveToFile() {
        ImageUtil.saveToFile(image, this);
    }

    class ImagePanel extends JPanel{
        @Override
        public void paint(Graphics g) {
            this.setSize(image.getWidth(), image.getHeight());
            g.drawImage(image, 0, 0,image.getWidth(),image.getHeight(),null);
        }
    }

    // 自定义监听器
    public interface Listener{
        void onDraw1(int x, int y, int l, int w);
        void saveToDataBase();
    }

}