package view.drawPlatform;
import utils.DrawPlatformMode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 左侧工具栏
 *
 * 各种绘图形状
 */
public class LeftToolBar extends JToolBar implements ActionListener {
    LeftToolListener listener;  // 自定义监听器

    public LeftToolBar() {
        // 创建选择画笔工具,直接从枚举类中遍历
        for (DrawPlatformMode dm : DrawPlatformMode.values()) {
            JButton button = new JButton();
            button.setIcon(new ImageIcon(dm.getIcon()));
            button.setToolTipText(dm.getMode());
            this.add(button);
            button.setActionCommand(dm.getMode());
            button.addActionListener(this);
        }

        // 设置
        this.setOrientation(SwingConstants.VERTICAL);
        this.setBorderPainted(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DrawPlatformMode mode = DrawPlatformMode.getModeByStr(e.getActionCommand());
        if(mode != null && listener != null){
            listener.onModeChanged(mode);
        }
    }

    // 设置自定义监听器
    public void setLeftToolListener(LeftToolListener listener){
        this.listener = listener;
    }

    // 自定义监听器
    public interface LeftToolListener{
        void onModeChanged(DrawPlatformMode drawPlatformMode);
    }
}
