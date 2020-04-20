import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

public class ApplicationStart extends Application {
    // Zeng
    //添加二行
    // 添加一行
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("style/Login.fxml"));
        root.getStylesheets().add(getClass().getClassLoader().getResource("style/login.css").toExternalForm());
        primaryStage.setTitle("用户登陆");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        JFrame.setDefaultLookAndFeelDecorated(true);
        UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");

    }

}
