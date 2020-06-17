package controller.drawMath;

import utils.DrawMathMode;
import view.DataQueryView;
import view.PasswordModifyView;
import view.drawMath.DrawMathView;
import view.drawMath.LeftToolBar;
import view.drawMath.TopMenuBar;
import view.drawPlatform.DrawPlatformView;

import javax.swing.*;


public class DrawMathController implements TopMenuBar.TopMenuListener, LeftToolBar.LeftMathToolListener  {
    DrawMathView drawMathView;  // JFram
    DrawMathMode mode;

    public DrawMathController(DrawMathView drawMathView) {
        this.mode = DrawMathMode.TRIANGLE;
        this.drawMathView = drawMathView;
    }

    @Override
    public void onModeChanged(DrawMathMode drawMathMode) {
        this.mode = drawMathMode;
        drawMathView.modeChanged(drawMathMode);
    }

    @Override
    public void onMenuItemClick(String command) {

        switch (command){
            case "用户-修改密码" : {
                new PasswordModifyView();
            }break;
            case "用户-注销" : {
                drawMathView.dispose();
            }break;
            case "文件-打开数据库文件" : {
                new DataQueryView(mode);
            }break;
            case "文件-新建" : {
                new DrawMathView();
            }break;
            case "文件-保存到数据库" : {
                drawMathView.drawComponent.saveToDataBase();
            }break;
            case "文件-保存到文件" : {
                drawMathView.drawComponent.saveToFile();
            }break;
            case "编辑-清空输入" : {
                drawMathView.drawComponent.clean();
            }break;
            case "画板模式-平面绘图模式" : {
                int r= JOptionPane.showConfirmDialog(drawMathView,"是否关闭当前窗口？","是否关闭",JOptionPane.YES_NO_CANCEL_OPTION);
                if(r==JOptionPane.YES_OPTION){
                    drawMathView.dispose();
                    new DrawPlatformView();
                }else if(r != JOptionPane.CANCEL_OPTION){
                    new DrawPlatformView();
                }
            }break;
            default:break;
        }
    }
}
