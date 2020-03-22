package coms;

import javax.swing.*;
import java.awt.*;

/**
 * 绘图模式枚举类
 *
 * 各模式中文名称、模式下鼠标指针形状
 */
public enum DrawMode {

    MOUSE("  鼠标  ", CursorTool.MOUSE_CURSOR),
    PEN("  钢笔  ", CursorTool.PEN_CURSOR),
    RUBBER("  橡皮  ", CursorTool.RUBBER_CURSOR),
    LINE("  直线  ", CursorTool.AIM_CURSOR),
    TRIANGLE(" 三角形 ", CursorTool.AIM_CURSOR),
    RECTANGEL("  矩形  ", CursorTool.AIM_CURSOR),
    FILL_RECTANGEL("实心矩形", CursorTool.AIM_CURSOR),
    CIRCLE("  圆形  ", CursorTool.AIM_CURSOR);

    // 鼠标形状
    static class CursorTool {
        public static Cursor MOUSE_CURSOR = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
        public static Cursor PEN_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("img_source/pen2.png").getImage(), new Point(0, 30), "norm");
        public static Cursor RUBBER_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("img_source/rubber.png").getImage(), new Point(0, 30), "norm");
        public static Cursor AIM_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("img_source/aim.png").getImage(), new Point(17, 17), "norm");
    }

    private String mode;
    private Cursor cursor;

    DrawMode(String mode, Cursor cursor) {
        this.mode = mode;
        this.cursor = cursor;
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

    // 根据字符串获取对应enum
    public static DrawMode getModeByStr(String str) {
        for (DrawMode dm : values()) {
            if (dm.getMode().equals(str)) {
                return dm;
            }
        }
        return null;
    }

}
