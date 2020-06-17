package utils;


import javax.swing.*;
import java.awt.*;

/**
 * 绘图模式枚举类
 *
 * 各模式中文名称、模式下鼠标指针形状
 */
public enum DrawMathMode {

    RETANGLE("矩形", CursorTool.MOUSE_CURSOR, IconTool.retangle),
    TRIANGLE("三角形", CursorTool.RUBBER_CURSOR, IconTool.trangle),
    CIRCLE("圆形", CursorTool.PEN_CURSOR, IconTool.circle),
    ROSE("玫瑰", CursorTool.AIM_CURSOR, IconTool.rose),
    CONE("圆锥", CursorTool.AIM_CURSOR, IconTool.cone),
    SPHERE("球体", CursorTool.AIM_CURSOR, IconTool.sphere),
    CUBOID("长方体", CursorTool.AIM_CURSOR, IconTool.cubiod);

    // 鼠标形状
    static class CursorTool {
        public static Cursor MOUSE_CURSOR = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
        public static Cursor PEN_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/main/resources/image/pen2.png").getImage(), new Point(0, 30), "norm");
        public static Cursor RUBBER_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/main/resources/image/rubber.png").getImage(), new Point(0, 30), "norm");
        public static Cursor AIM_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/main/resources/image/aim.png").getImage(), new Point(17, 17), "norm");
    }

    static class IconTool {
        public static String retangle = "src/main/resources/image/mode_retangle_32.png";
        public static String trangle = "src/main/resources/image/mode_trangle_32.png";
        public static String circle = "src/main/resources/image/mode_circle_32.png";
        public static String rose = "src/main/resources/image/mode_rose_32.png";
        public static String cone = "src/main/resources/image/mode_cone_32.png";
        public static String sphere = "src/main/resources/image/mode_sphere_32.png";
        public static String cubiod = "src/main/resources/image/mode_cuboid_32.png";
    }

    private String mode;
    private Cursor cursor;
    private String icon;

    DrawMathMode(String mode, Cursor cursor, String icon) {
        this.mode = mode;
        this.cursor = cursor;
        this.icon = icon;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
