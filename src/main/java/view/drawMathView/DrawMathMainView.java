package view.drawMathView;


import entity.DrawMain;
import entity.DrawMathMain;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import view.drawMainView.*;

import javax.media.j3d.BranchGroup;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 主函数
 *
 * 创建窗体，把个模块布置到窗体中
 */
public class DrawMathMainView extends JFrame {
    private TopMathMenuBar topMenu;    // 顶部菜单
    private TopMathToolBar topTool;  // 顶部工具条
    private LeftMathToolBar leftTool;    // 左侧工具条
    private DrawMathBroad drawMathBroad;    // 中部画板
    private InforView inforView;//右侧信息栏

    BranchGroup drawGroup = null;

    public DrawMathMainView(DrawMathMain drawMathMain){
        // 初始化各模块
        topMenu = new TopMathMenuBar();
        topTool = new TopMathToolBar(drawMathMain.getPenColor());
        leftTool = new LeftMathToolBar();
        drawMathBroad=new DrawMathBroad();
        inforView=new InforView();

       // drawBroad.setDrawGroup(this.getDrawGroup());

        // 向主界面添加组件
        this.setJMenuBar(topMenu);	// 设置菜单栏

        this.add(inforView,BorderLayout.EAST);
    //    this.add(topTool,BorderLayout.NORTH);	// 添加顶部工具栏
        this.add(leftTool,BorderLayout.WEST);    // 添加侧边工具栏
        this.add(drawMathBroad,BorderLayout.CENTER);	//添加绘图区容器

        this.validate();
        this.repaint();
        // 设置
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds((screenWidth-800)/2,(screenHeight-600)/2,800,600);	// 设置窗体位置和大小
        this.setVisible(true);	// 设置窗体可见性
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    public void setListener(TopMathMenuBar.TopMathMenuListener topMenuListener,
                            TopMathToolBar.TopMathToolListener topToolListener,
                            LeftMathToolBar.LeftMathToolListener leftToolListener){
        topMenu.setTopMathMenuListener(topMenuListener);
        topTool.setTopMathToolListener(topToolListener);
        leftTool.setLeftToolListener(leftToolListener);

    }
}
