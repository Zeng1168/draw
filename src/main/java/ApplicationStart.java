import javafx.application.Application;
import javafx.stage.Stage;
import view.drawMath.DrawMathView;
import view.drawPlatform.DrawPlatformView;


public class ApplicationStart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

//        new DrawPlatformView();

        new DrawMathView();

//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/Login.fxml"));
//        root.getStylesheets().add(getClass().getClassLoader().getResource("style/login.css").toExternalForm());
//        primaryStage.setTitle("用户登陆");
//        primaryStage.setScene(new Scene(root, 600, 400));
//        primaryStage.show();
//        JFrame.setDefaultLookAndFeelDecorated(true);
//        UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
    }
}
