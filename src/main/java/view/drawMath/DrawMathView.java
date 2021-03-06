package view.drawMath;


import com.alibaba.fastjson.JSON;
import controller.drawMath.DrawMathController;
import entity.*;
import utils.DrawMathMode;
import utils.SwingUtills;

import javax.swing.*;
import java.awt.*;

/**
 * 主函数
 *
 * 创建窗体，把个模块布置到窗体中
 */
public class DrawMathView extends JFrame {
    private DrawMathController controller;  // 持有控制器
    private TopMenuBar topMenu;    // 顶部菜单
    private LeftToolBar leftTool;    // 左侧工具条
    private JPanel drawPanel;    //
    public IDraw drawComponent; // 用于数学绘图主界面操作绘图区组件


    public DrawMathView(){
        // 初始化各模块
        topMenu = new TopMenuBar();
        leftTool = new LeftToolBar();
        drawPanel = new JPanel();

        drawPanel.setLayout(new BorderLayout());

        // 添加一个默认模式
        DrawTriangle drawTriangle = new DrawTriangle();
        drawComponent = drawTriangle;
        drawPanel.add(drawTriangle,BorderLayout.CENTER);


        // 向主界面添加组件
        this.setJMenuBar(topMenu);	// 设置菜单栏
        this.setLayout(new BorderLayout());
        this.add(leftTool,BorderLayout.WEST);    // 添加侧边工具栏
        this.add(drawPanel,BorderLayout.CENTER);	//添加绘图区容器

        // 设置
        this.setTitle("ODS画板-数学绘图模式");
        SwingUtills.setMiddleBounds(this, 800, 660);
        this.setVisible(true);	// 设置窗体可见性
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        controller = new DrawMathController(this);
        // 设置监听
        topMenu.setTopMenuListener(controller);
        leftTool.setLeftToolListener(controller);
    }

    /** 切换绘图模式  */
    public void modeChanged(DrawMathMode drawMathMode){
        drawPanel.removeAll();  // 清除绘图区组件
        drawPanel.repaint();

        switch (drawMathMode){
            case TRIANGLE: {
                DrawTriangle drawTriangle = new DrawTriangle();
                drawComponent = drawTriangle;
                drawPanel.add(drawTriangle, BorderLayout.CENTER);
            } break;
            case CONE:{
                DrawCone drawCone = new DrawCone();
                drawComponent = drawCone;
                drawPanel.add(drawCone, BorderLayout.CENTER);
            }break;
            case CIRCLE:{
                DrawCircle drawCircle=new DrawCircle();
                drawComponent=drawCircle;
                drawPanel.add(drawCircle,BorderLayout.CENTER);
            }break;
            case RETANGLE:{
                DrawRectangle drawRectangle=new DrawRectangle();
                drawComponent = drawRectangle;
                drawPanel.add(drawRectangle,BorderLayout.CENTER);
            }break;
            case SPHERE:{
                DrawSphere drawSphere=new DrawSphere();
                drawComponent=drawSphere;
                drawPanel.add(drawSphere,BorderLayout.CENTER);
            }break;
            case CUBOID:{
                DrawBox drawBox=new DrawBox();
                drawComponent=drawBox;
                drawPanel.add(drawBox,BorderLayout.CENTER);
            }break;
            case ROSE:{
                DrawRose drawRose=new DrawRose();
                drawComponent=drawRose;
                drawPanel.add(drawRose,BorderLayout.CENTER);

            }break;
        }

        drawPanel.revalidate();
        System.out.println(drawMathMode.getMode());
    }

    public void dataLoading(DrawMathMode drawMathMode, String jsonStr){
        drawPanel.removeAll();  // 清除绘图区组件
        drawPanel.repaint();

        System.out.println("加载网络数据");

        switch (drawMathMode) {
            case TRIANGLE: {
                ShapeTriangle shapeTriangle = JSON.parseObject(jsonStr, ShapeTriangle.class);
                DrawTriangle drawTriangle = new DrawTriangle(shapeTriangle);
                drawComponent = drawTriangle;
                drawPanel.add(drawTriangle, BorderLayout.CENTER);
            }
            break;
            case CONE: {
                ShapeCone shapeCone = JSON.parseObject(jsonStr, ShapeCone.class);
                DrawCone drawCone = new DrawCone(shapeCone);
                drawComponent = drawCone;
                drawPanel.add(drawCone, BorderLayout.CENTER);
            }
            break;
            case CIRCLE: {
                ShapeCircle shapeCircle = JSON.parseObject(jsonStr, ShapeCircle.class);
                DrawCircle drawCircle = new DrawCircle(shapeCircle);
                drawComponent = drawCircle;
                drawPanel.add(drawCircle, BorderLayout.CENTER);
            }
            break;
            case RETANGLE: {
                ShapeRectangle shapeRectangle = JSON.parseObject(jsonStr, ShapeRectangle.class);
                DrawRectangle drawRectangle = new DrawRectangle(shapeRectangle);
                drawComponent = drawRectangle;
                drawPanel.add(drawRectangle, BorderLayout.CENTER);
            }
            break;
            case SPHERE: {
                ShapeSphere shapeSphere = JSON.parseObject(jsonStr, ShapeSphere.class);
                DrawSphere drawSphere = new DrawSphere(shapeSphere);
                drawComponent = drawSphere;
                drawPanel.add(drawSphere, BorderLayout.CENTER);
            }
            break;
            case CUBOID: {
                ShapeBox shapeBox = JSON.parseObject(jsonStr, ShapeBox.class);
                DrawBox drawBox = new DrawBox(shapeBox);
                drawComponent = drawBox;
                drawPanel.add(drawBox, BorderLayout.CENTER);
            }
        }
    }
}
