package controller.drawMain;

import entity.ConeThreeD;
import view.ConeView;

public class ConeController implements ConeView.ConeController{
    private ConeView vcone;

    private  double area,volume;
    public ConeController() {
        vcone=new ConeView();
        vcone.setConeListener( this);
    }

    @Override
    public void paint(String r, String h) {
        if(r == null || r.equals("")){
            vcone.showMessageDialog("半径不能为空！");
            vcone.focusrInput();
        }else if(h == null || h.equals("")){
            vcone.showMessageDialog("高度不能为空！");
            vcone.focushInput();
        }else {
            ConeThreeD cone3d =new ConeThreeD();
            cone3d.setR(Float.parseFloat(r));
            cone3d.setH(Float.parseFloat(h));
            area= cone3d.getArea();
            volume= cone3d.getVolume();
            vcone.setproperty(area,volume);
        }

    }
//    public static void main(String[] args) {
//        new ConeController();
//    }


}
