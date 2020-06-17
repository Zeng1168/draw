package view;

import controller.PasswordModifyController;
import utils.SwingUtills;

import javax.swing.*;
import java.awt.*;

/**
 * 用户密码修改窗口
 */
public class PasswordModifyView extends JFrame {
    private UserModifyListener listener;

    private JPasswordField passwordOldEdit,passwordEdit;
    private JButton sureBtn;
    public PasswordModifyView() {
        Box mainBox = Box.createVerticalBox();
        // 上下两个水平盒子
        Box inputBox = Box.createHorizontalBox();
        Box btnBox = Box.createHorizontalBox();


        // 上边盒子
        // 垂直标签盒子
        Box labelBox = Box.createVerticalBox();
        labelBox.add(new JLabel("请输入原始密码："));
        labelBox.add(Box.createVerticalStrut(10));
        labelBox.add(new JLabel("请输入新密码："));
        // 垂直输入框盒子
        Box EditBox = Box.createVerticalBox();
        passwordOldEdit=new JPasswordField(10);
        passwordEdit=new JPasswordField(10);
        EditBox.add(passwordOldEdit);
        EditBox.add(Box.createVerticalStrut(10));
        EditBox.add(passwordEdit);

        inputBox.add(labelBox);
        inputBox.add(EditBox);

        // 下边盒子
        sureBtn = new JButton("确定");
        btnBox.add(sureBtn);


        mainBox.add(Box.createVerticalStrut(30));
        mainBox.add(inputBox);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(btnBox);
        this.setLayout(new FlowLayout());
        this.add(mainBox);

        // 绑定controller
        listener = new PasswordModifyController(this);

        // 设置监听
        sureBtn.addActionListener(e -> {
            if(listener != null){
                listener.onModify(passwordEdit.getText().trim(), passwordOldEdit.getText().trim());
                System.out.println(e.getActionCommand());
            }
        });

        // 设置窗口
        SwingUtills.setMiddleBounds(this, 300, 200);
        setTitle("密码修改");
        setVisible(true);
    }


    public void showMessageDialog(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }

    public void focusPasswordInput(){
        passwordEdit.requestFocusInWindow();
    }

    public void focusPasswordOldInput(){
        passwordOldEdit.requestFocusInWindow();
    }


    // 自定义监听器
    public interface UserModifyListener{
        void onModify(String password, String passwordOld);
    }
}
