package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements ActionListener {
    LoginListener listener;

    JLabel lab1,lab2,lab3;
    JTextField tfyhm;
    JPasswordField tfpsw;
    JButton btnLogin,btnCancel,btnRegister,btnmodify;
    JRadioButton student,teacher;
    ButtonGroup group;
    Box boxh1,boxh2,boxh3,boxh4;
    Box boxv1,boxv2;
    Font f=new Font("",Font.BOLD,25);

    public LoginView() {
        setTitle("用户登录");
        setLayout(new FlowLayout());
        lab1=new JLabel("欢迎图形绘制系统");
        lab1.setFont(f);
        lab1.setForeground(Color.blue);
        lab2=new JLabel("用户名 ");
        lab2.setFont(new Font("",Font.BOLD,20));
        lab3=new JLabel("密    码 ");
        lab3.setFont(new Font("",Font.BOLD,20));
        tfyhm=new JTextField(20);
        tfpsw=new JPasswordField(20);
        btnLogin=new JButton("登录");
        btnCancel=new JButton("取消");
        btnRegister=new JButton("注册");
        btnmodify=new JButton("修改信息");

        group=new ButtonGroup();
        group.add(student);
        group.add(teacher);
        boxh1=Box.createHorizontalBox();
        boxh2=Box.createHorizontalBox();
        boxh3=Box.createHorizontalBox();
        boxh4=Box.createHorizontalBox();
        boxv1=Box.createVerticalBox();
        boxv2=Box.createVerticalBox();
        boxh1.add(lab1);
        boxv1.add(lab2);
        boxv1.add(lab3);
        boxv2.add(tfyhm);
        boxv2.add(tfpsw);
        boxh2.add(boxv1);
        boxh2.add(boxv2);
        boxh3.add(btnLogin);
        boxh3.add(btnCancel);
        boxh3.add(btnRegister);
        boxh3.add(btnmodify);
        add(boxh1);
        add(boxh2);
        add(boxh4);
        add(boxh3);

        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);
        btnCancel.addActionListener(this);
        btnmodify.addActionListener(this);

        setSize(400,400);
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

    public void focusUserInput(){
        tfyhm.requestFocusInWindow();
    }

    public void focusPasswordInput(){
        tfpsw.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (btnLogin.equals(e.getSource())) {   // 登录事件
            if (listener != null) {
                listener.onLogin(tfyhm.getText().trim(), tfpsw.getText().trim());
            }
        } else if (e.getSource() == btnRegister) {  // 注册事件
            if (listener != null) {
                listener.onRegister(tfyhm.getText().trim(), tfpsw.getText().trim());
            }
        } else if (e.getSource() == btnCancel) {    // 取消
            tfyhm.setText(null);
            tfpsw.setText(null);
            tfpsw.requestFocusInWindow();
            tfyhm.requestFocusInWindow();
        } else if (e.getSource() == btnmodify) {    // 修改用户信息
            if(listener != null){
                listener.onModifyUser();
            }
        }

    }


    // 设置监听
    public void setLoginListener(LoginListener listener){
        this.listener = listener;
    }

    // 自定义监听器
    public interface LoginListener{
        void onLogin(String name, String password);
        void onRegister(String name, String password);
        void onModifyUser();
    }
}
