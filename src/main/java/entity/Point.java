package entity;

/**
 * 鼠标坐标
 */
public class Point {
    public int x;
    public int y;

    public Point(){
        this.setPoint(0,0);
    }

    public Point(int x, int y) {
        this.setPoint(x, y);
    }

    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
