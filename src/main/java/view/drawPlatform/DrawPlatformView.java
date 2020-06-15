package view.drawPlatform;

import controller.DrawPlatformController;
import entity.module.DrawParams;
import utils.ImageUtil;
import utils.WindowUtills;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 主函数
 *
 * 创建窗体，把个模块布置到窗体中
 */
public class DrawPlatformView extends JFrame {
    private DrawPlatformController controller;  // 持有控制器


    private TopMenuBar topMenu;    // 顶部菜单
    private TopToolBar topTool;  // 顶部工具条
    private LeftToolBar leftTool;    // 左侧工具条
    private DrawBroadPanel drawBroad;    // 中部画板

    public DrawPlatformView(BufferedImage image) {
        this.init(image);
    }

    public DrawPlatformView(){
        this.init(null);
    }

    public void init(BufferedImage image){


        // 初始化各模块
        topMenu = new TopMenuBar();
        topTool = new TopToolBar();
        leftTool = new LeftToolBar();
        drawBroad = new DrawBroadPanel();


        // 向主界面添加组件
        this.setJMenuBar(topMenu);	// 设置菜单栏
        this.add(topTool,BorderLayout.NORTH);	// 添加顶部工具栏
        this.add(leftTool,BorderLayout.WEST);    // 添加侧边工具栏
        this.add(drawBroad,BorderLayout.CENTER);	//添加绘图区容器

        // 设置窗口
        this.setIconImage((new ImageIcon("src/main/resources/image/iconfont.png").getImage()));
        WindowUtills.setMiddleBounds(this, 800, 600);   // 设置窗口大小
        this.setVisible(true);	// 设置窗体可见性


        controller = new DrawPlatformController(this, image);
        // 设置监听
        topMenu.setTopMenuListener(controller);
        topTool.setTopToolListener(controller);
        leftTool.setLeftToolListener(controller);
        drawBroad.setDrawBroadListener(controller);
    }




    // 绘制图像到画板
    public void paintImage(BufferedImage image){
        drawBroad.paintImage(image);
    }

    // 改变绘图区鼠标形状
    public void setDrawBroadCursor(Cursor cursor){
        drawBroad.setCursor(cursor);
    }

    public void initTopToolParams(Color penColor, Color backColor, int width, int height){
        topTool.initParams(penColor, backColor, width, height);
    }

}
