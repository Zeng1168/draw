package view.drawMath;


import view.drawMainView.TopMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * 主函数
 *
 * 创建窗体，把个模块布置到窗体中
 */
public class DrawMathView extends JFrame {
    private TopMenuBar topMenu;    // 顶部菜单
    private TopMathToolBar topTool;  // 顶部工具条
    private LeftMathToolBar leftTool;    // 左侧工具条
    private MathDrawBroad mathDrawBroad;    // 中部画板
    private InfoView infoView;//右侧信息栏


    public DrawMathView(){
        // 初始化各模块
        topMenu = new TopMenuBar();
        topTool = new TopMathToolBar();
        leftTool = new LeftMathToolBar();
        mathDrawBroad =new MathDrawBroad();
        infoView =new InfoView();

        // 向主界面添加组件
        this.setJMenuBar(topMenu);	// 设置菜单栏

        this.add(infoView,BorderLayout.EAST);
        this.add(topTool,BorderLayout.NORTH);	// 添加顶部工具栏
        this.add(leftTool,BorderLayout.WEST);    // 添加侧边工具栏
        this.add(mathDrawBroad,BorderLayout.CENTER);	//添加绘图区容器

        // 设置
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds((screenWidth-800)/2,(screenHeight-600)/2,800,600);	// 设置窗体位置和大小
        this.setVisible(true);	// 设置窗体可见性
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void refrashInfo(Map<String, String> map){
        infoView.refrashInfo(map);
    }

    // 绘制图像到画板
    public void paintImage(BufferedImage image){
        mathDrawBroad.paintImage(image);
    }


    public void setListener(TopMenuBar.TopMenuListener topMenuListener,
                            TopMathToolBar.TopMathToolListener topToolListener,
                            LeftMathToolBar.LeftMathToolListener leftToolListener){
        topMenu.setTopMenuListener(topMenuListener);
        topTool.setTopMathToolListener(topToolListener);
        leftTool.setLeftToolListener(leftToolListener);

    }
}
