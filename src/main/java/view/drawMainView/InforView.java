package view.drawMainView;

import entity.DrawMode;

import javax.swing.*;
import java.awt.*;

public class InforView extends JPanel implements LeftToolBar.LeftToolListener {

    InfoViewListener listener;
    JLabel object;
    JLabel objectContent;
    /** 周长 **/
    JLabel perimeter;
    JLabel perimeterContent;
    /**面积**/
    JLabel area;
    JLabel areaContent;
    /**体积**/
    JLabel volumn;
    JLabel volumnContent;
    /**位置信息**/
    JLabel position;
    JLabel positionContent;

    Box boxh1,boxh2;
    Box boxv1,boxv2;

    public InforView() {
        object=new JLabel("图形:");
        objectContent=new JLabel("选择模式");
        perimeter=new JLabel("周长:");
        perimeterContent=new JLabel();
        area=new JLabel("面积:");
        areaContent=new JLabel();
        volumn=new JLabel("体积:");
        volumnContent=new JLabel();
        position=new JLabel("位置:");
        perimeterContent=new JLabel();
        boxh1=Box.createHorizontalBox();
        boxh2= Box.createHorizontalBox();
        boxv1=Box.createVerticalBox();
        boxv2=Box.createVerticalBox();
        boxv1.add(object);
        boxv2.add(objectContent);
        boxv1.add(perimeter);
        boxv1.add(area);
        boxv2.add(perimeterContent);
        boxv2.add(areaContent);
        boxv1.add(volumn);
        boxv2.add(volumnContent);
        boxv1.add(position);
        boxv2.add(positionContent);
        boxh1.add(boxv1);
        boxh1.add(boxv2);

        this.add(boxh1);
        this.setLayout(new FlowLayout());
        this.setSize(200,480);
        this.setVisible(true);


    }

    @Override
    public void onModeChanged(DrawMode drawMode) {
        String mode=drawMode.getMode();
        objectContent.setText(mode.toLowerCase());
        if(mode.equals("RECTANGLE")||mode.equals("CIRCLE")||mode.equals("TRIANGLE")||mode.equals("FILL_RECTANGEL")){


        }else if(mode.equals("SPHERE")||mode.equals("BOX")||mode.equals("CONE")){

        }

    }



    interface InfoViewListener{
         void infoTrans();

    }
}
