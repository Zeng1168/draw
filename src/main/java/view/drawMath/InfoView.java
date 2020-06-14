package view.drawMath;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class InfoView extends JPanel {
    private Map<String, String> infoMap;

    public InfoView() {
        this.setLayout(new FlowLayout());
        this.setSize(200,480);
        this.setVisible(true);

    }

    public void refrashInfo(Map<String, String> map){
        this.infoMap = map;
        this.paintComponent();
    }

    private void paintComponent(){
        // 先移除所有元素
        this.removeAll();
        if(infoMap != null){
            Box mainBox = Box.createVerticalBox();
            Set<Map.Entry<String, String>> entrySet = infoMap.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> mapEntry = iterator.next();
                Box lineBox = Box.createVerticalBox();
                JLabel label = new JLabel(mapEntry.getKey() + ":" + mapEntry.getValue());
                lineBox.add(label);
                lineBox.add(Box.createVerticalStrut(10));   // 添加垂直间隙
                mainBox.add(lineBox);
            }
            this.add(mainBox);
        }

        this.repaint();
    }

}
