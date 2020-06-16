package view.drawMath;


import controller.drawMath.DrawMathController;
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
    private JPanel drawPanel;    // 中部画板


    public DrawMathView(){
        // 初始化各模块
        topMenu = new TopMenuBar();
        leftTool = new LeftToolBar();
        drawPanel = new JPanel();

        drawPanel.setLayout(new BorderLayout());
        drawPanel.add(new DrawTriangle(),BorderLayout.CENTER);


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





}
