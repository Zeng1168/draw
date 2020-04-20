package utils;

import javafx.scene.control.Alert;

public class AlertUtil {

    public static void alertInfo(String title, String headerText, String contentText){
        alert(Alert.AlertType.INFORMATION, title, headerText, contentText);
    }

    public static void alertWarn(String title, String headerText, String contentText){
        alert(Alert.AlertType.WARNING, title, headerText, contentText);
    }

    public static void alertError(String title, String headerText, String contentText){
        alert(Alert.AlertType.ERROR, title, headerText, contentText);
    }

    private static void alert(Alert.AlertType type, String title, String headerText, String contentText){
        Alert alert=new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
