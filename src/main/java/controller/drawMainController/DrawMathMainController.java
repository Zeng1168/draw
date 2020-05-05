package controller.drawMainController;

import controller.ImageSaveController;
import controller.OffScreen3DImage;
import controller.UserModifyController;
import entity.DrawMain;
import entity.DrawMathMain;
import entity.Point;
import sun.misc.BASE64Encoder;
import utils.DrawMathMode;
import utils.DrawMode;
import utils.ImageUtil;
import view.drawMainView.DrawMainView;
import view.drawMathView.DrawMathMainView;
import view.drawMathView.LeftMathToolBar;
import view.drawMathView.TopMathMenuBar;
import view.drawMathView.TopMathToolBar;

import javax.imageio.ImageIO;
import javax.media.j3d.BranchGroup;
import javax.vecmath.Color3f;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class DrawMathMainController implements TopMathMenuBar.TopMathMenuListener, TopMathToolBar.TopMathToolListener, LeftMathToolBar.LeftMathToolListener {

    DrawMathMainView drawMainView;//JFram

    DrawMathMain drawMain;//参数

    BranchGroup group;

    public DrawMathMainController() {
        // 生成初始图片

        Graphics g;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 680, 460);
        this.init();
    }

    public void init() {
        // 设置初始参数
        drawMain = new DrawMathMain();
        drawMain.setDrawMode(DrawMathMode.LINE);
        drawMain.setLineStroke(2.0f);
        drawMain.setPenColor(Color.BLUE);

        // 绑定view层
        drawMainView = new DrawMathMainView(drawMain);
        drawMainView.setListener(this, this, this, this);

    }

    // 绘制到界面上
    private void paint() {
        drawMainView.paintImage(drawMain.getImage());
    }


    /**
     * 顶部菜单栏监听
     **/
    @Override
    public void onOpenDB() {
        new ImageSaveController();
    }

    @Override
    public void onCreateNew() {
        // 生成初始图片
        BufferedImage image = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        new controller.drawMainController.DrawMainController(image);
    }

    // 保存到数据库
    @Override
    public void onSaveDB() {
        BASE64Encoder encoder = new BASE64Encoder();
        String imageStr;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(drawMain.getImage(), "jpeg", baos);
            byte[] bytes = baos.toByteArray();
            imageStr = encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        entity.Image image = new entity.Image();
        image.setUserId(1);
        image.setTime(new Date());
        image.setImage(imageStr);
//        ImageMapper imageMapper = new ImageMapper();
//        if(imageMapper.insertImage(image) > 0){
//            drawMainView.showMessageDialog("保存成功！");
//        }else {
//            drawMainView.showMessageDialog("保存失败！");
//        }
    }

    // 保存为文件
    @Override
    public void onSaveFile() {

    }



    // 存为文件
    private void saveToFile(String fileName, String fileSrc) {
        try {
            ImageIO.write(drawMain.getImage(), "jpeg", new File(fileSrc + fileName + ".jpeg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //修改信息


    @Override
    public void onModify() {
        new UserModifyController();
    }

    /**
     * 顶部工具栏监听
     **/
    // 画笔颜色改变
    @Override
    public void onPenColorChanged(Color color) {
        drawMain.setPenColor(color);
    }

    // 画笔粗细改变
    @Override
    public void onLineStrockChanged(Float lineStroke) {
        drawMain.setLineStroke(lineStroke);
    }




    /**
     * 侧边工具栏监听
     **/
    // 鼠绘图模式改变
    @Override
    public void onModeChanged(DrawMode drawMode) {
        drawMain.setDrawMode(drawMode);


        switch (drawMode) {

            case CONE: {
                ConeDraw coneDraw = new ConeDraw();
                OffScreen3DImage offscreenTest = new OffScreen3DImage(new Color3f(drawMain.getPenColor()), coneDraw.draw());
                offscreenTest.setVisible(true);
                drawMain.setImage(offscreenTest.getDrawImage());
            }
            break;
            case BOX: {
                BoxDraw box = new BoxDraw();
                OffScreen3DImage offscreenTest = new OffScreen3DImage(new Color3f(drawMain.getPenColor()), box.draw());
                offscreenTest.setVisible(true);
                drawMain.setImage(offscreenTest.getDrawImage());
            }
            break;
            case SPHERE: {
                SphereThreeDDraw sphereDraw = new SphereThreeDDraw();
                OffScreen3DImage offscreenTest = new OffScreen3DImage(new Color3f(drawMain.getPenColor()), sphereDraw.draw());
                offscreenTest.setVisible(true);
                drawMain.setImage(offscreenTest.getDrawImage());
            }

            default:
                break;
        }
    }

    // 鼠标拖动
    @Override
    public void mouseDragged(entity.Point p) {
        switch (drawMain.getDrawMode()) {



            case FILL_RECTANGEL: {  // 绘制实心矩形
                FillRectangleDraw fillRectangleDraw = new FillRectangleDraw(drawMain.getPressedPoint(), p, drawMain.getPenColor(), drawMain.getLineStroke());
                BufferedImage image = fillRectangleDraw.draw(drawMain.getPressedImage());

            }
            break;
            case RECTANGEL: {    // 绘制矩形
                // 传入参数创建矩形控制类
                RectangleDraw rectangleController = new RectangleDraw(drawMain.getPressedPoint(), p, drawMain.getPenColor(), drawMain.getLineStroke());
                BufferedImage image = rectangleController.draw(drawMain.getGroundSizeX(), drawMain.getGroundSizeY());  // 绘制矩形到图像上
                   // 更新绘制图像
            }
            break;
            case CIRCLE: {   // 绘制圆形
                CircleDraw circleController = new CircleDraw(drawMain.getPressedPoint(), p, drawMain.getPenColor().getRGB(), drawMain.getLineStroke().intValue());
                BufferedImage image = circleController.draw(drawMain.getGroundSizeX(), drawMain.getGroundSizeY());
                drawMain.setImage(ImageUtil.imageCombine(drawMain.getPressedImage(), image));   // 更新绘制图像
            }
            break;
            case LINE: {    // 画直线
                LineDraw lineDraw = new LineDraw(drawMain.getPressedPoint(), p, drawMain.getPenColor(), drawMain.getLineStroke());
                BufferedImage image = lineDraw.draw(drawMain.getPressedImage());
                drawMain.setImage(image);   // 更新绘制图像
            }
            break;
            case TRIANGLE: {    // 画三角形
                TriangleDraw triangleController = new TriangleDraw(drawMain.getPressedPoint(), p, drawMain.getPenColor(), drawMain.getLineStroke());
                BufferedImage image = triangleController.draw(drawMain.getPressedImage());  // 绘制三角形到图像上
                drawMain.setImage(image);   // 更新绘制图像
            }
            break;
            case ROSE: { // 画玫瑰
                RoseDraw roseController = new RoseDraw();
                BufferedImage image = roseController.draw(drawMain.getPressedImage());
                drawMain.setImage(image);
            }
            break;
            default:
                break;
        }
        // 把更新后的图像绘制到界面上
        paint();
        // 记录当前坐标
        drawMain.setPreviousPoint(p);
    }

}
