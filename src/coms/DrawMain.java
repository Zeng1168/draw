package coms;

import javax.swing.*;
import java.awt.*;

/**
 * 主函数
 *
 * 创建窗体，把个模块布置到窗体中
 */
public class DrawMain extends JFrame {
    Params params;

    private TopMenu topMenu;    // 顶部菜单
    private TopTools topTools;  // 顶部工具条
    private LeftTools leftTools;    // 左侧工具条
    private DrawBroad drawBroad;    // 中部画板

    public DrawMain(){
        params = new Params();

        // 初始化各模块
        topMenu = new TopMenu();
        topTools = new TopTools(params);
        leftTools = new LeftTools();
        drawBroad = new DrawBroad(params);

        // 设置工具组件监听
        topMenu.setTopMenuListener(drawBroad);
        topTools.setTopToolListener(drawBroad);
        leftTools.setLeftToolListener(drawBroad);

        // 向主界面添加组件
        this.setJMenuBar(topMenu);	// 设置菜单栏
        this.add(topTools, BorderLayout.NORTH);	// 添加顶部工具栏
        this.add(leftTools,BorderLayout.WEST);    // 添加侧边工具栏
        this.add(drawBroad,BorderLayout.CENTER);	//添加绘图区容器


        // 设置
        this.setBounds(100,100,800,600);	// 设置窗体位置和大小
        this.setVisible(true);	// 设置窗体可见性
    }
}
