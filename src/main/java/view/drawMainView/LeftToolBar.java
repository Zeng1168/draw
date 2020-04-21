package view.drawMainView;
import utils.DrawMode;

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
        for (DrawMode dm : DrawMode.values()) {
            JButton button = new JButton(dm.getMode());
            this.add(button);
            button.setActionCommand(dm.getMode());
            button.addActionListener(this);
        }

        // 设置
        this.setOrientation(SwingConstants.VERTICAL);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DrawMode mode = DrawMode.getModeByStr(e.getActionCommand());
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
        void onModeChanged(DrawMode drawMode);
    }
}
