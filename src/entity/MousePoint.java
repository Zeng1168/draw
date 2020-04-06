package entity;

public class MousePoint {
    private int X;
    private int Y;

    public MousePoint(int x, int y) {
        this.setPoint(x, y);
    }

    public void setPoint(int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
