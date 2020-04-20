package view.drawMainView;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MenuController extends Application {
    MenuController.TopMenuListener listener;	// 自定义监听器
    @FXML
    private MenuItem fnew;
    @FXML
    private MenuItem fopen;
    @FXML
    private MenuItem fSavedb;
    @FXML
    private MenuItem fSaveLocal;
    @FXML
    private MenuItem eredo;
    @FXML
    private MenuItem ecancel;
    @FXML
    private MenuItem eclean;
    Scene scene;



    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root= FXMLLoader.load(getClass().getResource("menu.fxml"));
        //primaryStage.setTitle("登录成功界面");
        scene=new Scene(root,100,600);
        setSecene(scene);
        primaryStage.setScene(scene);
        //primaryStage.show();

    }
    public void setSecene(Scene secene){
        this.scene=secene;
    }
    public Scene getScene(){
        return scene;


    }

    // 设置监听
    public void setListener() {
        fopen.setOnAction(e -> {
            if(listener != null){
                listener.onOpenDB();
            }
        });

        fnew.setOnAction(e -> {
            if(listener != null){
                listener.onCreateNew();
            }
        });

        fSavedb.setOnAction(e -> {
            if(listener != null){
                listener.onSaveDB();
            }
        });

        fSaveLocal.setOnAction(e -> {
            if(listener != null){
                listener.onSaveFile();
            }
        });

        ecancel.setOnAction(e -> {
            if(listener != null){
                listener.onCancleEdit();
            }
        });

        eredo.setOnAction(e -> {
            if(listener != null){
                listener.onRedoEdit();
            }
        });
        eclean.setOnAction(e -> {
            if(listener != null){
                listener.onClearEdit();
            }
        });
    }

    // 设置自定义监听器
    public void setTopMenuListener(MenuController.TopMenuListener listener){
        this.listener = listener;
    }

    // 自定义监听器
    public interface TopMenuListener{
        void onOpenDB();
        void onCreateNew();
        void onSaveDB();
        void onSaveFile();
        void onCancleEdit();
        void onRedoEdit();
        void onClearEdit();
    }


}
