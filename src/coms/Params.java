package coms;

import java.awt.*;

/**
 * 绘图参数
 */
public class Params {
    private DrawMode drawMode;  // 绘图模式
    private Float lineStroke;   // 线条粗细
    private BasicStroke penStroke;
    private Color penColor; // 画笔颜色
    private Color backgroundColor;  // 背景颜色
    private Integer groundSizeX;    // 画布宽度
    private Integer groundSizeY;    // 画布高度
    private Integer rubberSize;     // 橡皮半径

    public Params(DrawMode mode, Float lineStroke, Color penColor, Color backgroundColor, Integer groundSizeX, Integer groundSizeY, Integer rubberSize) {
        this.drawMode = mode;
        this.lineStroke = lineStroke;
        this.penStroke=penStroke;
        this.penColor = penColor;
        this.backgroundColor = backgroundColor;
        this.groundSizeX = groundSizeX;
        this.groundSizeY = groundSizeY;
        this.rubberSize = rubberSize;
    }

    public Params() {
        this(DrawMode.MOUSE,Float.valueOf(2f),  Color.BLUE, Color.WHITE, 640, 480, 20);
    }


    public DrawMode getDrawMode() {
        return drawMode;
    }

    public void setDrawMode(DrawMode drawMode) {
        this.drawMode = drawMode;
    }

    public Float getLineStroke() {
        return lineStroke;
    }

    public void setLineStroke(Float lineStroke) {
        this.lineStroke = lineStroke;
       // penStroke=new BasicStroke(lineStroke);
    }

    public Color getPenColor() {
        return penColor;
    }

    public void setPenColor(Color penColor) {
        this.penColor = penColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Integer getGroundSizeX() {
        return groundSizeX;
    }

    public Integer getGroundSizeY() {
        return groundSizeY;
    }

    public void setGroundSize(Integer groundSizeX, Integer groundSizeY) {
        this.groundSizeX = groundSizeX;
        this.groundSizeY = groundSizeY;
    }

    public Integer getRubberSize() {
        return rubberSize;
    }

    public void setRubberSize(Integer rubberSize) {
        this.rubberSize = rubberSize;
    }
}
