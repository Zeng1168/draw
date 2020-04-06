package entity;


import java.awt.*;

public class Triangle {
    private Integer x1,x2;  // 位置坐标x1,x2
    private Integer y1,y2;  // 位置坐标y1,y2
 
    private Color color;    // 颜色
    private BasicStroke stroke; // 厚度

  
    public Integer getX1() {
		return x1;
	}

	public void setX1(Integer x1) {
		this.x1 = x1;
	}

	public Integer getX2() {
		return x2;
	}

	public void setX2(Integer x2) {
		this.x2 = x2;
	}
	public Integer getY1() {
		return y1;
	}

	public void setY1(Integer y1) {
		this.y1 = y1;
	}

	public Integer getY2() {
		return y2;
	}

	public void setY2(Integer y2) {
		this.y2 = y2;
	}

	

	public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BasicStroke getStroke() {
        return stroke;
    }

    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }
}

