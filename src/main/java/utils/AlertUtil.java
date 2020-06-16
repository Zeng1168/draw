package utils;

import javafx.scene.control.Alert;

import javax.swing.*;

public class AlertUtil {

    /**  以下为javafx的提示框 */
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


    /**  以下为java Swing的提示框   */
    public static final String MESSAGE_WARNING     = " 警告";
    public static final String MESSAGE_INFORMATION = " 消息";
    public static final String MESSAGE_ERROR = " 错误";

    /***
     * Warning boxes
     *
     * @param mesg
     */
    public static void warningDialog(String mesg)
    {
        JOptionPane
                .showMessageDialog(
                        null,
                        "<html><font color=\"yellow\"  style=\"font-weight:bold;" +
                                "background-color:#666666;\" >"
                                + mesg + "</font></html>", MESSAGE_WARNING,
                        JOptionPane.WARNING_MESSAGE);
    }

    /***
     * error
     *
     * @param mesg
     */
    public static void errorDialog(String mesg)
    {
        JOptionPane
                .showMessageDialog(
                        null,
                        "<html><font color=\"red\"  style=\"font-weight:bold;" +
                                "background-color:white\" >"
                                + mesg + "</font></html>", MESSAGE_ERROR,
                        JOptionPane.ERROR_MESSAGE);
    }

    /***
     * information
     *
     * @param mesg
     */
    public static void infoDialog(String mesg)
    {
        JOptionPane.showMessageDialog(null,
                "<html><font color=\"green\"  style=\"font-weight:bold;\" >" + mesg
                        + "</font></html>", MESSAGE_INFORMATION,
                JOptionPane.INFORMATION_MESSAGE);
    }
}
