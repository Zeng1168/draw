package view.drawMainView;


import entity.DrawMain;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import javax.media.j3d.BranchGroup;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 主函数
 *
 * 创建窗体，把个模块布置到窗体中
 */
public class DrawMainView extends JFrame {
    private TopMenuBar topMenu;    // 顶部菜单
    private MenuController topMenu2;
    private TopToolBar topTool;  // 顶部工具条
    private LeftToolBar leftTool;    // 左侧工具条
    //private DrawBroadPanel2 drawBroad;    // 中部画板
    private DraBroadPanel2 drawBroad;
    private InforView inforView;//右侧信息栏
    JFXPanel jfxPanel;
    BranchGroup drawGroup = null;

    public DrawMainView(DrawMain drawMain){
        // 初始化各模块
        topMenu = new TopMenuBar();
        topMenu2=new MenuController();
        topTool = new TopToolBar(drawMain.getPenColor(), drawMain.getBackgroundColor(), drawMain.getGroundSizeX(), drawMain.getGroundSizeY());
        leftTool = new LeftToolBar();
       // drawBroad = new DrawBroadPanel2(drawMain.getImage());
        drawBroad=new DraBroadPanel2(drawMain.getImage());
        inforView=new InforView();
        jfxPanel=new JFXPanel();

        drawBroad.setDrawGroup(this.getDrawGroup());



        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    topMenu2.start(new Stage());

                    jfxPanel.setScene(topMenu2.getScene());
                }catch (Exception e){

                }
            }
        });

        // 向主界面添加组件
        this.setJMenuBar(topMenu);	// 设置菜单栏
        //this.add(jfxPanel,BorderLayout.EAST);
        this.add(inforView,BorderLayout.EAST);
        this.add(topTool,BorderLayout.NORTH);	// 添加顶部工具栏
        this.add(leftTool,BorderLayout.WEST);    // 添加侧边工具栏
        this.add(drawBroad,BorderLayout.CENTER);	//添加绘图区容器

        // 设置
        this.setBounds(100,100,800,600);	// 设置窗体位置和大小
        this.setVisible(true);	// 设置窗体可见性
    }

    public void showMessageDialog(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }

    public BranchGroup getDrawGroup() {
        return drawGroup;
    }

    public void setDrawGroup(BranchGroup drawGroup) {
        this.drawGroup = drawGroup;
    }

    // 绘制图像到画板
    public void paintImage(BufferedImage image){
        drawBroad.paintImage(image);
    }

    // 改变绘图区鼠标形状
    public void setDrawBroadCursor(Cursor cursor){
        drawBroad.setCursor(cursor);
    }

    public void setListener(TopMenuBar.TopMenuListener topMenuListener,
                            TopToolBar.TopToolListener topToolListener,
                            LeftToolBar.LeftToolListener leftToolListener,
                            DrawBroadPanel.DrawBroadListener drawBroadListener){
        topMenu.setTopMenuListener(topMenuListener);
        topTool.setTopToolListener(topToolListener);
        leftTool.setLeftToolListener(leftToolListener);
        drawBroad.setDrawBroadListener(drawBroadListener);
    }
}
