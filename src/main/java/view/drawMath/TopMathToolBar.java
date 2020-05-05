package view.drawMath;

import utils.DrawMathMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 顶部工具栏
 *
 * 画笔、画布设置
 */
public class TopMathToolBar extends JPanel implements ActionListener,LeftMathToolBar.LeftMathToolListener {
    TopMathToolListener listener;  // 自定义监听

    JButton penColorBtn; // 画笔颜色选择按钮
    JTextField penColorDisp;    // 画笔颜色预览
    JLabel labelStick;
    TextField lineStickEdit;    // 画笔粗细编辑框

    JPanel area1,area2,area3;

    JSeparator separator;

    JLabel labelX1, labelX2, labelY1, labelY2;
    JLabel labelLen;
    JLabel labelWid;
    JLabel labelHei;
    JLabel labelRadius;

    TextField fieldX1, fieldX2, fieldY1, fieldY2;
    TextField fieldLen;
    TextField fieldWid;
    TextField fieldHei;
    TextField fieldRadius;
    JSeparator separator2;

    JButton confirm;

    Box boxh, boxh2, boxh3, boxh4, boxv1, boxv2, boxv3;
    Box boxCircle,boxRectangle,boxTriangle,boxLine,boxSphere,boxCone,boxBox;
    Box boxhAll;

    DrawMathMode mode;

    JButton test=new JButton("模式更改");

    public TopMathToolBar() {


        // 设置
        this.setLayout(new BorderLayout());
        this.init(Color.BLACK);
        this.setListener(); // 为需要交互的组件设置监听
        this.setVisible(true);
        test.addActionListener(this);

    }

    public DrawMathMode getMode() {
        return mode;
    }

    public void setMode(DrawMathMode mode) {
        this.mode = mode;
    }

    public void init(Color penColor){
        setMode(DrawMathMode.RECTANGEL);

        area1=new JPanel();
        area2=new JPanel();
        area3=new JPanel();

        /** 画笔设置  */
        penColorBtn = new JButton("颜色");    // 颜色标签
        penColorDisp = new JTextField(3);
        penColorDisp.setBackground(penColor);

        // 添加画笔粗细标签、画笔粗细输入框
        lineStickEdit = new TextField("2.0", 2);
        labelStick = new JLabel("粗细");

        //
        separator=new JSeparator();
        separator.setOrientation(JSeparator.VERTICAL);

        //
        labelX1 = new JLabel("X");
        fieldX1 = new TextField("0",4);
        labelY1 = new JLabel("Y");
        fieldY1 = new TextField("0",4);
        //
        labelX2 = new JLabel("X2");
        fieldX2 = new TextField("0",4);
        labelY2 = new JLabel("Y2");
        fieldY2 = new TextField("0",4);

        //
        labelLen = new JLabel("长");
        fieldLen = new TextField(20);
        labelWid = new JLabel("宽");
        fieldWid = new TextField(20);
        labelHei = new JLabel("高");
        fieldHei = new TextField(20);
        labelRadius = new JLabel("半径");
        fieldRadius = new TextField(20);

        separator2 = new JSeparator();
        separator2.setOrientation(JSeparator.VERTICAL);
        //
        confirm = new JButton("确认");

        boxh = Box.createHorizontalBox();
        boxh2 = Box.createHorizontalBox();
        boxh3 = Box.createHorizontalBox();
        boxh4 = Box.createHorizontalBox();

        boxv1 = Box.createVerticalBox();
        boxv2 = Box.createVerticalBox();
        boxv3 = Box.createVerticalBox();

        boxhAll = Box.createHorizontalBox();
        //区域一
        boxh.add(penColorBtn);
        boxh.add(penColorDisp);
        boxh2.add(labelStick);
        boxh2.add(lineStickEdit);
        boxv1.add(boxh);
        boxv1.add(boxh2);
        area1.add(boxv1);

        //区域二
        boxh3.add(labelX1);
        boxh3.add(fieldX1);
        boxh3.setAlignmentX(4);
        boxh3.add(labelY1);
        boxh3.add(fieldY1);
        boxv2.add(boxh3);
        area2.add(boxv2);
        area2.setLayout(new BoxLayout(area2,BoxLayout.X_AXIS));
        area3.add(confirm);

        this.add(area1,BorderLayout.WEST);

    }




    @Override
    public void onModeChanged(DrawMathMode drawMathMode) {
                setMode(drawMathMode);
        switch(drawMathMode){
            case CIRCLE:{
                boxh4.add(labelRadius);
                boxh4.add(fieldRadius);
                boxv2.add(boxh4);
                area2.add(boxv2);
                //area2.revalidate();
            }break;

            case RECTANGEL:{
                boxh4.add(labelLen);
                boxh4.add(fieldLen);
                boxh4.setAlignmentX(4);
                boxh4.add(labelWid);
                boxh4.add(fieldWid);
                boxv2.add(boxh4);
                area2.add(boxv2);
               // area2.revalidate();
            }break;
            case TRIANGLE:{
                boxh4.add(labelX2);
                boxh4.add(fieldX2);
                boxh4.setAlignmentX(4);
                boxh4.add(labelY2);
                boxh4.add(fieldY2);
                boxv2.add(boxh4);
                area2.add(boxv2);
                //area2.revalidate();
            }
            case LINE:{
                boxh4.add(labelX2);
                boxh4.add(fieldX2);
                boxh4.setAlignmentX(4);
                boxh4.add(labelY2);
                boxh4.add(fieldY2);
                boxv2.add(boxh4);
                area2.add(boxv2);
               // area2.revalidate();
            }break;
            case CONE:{
                boxh4.add(labelRadius);
                boxh4.add(fieldRadius);
                boxh4.setAlignmentX(4);
                boxh4.add(labelHei);
                boxh4.add(fieldHei);
                boxv2.add(boxh4);
                area2.add(boxv2);
               // area2.revalidate();
            }break;
            case BOX:{
                boxh4.add(labelLen);
                boxh4.add(fieldLen);
                boxh4.setAlignmentX(4);
                boxh4.add(labelWid);
                boxh4.add(fieldWid);
                boxh4.setAlignmentX(4);
                boxh4.add(labelHei);
                boxh4.add(fieldHei);
                boxv2.add(boxh4);
                area2.add(boxv2);
                area2.revalidate();
            }break;
            case SPHERE:{
                boxh4.add(labelRadius);
                boxh4.add(fieldRadius);
                boxv2.add(boxh4);
                area2.add(boxv2);
                area2.revalidate();
            }
        }
        this.add(area2,BorderLayout.CENTER);
        this.add(area3,BorderLayout.EAST);

        SwingUtilities.updateComponentTreeUI(this);
    }

    // 设置功能组件监听
    private void setListener() {
        // 监听画笔颜色按钮
        penColorBtn.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "颜色选择", penColorDisp.getBackground());
            if (listener != null) {
                listener.onPenColorChanged(color);
                penColorDisp.setBackground(color);
            }
        });

        // 监听画笔粗细输入框输入
        lineStickEdit.addTextListener(e -> {
            if (listener != null) {
                try {
                    listener.onLineStrockChanged(Float.parseFloat(lineStickEdit.getText()));
                } catch (NumberFormatException nfe) {
                }
            }
        });

        confirm.addActionListener(e ->{
            if (listener != null) {
                try {
                    listener.onParamsChanged(Integer.parseInt(fieldX1.getText()),Integer.parseInt(fieldY1.getText()),
                            Float.parseFloat(fieldLen.getText()),Float.parseFloat(fieldWid.getText()),
                            Float.parseFloat(fieldHei.getText()),Float.parseFloat(fieldRadius.getText()),
                            Integer.parseInt(fieldX2.getText()),Integer.parseInt(fieldY2.getText()));
                } catch (NumberFormatException nfe) {
                }
            }

        });

    }


        // 设置自定义监听器
        public void setTopMathToolListener (TopMathToolListener listener){
            this.listener = listener;
        }

        // 自定义监听器
        public interface TopMathToolListener {
            void onPenColorChanged(Color color);
            void onLineStrockChanged(Float lineStroke);
            void onParamsChanged(Integer X1,Integer Y1,Float length,Float width,Float height,Float radius,Integer X2,Integer Y2);

        }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object=e.getSource();
        if(object==test){
            this.remove(area1);

        }

    }
}

