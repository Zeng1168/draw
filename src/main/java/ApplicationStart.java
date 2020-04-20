import controller.LoginController2;

import javax.swing.*;

public class ApplicationStart {
    public static void main(String[] args) {
//        new DrawMainController();
//        new LoginController();
//        new ConeController();

        try {
            ////////////////////////---------------------------------- select Look and Feel(下面就是要改变的地方了)
            JFrame.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
            ////////////////////////---------------------------------- start application



            try {
                LoginController2.launch(LoginController2.class);

            }catch (Exception e){
                e.printStackTrace();
            }


        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
