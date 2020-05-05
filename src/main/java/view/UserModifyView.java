package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserModifyView extends JFrame implements ActionListener {
    UserModifyListener listener;

    Box boxh1,boxh2;
    Box boxv1,boxv2;
    JLabel jl1,jl2,jl3;
    JTextField jf1,jf2,jf3;
    JButton jb;
    public UserModifyView() {

        boxh1=Box.createHorizontalBox();
        boxh2=Box.createHorizontalBox();
        boxv1=Box.createVerticalBox();
        boxv2=Box.createVerticalBox();
        jl2=new JLabel("请输入原始密码：");
        jl3=new JLabel("请输入新密码：");
        jf2=new JTextField(10);
        jf3=new JTextField(10);
        jb=new JButton("确定");
        boxv1.add(jl2);
        boxv2.add(jf2);
        boxv1.add(jl3);
        boxv2.add(jf3);
        boxh1.add(boxv1);
        boxh1.add(boxv2);
        boxh2.add(jb);

        this.setLayout(new FlowLayout());
        this.add(boxh1);
        this.add(boxh2);

        jb.addActionListener(this);
        setSize(300,200);
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


    public void showMessageDialog(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }

    public void focusPasswordInput(){
        jf3.requestFocusInWindow();
    }

    public void focusPasswordOldInput(){
        jf2.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(listener != null){
            listener.onModify(jf3.getText().trim(), jf2.getText().trim());
            System.out.println(e.getActionCommand());
        }
    }

    // 设置监听
    public void setUserModifyListener(UserModifyListener listener){
        this.listener = listener;
    }

    // 自定义监听器
    public interface UserModifyListener{
        void onModify(String password, String passwordOld);
    }
}
