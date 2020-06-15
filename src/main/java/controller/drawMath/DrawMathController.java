package controller.drawMath;

import utils.DrawMathMode;
import view.drawMath.DrawMathView;
import view.drawMath.LeftToolBar;
import view.drawMath.TopMenuBar;
import view.drawPlatform.DrawPlatformView;


public class DrawMathController implements TopMenuBar.TopMenuListener, LeftToolBar.LeftMathToolListener  {
    DrawMathView drawMathView;  // JFram
    DrawMathMode mode;

    public DrawMathController(DrawMathView drawMathView) {
        this.drawMathView = drawMathView;
    }

    @Override
    public void onModeChanged(DrawMathMode drawMathMode) {
        this.mode = drawMathMode;
    }

    @Override
    public void onMenuItemClick(String command) {

        switch (command){
            case "用户-修改密码" : {
//                new PasswordModifyController();
            }break;
            case "文件-打开数据库文件" : {
//                new ImageSaveController();
            }break;
            case "文件-新建" : {
                new DrawMathView();
            }break;
            case "文件-保存到数据库" : {
//                saveToDB();
            }break;
            case "文件-保存到文件" : {

            }break;
            case "画板模式-平面绘图模式" : {
                new DrawPlatformView();
            }break;
            default:break;
        }
    }
}
