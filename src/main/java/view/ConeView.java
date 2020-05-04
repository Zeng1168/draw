package view;


import javax.media.j3d.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConeView extends JPanel implements ActionListener {
   ConeController listener;
    Box boxh1,boxh2,boxh3;
    Box boxv1,boxv2,boxv3,boxv4;
    JLabel jl1,jl2,jfarea,jfvolume,area,volume;
    JTextField jf1,jf2;
    JButton jb;
    JPanel jPanel1;
    Canvas3D canvas;
    draw3d d=new draw3d();
    Color color=new Color(147, 218, 182);
    public ConeView() {
        boxh1=Box.createHorizontalBox();
        boxh2=Box.createHorizontalBox();
        boxh3=Box.createHorizontalBox();
        boxv1=Box.createVerticalBox();
        boxv2=Box.createVerticalBox();
        boxv3=Box.createVerticalBox();
        boxv4=Box.createVerticalBox();
        jl1=new JLabel("请输入圆锥的半径：");
        jl2=new JLabel("请输入圆锥高：");
        jf1=new JTextField(10);
        jf2=new JTextField(10);
        jfarea=new JLabel("表面积: ");
        area=new JLabel();
        jfvolume=new JLabel("体    积: ");
        volume=new JLabel();
        jPanel1=new JPanel();
        jb=new JButton("确定");
        boxv1.add(jl1);
        boxv2.add(jf1);
        boxv1.add(jl2);
        boxv2.add(jf2);
        boxv3.add(jfarea);
        boxv3.add(jfvolume);
        boxv4.add(area);
        boxv4.add(volume);
        boxh1.add(boxv1);
        boxh1.add(boxv2);
        boxh2.add(jb);
        boxh3.add(boxv3);
        boxh3.add(boxv4);
        jPanel1.add(boxh1);
        jPanel1.add(boxh2);
        jPanel1.add(boxh3);

       // this.setLayout(new BorderLayout());
        this.add(jPanel1,BorderLayout.NORTH);

        jb.addActionListener(this);

        //setSize(800,600);
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        Dimension frameSize = this.getSize();
//        if (frameSize.height > screenSize.height) {
//            frameSize.height = screenSize.height;
//        }
//        if (frameSize.width > screenSize.width) {
//            frameSize.width = screenSize.width;
//        }
//        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
//
        setVisible(true);
       // this.repaint();
    }


    public void showMessageDialog(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }

    public void focusrInput(){
        jf1.requestFocusInWindow();
    }

    public void focushInput(){
        jf2.requestFocusInWindow();
    }
    public void setproperty(double area1,double volume1){
        area.setText(area1+"");
        volume.setText(volume1+"");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(listener != null){
            listener.paint(jf1.getText().trim(), jf2.getText().trim());
            float r=Float.parseFloat(jf1.getText().trim());
            float h= Float.parseFloat(jf2.getText().trim());
            d.setrh(r,h);

            canvas=d.draw();
            this.add(canvas,BorderLayout.CENTER);
           this.repaint();

        }
    }
    //设置监听
    public void setConeListener(ConeController listener){this.listener=listener;}

    public interface ConeController{ void paint(String r, String h);}
}
