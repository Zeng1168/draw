package utils;

import javax.swing.*;
import java.awt.*;

public enum DrawMathMode{


    LINE("直线"),
    TRIANGLE("三角形"),
    RECTANGEL("矩形"),
    FILL_RECTANGEL("实心矩形"),
    CIRCLE("圆形"),
    ROSE("玫瑰"),
    CONE("圆锥"),
    SPHERE("球体"),
    BOX("长方体");


    private String mode;

    DrawMathMode(String mode) {
        this.mode = mode;

    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }



    // 根据字符串获取对应enum
    public static DrawMathMode getModeByStr(String str) {
        for (DrawMathMode dm : values()) {
            if (dm.getMode().equals(str)) {
                return dm;
            }
        }
        return null;
    }







}
