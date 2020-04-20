package controller.drawMainController;

import controller.ImageSaveController;

import controller.OffScreen3DImage;
import controller.UserModifyController;
import entity.*;
import entity.Image;
import sun.misc.BASE64Encoder;
import utils.ImageUtil;
import view.drawMainView.*;

import javax.imageio.ImageIO;
import javax.media.j3d.BranchGroup;
import javax.vecmath.Color3f;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class DrawMainController implements TopMenuBar.TopMenuListener, TopToolBar.TopToolListener, LeftToolBar.LeftToolListener, DrawBroadPanel.DrawBroadListener {
    DrawMainView drawMainView;//JFram

    DrawMain drawMain;//参数

    BranchGroup group;

    public DrawMainController() {
        // 生成初始图片
        BufferedImage image = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,image.getWidth(), image.getHeight());
        this.init(image);
    }

    public DrawMainController(BufferedImage image) {
        this.init(image);
    }

    public void init(BufferedImage image){
        // 设置初始参数
        drawMain = new DrawMain();
        drawMain.setImage(image);
        drawMain.setDrawMode(DrawMode.MOUSE);
        drawMain.setLineStroke(new BasicStroke(2));
        drawMain.setPenColor(Color.BLUE);
        drawMain.setBackgroundColor(Color.WHITE);
        drawMain.setGroundSizeX(image.getWidth());
        drawMain.setGroundSizeY(image.getHeight());
        drawMain.setRubberSize(20);

        // 绑定view层
        drawMainView = new DrawMainView(drawMain);
        drawMainView.setListener(this, this, this, this);

        group=new BranchGroup();

    }

    public BranchGroup getGroup() {
        return group;
    }

    public void setGroup(BranchGroup group) {
        this.group = group;
    }

    // 绘制到界面上
    private void paint(){
        drawMainView.paintImage(drawMain.getImage());
    }


    /**  顶部菜单栏监听   **/
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
        g.fillRect(0,0,image.getWidth(), image.getHeight());

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

        Image image = new Image();
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

    // 撤销
    @Override
    public void onCancleEdit() {
        Object image = drawMain.popRecordStack(); // 从历史记录栈取出
        if( image != null){
            drawMain.pushCancelStack(drawMain.getImage());    // 把当前图片放入撤销记录栈
            drawMain.setImage((BufferedImage)image);
            paint();
        }
    }

    // 重做
    @Override
    public void onRedoEdit() {
        Object image = drawMain.popCancelStack();
        if( image != null){
            drawMain.pushRecordStack(drawMain.getImage());
            drawMain.setImage((BufferedImage)image);
            paint();
        }
    }

    //清空
    @Override
    public void onClearEdit() {
        drawMain.setBackgroundColor(Color.WHITE);
        Graphics g = drawMain.getImage().getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, drawMain.getGroundSizeX(), drawMain.getGroundSizeY());
        paint();
    }

    // 存为文件
    private void saveToFile(String fileName,String fileSrc) {
        try {
            ImageIO.write(drawMain.getImage(), "jpeg", new File(fileSrc+fileName+".jpeg"));
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

    /**  顶部工具栏监听  **/
    // 画笔颜色改变
    @Override
    public void onPenColorChanged(Color color) {
        drawMain.setPenColor(color);
    }

    // 画布颜色改变
    @Override
    public void onBackColorChanged(Color color) {
        drawMain.setBackgroundColor(color);
        Graphics g = drawMain.getImage().getGraphics();
        g.setColor(color);
        g.fillRect(0,0,this.drawMain.getGroundSizeX(),drawMain.getGroundSizeY());
        paint();
    }

    // 画笔粗细改变
    @Override
    public void onLineStrockChanged(Float lineStroke) {
        drawMain.setLineStroke(new BasicStroke(lineStroke));
    }

    // 画布尺寸改变
    @Override
    public void onSizeChanged(Integer x, Integer y) {
        // 保留图像历史记录
        drawMain.pushRecordStack(ImageUtil.imageCopy(drawMain.getImage()));

        // 创建一个新的image对象，把原来的image对象绘制到上面
        BufferedImage image = new BufferedImage(x,y,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(drawMain.getBackgroundColor());
        g.fillRect(0,0,x, y);
        g.setColor(drawMain.getPenColor());
        g.drawImage(drawMain.getImage(), 0, 0, drawMain.getGroundSizeX(), drawMain.getGroundSizeY(),null);

        // 改变存储参数
        drawMain.setGroundSizeX(x);
        drawMain.setGroundSizeY(y);
        drawMain.setImage(image);

        // 把改变后的图像绘制到界面上
        paint();
    }


    /**  侧边工具栏监听  **/
    // 鼠绘图模式改变
    @Override
    public void onModeChanged(DrawMode drawMode) {
        drawMain.setDrawMode(drawMode);
        drawMainView.setDrawBroadCursor(drawMode.getCursor());   // 改变鼠标形状

        switch (drawMode){

            case CONE: {
                ConeDraw coneDraw=new ConeDraw();
                OffScreen3DImage offscreenTest=new OffScreen3DImage(new Color3f(drawMain.getPenColor()),coneDraw.draw());
                offscreenTest.setVisible(true);
                drawMain.setImage(offscreenTest.getDrawImage());
            } break;
            case BOX:{
                BoxDraw box=new BoxDraw();
                OffScreen3DImage offscreenTest=new OffScreen3DImage(new Color3f(drawMain.getPenColor()),box.draw());
                offscreenTest.setVisible(true);
                drawMain.setImage(offscreenTest.getDrawImage());
            }break;
            case SPHERE:{
                SphereDraw sphereDraw=new SphereDraw();
                OffScreen3DImage offscreenTest=new OffScreen3DImage(new Color3f(drawMain.getPenColor()),sphereDraw.draw());
                offscreenTest.setVisible(true);
                drawMain.setImage(offscreenTest.getDrawImage());
            }

            default: break;
        }
    }

    // 鼠标拖动
    @Override
    public void mouseDragged(MousePoint p) {
        switch(drawMain.getDrawMode()) {
            case PEN :{ // 钢笔绘制
                if(drawMain.getPreviousPoint() != null) {
                    Graphics g = drawMain.getImage().getGraphics();
                    g.setColor(drawMain.getPenColor());
                    g.drawLine(drawMain.getPreviousPoint().getX(), drawMain.getPreviousPoint().getY(), p.getX(), p.getY());
                }
            }break;
            case RUBBER :{  // 橡皮擦
                Graphics g = drawMain.getImage().getGraphics();
                g.setColor(drawMain.getBackgroundColor());
                Integer radius = drawMain.getRubberSize();  // 橡皮擦半径
                g.fillOval(p.getX() - (radius/2),p.getY() - (radius/2), radius, radius);
            }break;
            case FILL_RECTANGEL: {  // 绘制实心矩形
                FillRectangleDraw fillRectangleDraw = new FillRectangleDraw(drawMain.getPressedPoint(), p, drawMain.getPenColor(), drawMain.getLineStroke());
                BufferedImage image = fillRectangleDraw.draw(drawMain.getPressedImage());
                drawMain.setImage(image);   // 更新绘制图像
            }break;
            case RECTANGEL:{    // 绘制矩形
                // 传入参数创建矩形控制类
                RectangleDraw rectangleController = new RectangleDraw(drawMain.getPressedPoint(), p, drawMain.getPenColor(), drawMain.getLineStroke());
                BufferedImage image = rectangleController.draw(drawMain.getGroundSizeX(), drawMain.getGroundSizeY());  // 绘制矩形到图像上
                drawMain.setImage(ImageUtil.imageCombine(drawMain.getPressedImage(), image));   // 更新绘制图像
            }break;
            case CIRCLE:{   // 绘制圆形
                CircleDraw circleController=new CircleDraw(drawMain.getPressedPoint(), p, drawMain.getPenColor(), drawMain.getLineStroke());
                BufferedImage image=circleController.draw(drawMain.getPressedImage());
                drawMain.setImage(image);   // 更新绘制图像
            }break;
            case LINE :{    // 画直线
                LineDraw lineDraw =new LineDraw(drawMain.getPressedPoint(), p, drawMain.getPenColor(), drawMain.getLineStroke());
                BufferedImage image=lineDraw.draw(drawMain.getPressedImage());
                drawMain.setImage(image);   // 更新绘制图像
            }break;
            case TRIANGLE :{    // 画三角形
                TriangleDraw triangleController= new TriangleDraw(drawMain.getPressedPoint(), p, drawMain.getPenColor(), drawMain.getLineStroke());
                BufferedImage image = triangleController.draw(drawMain.getPressedImage());  // 绘制三角形到图像上
                drawMain.setImage(image);   // 更新绘制图像
            }break;
            case ROSE:{ // 画玫瑰
                RoseDraw roseController=new RoseDraw();
                BufferedImage image=roseController.draw(drawMain.getPressedImage());
                drawMain.setImage(image);
            }break;
            default : break;
        }
        // 把更新后的图像绘制到界面上
        paint();
        // 记录当前坐标
        drawMain.setPreviousPoint(p);
    }

    // 鼠标移动
    @Override
    public void mouseMoved(MousePoint p) { }

    // 鼠标点击
    @Override
    public void mouseClicked(MousePoint p) { }

    // 鼠标按下
    @Override
    public void mousePressed(MousePoint p) {
        // 记录鼠标按下时的点和图像
        drawMain.setPreviousPoint(p);
        drawMain.setPressedPoint(p);
        drawMain.setPressedImage(drawMain.getImage());
        // 按下就一定会产生图像绘制，此时先保存记录用以撤销
        if(drawMain.getDrawMode() != DrawMode.MOUSE){
            drawMain.pushRecordStack(ImageUtil.imageCopy(drawMain.getImage()));
        }
    }

    // 鼠标松开
    @Override
    public void mouseReleased(MousePoint p) {
        // 清除鼠标和图像绘制记录
        drawMain.setPreviousPoint(null);
        drawMain.setPreviousImage(null);
        drawMain.setPressedPoint(null);
        drawMain.setPressedImage(null);
    }

    // 鼠标进入绘图区
    @Override
    public void mouseEntered(MousePoint p) { }

    // 鼠标离开绘图区
    @Override
    public void mouseExited(MousePoint p) { }
}
