package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemodeView extends JFrame implements ActionListener {
    drawmodelListener listener;
    JLabel jLabel;
    JButton jb1,jb2;
    public DemodeView(){
        this.setLayout(null);
        Font f=new Font("宋体",1,16);
        jLabel=new JLabel("请选择工作区间: 使用当前窗口还是打开新窗口？");
        jLabel.setFont(f);
        jLabel.setBounds(10,50,410,20);
        jb1=new JButton("当前窗口");
        jb2=new JButton("新窗口");
        jb1.setBounds(60,150,100,35);
        jb2.setBounds(270,150,100,35);
        this.add(jLabel);

        this.add(jb1);
        this.add(jb2);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        setSize(430,250);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
        frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
        frameSize.width = screenSize.width;
        }
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(listener != null) {
            switch (e.getActionCommand()) {
                case "当前窗口": {
                    System.out.println(e.getActionCommand());
                    this.setVisible(false);//关闭当前窗口
                    //System.exit(0);
                }
                break;
                case "新窗口": {
                    listener.new_window();
                }break;
            }
        }
    }
    // 设置监听

    public void setDrawModelListener(drawmodelListener listener) {
        this.listener =  listener;
    }

    // 自定义监听器
    public interface drawmodelListener{
        void new_window();
    }
}
