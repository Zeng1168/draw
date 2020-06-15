package utils;


import javax.swing.*;
import java.awt.*;

/**
 * 绘图模式枚举类
 *
 * 各模式中文名称、模式下鼠标指针形状
 */
public enum DrawPlatformMode {

    MOUSE("鼠标", CursorTool.MOUSE_CURSOR, IconTool.point),
    RUBBER("橡皮", CursorTool.RUBBER_CURSOR, IconTool.rubber),
    PEN("钢笔", CursorTool.PEN_CURSOR, IconTool.line),
    LINE("直线", CursorTool.AIM_CURSOR, IconTool.straightLine),
    TRIANGLE("三角形", CursorTool.AIM_CURSOR, IconTool.trangle),
    RECTANGEL("矩形", CursorTool.AIM_CURSOR, IconTool.retangle),
    FILL_RECTANGEL("实心矩形", CursorTool.AIM_CURSOR, IconTool.fullTrangle),
    CIRCLE("圆形", CursorTool.AIM_CURSOR, IconTool.circle);

    // 鼠标形状
    static class CursorTool {
        public static Cursor MOUSE_CURSOR = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
        public static Cursor PEN_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/main/resources/image/pen2.png").getImage(), new Point(0, 30), "norm");
        public static Cursor RUBBER_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/main/resources/image/rubber.png").getImage(), new Point(0, 30), "norm");
        public static Cursor AIM_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/main/resources/image/aim.png").getImage(), new Point(17, 17), "norm");
    }

    static class IconTool {
        public static String point = "src/main/resources/image/mode_point_32.png";
        public static String rubber = "src/main/resources/image/mode_rubber_32.png";
        public static String line = "src/main/resources/image/mode_line_32.png";
        public static String straightLine = "src/main/resources/image/mode_straight_line_32.png";
        public static String trangle = "src/main/resources/image/mode_trangle_32.png";
        public static String retangle = "src/main/resources/image/mode_retangle_32.png";
        public static String fullTrangle = "src/main/resources/image/mode_full_trangle_32.png";
        public static String circle = "src/main/resources/image/mode_circle_32.png";
    }

    private String mode;
    private Cursor cursor;
    private String icon;

    DrawPlatformMode(String mode, Cursor cursor, String icon) {
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
    public static DrawPlatformMode getModeByStr(String str) {
        for (DrawPlatformMode dm : values()) {
            if (dm.getMode().equals(str)) {
                return dm;
            }
        }
        return null;
    }

}
