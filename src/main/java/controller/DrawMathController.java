package controller;

import sun.misc.BASE64Encoder;
import utils.DrawMathMode;
import view.drawMath.DrawMathView;
import view.drawMath.LeftMathToolBar;
import view.drawMath.TopMathToolBar;
import view.drawMath.TopMenuBar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class DrawMathController implements TopMenuBar.TopMenuListener, TopMathToolBar.TopMathToolListener, LeftMathToolBar.LeftMathToolListener  {
    DrawMathView drawMathView;  // JFram

    Shape shape;
    DrawMathMode mathMode;
    BufferedImage image;


    public DrawMathController() {
        // 生成初始图片
        BufferedImage image = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,image.getWidth(), image.getHeight());
        this.init(image);
    }

    public DrawMathController(BufferedImage image) {
        this.init(image);
    }

    public void init(BufferedImage image){
        // 绑定view层
        drawMathView = new DrawMathView();
        drawMathView.setListener(this,this, this);
        drawMathView.paintImage(image);
    }

    // 顶部菜单栏被点击
    @Override
    public void onMenuItemClick(String command) {
        switch (command){
            case "用户-修改密码" : {
//                new PasswordModifyController();
            }break;
            case "文件-打开数据库文件" : {
//                new ImageSaveController();
            }break;
            case "文件-新建" : {
                new DrawMathController();
            }break;
            case "文件-保存到数据库" : {
                saveToDB();
            }break;
            case "文件-保存到文件" : {

            }break;
            case "画板模式-平面绘图模式" : {
                new DrawPlatformController();
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
            ImageIO.write(image, "jpeg", baos);
            byte[] bytes = baos.toByteArray();
            imageStr = encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

//        Image image = new Image();
//        image.setUserId(1);
//        image.setTime(new Date());
//        image.setImage(imageStr);
//        ImageMapper imageMapper = new ImageMapper();
//        if(imageMapper.insertImage(image) > 0){
//            drawMainView.showMessageDialog("保存成功！");
//        }else {
//            drawMainView.showMessageDialog("保存失败！");
//        }
    }


    // 存为文件
    private void saveToFile(String fileName,String fileSrc) {
        try {
            ImageIO.write(image, "jpeg", new File(fileSrc+fileName+".jpeg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onPenColorChanged(Color color) {

    }

    @Override
    public void onLineStrockChanged(Float lineStroke) {

    }

    @Override
    public void onParamsChanged(Integer X1, Integer Y1, Float length, Float width, Float height, Float radius, Integer X2, Integer Y2) {

    }

    @Override
    public void onModeChanged(DrawMathMode drawMathMode) {

    }
}
