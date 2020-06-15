package view.drawMath;

import utils.DrawMathMode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 数学绘图左侧工具栏
 *
 * 各种绘图形状
 */
public class LeftToolBar extends JToolBar implements ActionListener {
    LeftMathToolListener listener;  // 自定义监听器

    public LeftToolBar() {
         for (DrawMathMode dm : DrawMathMode.values()) {
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
        DrawMathMode mode = DrawMathMode.getModeByStr(e.getActionCommand());
        if(mode != null && listener != null){
            listener.onModeChanged(mode);
        }
    }

    // 设置自定义监听器
    public void setLeftToolListener(LeftMathToolListener listener){
        this.listener = listener;
    }

    // 自定义监听器
    public interface LeftMathToolListener{
        void onModeChanged(DrawMathMode drawMathMode);
    }
}
