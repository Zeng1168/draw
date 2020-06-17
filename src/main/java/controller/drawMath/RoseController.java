package controller.drawMath;

import entity.ShapeRose;
import view.drawMath.DrawRose;

public class RoseController implements DrawRose.Listener{
    private DrawRose drawRose;
    private ShapeRose shapeRose;

    public RoseController(DrawRose drawRose){this.drawRose=drawRose;}

    @Override
    public void onDraw(int s) {
        shapeRose=new ShapeRose();
        shapeRose.setName("玫瑰花");
        shapeRose.setSize(s);
        drawRose.drawShape(shapeRose);
        System.out.println(shapeRose.toString());
        drawRose.updateInfoArea(shapeRose);

    }
}
