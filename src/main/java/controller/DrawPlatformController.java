package controller;

import api.DrawPlanformApi;
import controller.drawMath.DrawMathController;
import entity.module.DrawParams;
import entity.module.Point;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.misc.BASE64Encoder;
import utils.AlertUtil;
import utils.DrawPlatformMode;
import utils.ImageUtil;
import utils.http.MyResponse;
import utils.http.ResultCode;
import view.DataQueryView;
import view.PasswordModifyView;
import view.drawMath.DrawMathView;
import view.drawPlatform.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 平面绘图模式
 */

public class DrawPlatformController implements TopMenuBar.TopMenuListener, TopToolBar.TopToolListener, LeftToolBar.LeftToolListener, DrawBroadPanel.DrawBroadListener {
    DrawPlatformView drawPlatFormView;  // JFram
    DrawParams drawParams;  // 参数

    public DrawPlatformController(DrawPlatformView drawPlatFormView, BufferedImage image) {
        this.drawPlatFormView = drawPlatFormView;
        this.init(image);
    }

    public void init(BufferedImage image){
        // 设置初始参数
        drawParams = new DrawParams();
        drawParams.setDrawPlatformMode(DrawPlatformMode.MOUSE);
        drawParams.setLineStroke(2.0f);
        drawParams.setPenColor(Color.BLUE);
        drawParams.setBackgroundColor(Color.WHITE);
        drawParams.setRubberSize(20);

        if(image == null){
            image = ImageUtil.createBlankImage(640, 480);
        }
        drawParams.setImage(image);
        drawParams.setGroundSizeX(image.getWidth());
        drawParams.setGroundSizeY(image.getHeight());


        // 更新初始状态到view层
        drawPlatFormView.paintImage(image);
        drawPlatFormView.initTopToolParams(drawParams.getPenColor(), drawParams.getBackgroundColor(), drawParams.getGroundSizeX(), drawParams.getGroundSizeY());
    }

    // 绘制到界面上
    private void paint(){
        drawPlatFormView.paintImage(drawParams.getImage());
    }

    /**  顶部工具栏监听  **/
    // 画笔颜色改变
    @Override
    public void onPenColorChanged(Color color) {
        drawParams.setPenColor(color);
    }

    // 画布颜色改变
    @Override
    public void onBackColorChanged(Color color) {
        drawParams.setBackgroundColor(color);
        Graphics g = drawParams.getImage().getGraphics();
        g.setColor(color);
        g.fillRect(0,0,this.drawParams.getGroundSizeX(),drawParams.getGroundSizeY());
        paint();
    }

    // 画笔粗细改变
    @Override
    public void onLineStrockChanged(Float lineStroke) {
        drawParams.setLineStroke(lineStroke);
    }

    // 画布尺寸改变
    @Override
    public void onSizeChanged(Integer x, Integer y) {
        // 保留图像历史记录
        drawParams.pushRecordStack(ImageUtil.imageCopy(drawParams.getImage()));

        // 创建一个新的image对象，把原来的image对象绘制到上面
        BufferedImage image = new BufferedImage(x,y,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(drawParams.getBackgroundColor());
        g.fillRect(0,0,x, y);
        g.setColor(drawParams.getPenColor());
        g.drawImage(drawParams.getImage(), 0, 0, drawParams.getGroundSizeX(), drawParams.getGroundSizeY(),null);

        // 改变存储参数
        drawParams.setGroundSizeX(x);
        drawParams.setGroundSizeY(y);
        drawParams.setImage(image);

        // 把改变后的图像绘制到界面上
        paint();
    }

    /**   撤销  */
    @Override
    public void onOperateBack() {
        Object image = drawParams.popRecordStack(); // 从历史记录栈取出
        if( image != null){
            drawParams.pushCancelStack(drawParams.getImage());    // 把当前图片放入撤销记录栈
            drawParams.setImage((BufferedImage)image);
            paint();
        }
    }

    /**   重做  */
    @Override
    public void onOperateRedo() {
        Object image = drawParams.popCancelStack();
        if( image != null){
            drawParams.pushRecordStack(drawParams.getImage());
            drawParams.setImage((BufferedImage)image);
            paint();
        }
    }


    /**  侧边工具栏监听  **/
    // 鼠绘图模式改变
    @Override
    public void onModeChanged(DrawPlatformMode drawPlatformMode) {
        drawParams.setDrawPlatformMode(drawPlatformMode);
        drawPlatFormView.setDrawBroadCursor(drawPlatformMode.getCursor());   // 改变鼠标形状
    }

    // 鼠标拖动
    @Override
    public void mouseDragged(Point p2) {
        Point p1 = drawParams.getPressedPoint();

        if(drawParams.getDrawPlatformMode().equals(DrawPlatformMode.PEN)){ // 钢笔绘制
            if (drawParams.getPreviousPoint() != null) {
                Graphics2D g2 = drawParams.getImage().createGraphics();
                g2.setColor(drawParams.getPenColor());
                g2.setStroke(new BasicStroke(drawParams.getLineStroke()));
                ImageUtil.cleanTooth(g2);
                g2.drawLine(drawParams.getPreviousPoint().getX(), drawParams.getPreviousPoint().getY(), p2.getX(), p2.getY());
            }
        }else if(drawParams.getDrawPlatformMode().equals(DrawPlatformMode.RUBBER)){  // 橡皮擦
            Graphics g = drawParams.getImage().getGraphics();
            g.setColor(drawParams.getBackgroundColor());
            Integer radius = drawParams.getRubberSize();  // 橡皮擦半径
            g.fillOval(p2.getX() - (radius / 2), p2.getY() - (radius / 2), radius, radius);
        }else {
            // 计算最小点、最大点
            int x1, y1, x2, y2, width, height;
            if(p1.getX() < p2.getX() ){
                x1 = p1.getX();
                x2 = p2.getX();
            }else{
                x1 = p2.getX();
                x2 = p1.getX();
            }

            if(p1.getY() < p2.getY()){
                y1 = p1.getY();
                y2 = p2.getY();
            }else{
                y1 = p2.getY();
                y2 = p1.getY();
            }

            width = x2-x1;
            height = y2-y1;

            BufferedImage drawImage = ImageUtil.imageCopy(drawParams.getPressedImage());
            Graphics2D g2 = (Graphics2D)drawImage.getGraphics();
            g2.setColor(drawParams.getPenColor() );
            g2.setStroke(new BasicStroke(drawParams.getLineStroke()));
            ImageUtil.cleanTooth(g2);

            switch(drawParams.getDrawPlatformMode()) {
                case FILL_RECTANGEL: {  // 绘制实心矩形
                    g2.fill3DRect(x1, y1, width, height, false);
                }break;
                case RECTANGEL:{    // 绘制矩形
                    g2.drawRect(x1, y1, width, height);
                }break;
                case CIRCLE:{   // 绘制圆形
                    g2.drawOval(x1, y1, width, height);
                }break;
                case LINE :{    // 画直线
                    g2.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
                }break;
                case TRIANGLE :{    // 画三角形
                    // 一般画法
//                    g2.drawLine( x1, y2, x2, y2);   // AB线
//                    g2.drawLine(x2, y2, (x1+x2)/2, y1);    // BC线
//                    g2.drawLine((x1+x2)/2, y1, x1, y2);    // CA线

                    // 允许上下左右倒置的画法
                    g2.drawLine( p1.getX(), p2.getY(), p2.getX(), p2.getY());   // AB线
                    g2.drawLine(p2.getX(), p2.getY(), (p1.getX()+p2.getX())/2, p1.getY());    // BC线
                    g2.drawLine((p1.getX()+p2.getX())/2, p1.getY(), p1.getX(), p2.getY());    // CA线
                }break;
                default : break;
            }

            drawParams.setImage(drawImage);   // 更新绘制图像
        }

        // 把更新后的图像绘制到界面上
        paint();
        // 记录当前坐标
        drawParams.setPreviousPoint(p2);
    }

    // 鼠标移动
    @Override
    public void mouseMoved(Point p) { }

    // 鼠标点击
    @Override
    public void mouseClicked(Point p) { }

    // 鼠标按下
    @Override
    public void mousePressed(Point p) {
        // 记录鼠标按下时的点和图像
        drawParams.setPreviousPoint(p);
        drawParams.setPressedPoint(p);
        drawParams.setPressedImage(drawParams.getImage());
        // 按下就一定会产生图像绘制，此时先保存记录用以撤销
        if(drawParams.getDrawPlatformMode() != DrawPlatformMode.MOUSE){
            drawParams.pushRecordStack(ImageUtil.imageCopy(drawParams.getImage()));
        }
    }

    // 鼠标松开
    @Override
    public void mouseReleased(Point p) {
        // 清除鼠标和图像绘制记录
        drawParams.setPreviousPoint(null);
        drawParams.setPreviousImage(null);
        drawParams.setPressedPoint(null);
        drawParams.setPressedImage(null);
    }

    // 鼠标进入绘图区
    @Override
    public void mouseEntered(Point p) { }

    // 鼠标离开绘图区
    @Override
    public void mouseExited(Point p) { }

    // 顶部菜单栏被点击
    @Override
    public void onMenuItemClick(String command) {
        switch (command){
            case "用户-修改密码" : {
                new PasswordModifyView();
            }break;
            case "用户-注销" : {
                drawPlatFormView.dispose();
            }break;
            case "文件-打开数据库文件" : {
                new DataQueryView(null);
            }break;
            case "文件-新建" : {
                new DrawPlatformView();
            }break;
            case "文件-保存到数据库" : {
                saveToDB();
            }break;
            case "文件-保存到文件" : {
                ImageUtil.saveToFile(drawParams.getImage(), drawPlatFormView);
            }break;
            case "编辑-撤销" : {
                onOperateBack();
            }break;
            case "编辑-重做" : {
                onOperateRedo();
            }break;
            case "编辑-清空" : {
                drawParams.setBackgroundColor(Color.WHITE);
                Graphics g = drawParams.getImage().getGraphics();
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, drawParams.getGroundSizeX(), drawParams.getGroundSizeY());
                paint();
            }break;
            case "画板模式-平面绘图模式" : {
//                new DemodeController();
            }break;
            case "画板模式-数学绘图模式" : {
                int r=JOptionPane.showConfirmDialog(drawPlatFormView,"是否关闭当前窗口？","是否关闭",JOptionPane.YES_NO_CANCEL_OPTION);
                if(r==JOptionPane.YES_OPTION){
                    drawPlatFormView.dispose();
                    new DrawMathView();
                }else if(r != JOptionPane.CANCEL_OPTION){
                    new DrawMathView();
                }
            }break;
            default:break;
        }
    }

    // 保存到数据库
    public void saveToDB() {
        BASE64Encoder encoder = new BASE64Encoder();
        String imageStr;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(drawParams.getImage(), "jpeg", baos);
            byte[] bytes = baos.toByteArray();
            imageStr = encoder.encodeBuffer(bytes).trim();
            String name=(String) JOptionPane.showInputDialog(drawPlatFormView, "请输入绘制图像名称：", "图像名称", JOptionPane.PLAIN_MESSAGE);

            Map<String, String> params = new HashMap<>();
            params.put("image", imageStr);
            params.put("name", name);

            // 请求网络数据
            DrawPlanformApi drawPlanformApi = new DrawPlanformApi();

            MyResponse response = drawPlanformApi.insert(params);
            if(response.getStatus() == ResultCode.SUCCESS.getCode()){    // 成功
                AlertUtil.infoDialog(response.getMsg());
            }else { // 失败
                AlertUtil.errorDialog(response.getMsg());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }

}
