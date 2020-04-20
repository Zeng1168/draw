import controller.LoginController;

import javax.swing.*;

public class ApplicationStart {
    // Zeng
    //添加二行
    // 添加一行

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
                LoginController.launch(LoginController.class);

            }catch (Exception e){
                e.printStackTrace();
            }


        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
