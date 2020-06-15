package utils;

import javax.swing.*;
import java.awt.*;

public class WindowUtills {
    public static void setMiddleBounds(JFrame frame, int width, int height){
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        frame.setBounds((screenWidth-width)/2,(screenHeight-height)/2,width,height);	// 设置窗体位置和大小
    }
}
