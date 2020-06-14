package view.drawPlatform;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * 顶部工具栏
 *
 * 画笔、画布设置
 */
public class TopToolBar extends JToolBar{
	TopToolListener listener;  // 自定义监听

    JButton penColorBtn; // 画笔颜色选择按钮
    JTextField penColorDisp;    // 画笔颜色预览
    TextField lineStickEdit;    // 画笔粗细编辑框

    JButton backColorBtn;   // 画布设置按钮
    JButton changeSizeBtn;
    JTextField backColorDisp;    // 画布颜色预览
    JTextField groundSizeXEdit; // 画布宽度
    JTextField groundSizeYEdit; // 画布高度


    public TopToolBar(Color penColor, Color backColor, int width, int height){
        // 创建图形组件
        JPanel penPanel = new JPanel();	// 画笔属性
        JPanel canvaPanel = new JPanel();	// 画布属性

        /** 画笔设置  */
        // 添加画笔标题边框
        penPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLUE,5),"画笔",TitledBorder.LEFT,TitledBorder.TOP,new Font("StSong",Font.PLAIN,18)));
        // 添加画笔颜色选择按钮、画笔颜色预览块
        penColorBtn = new JButton("颜色");	// 颜色标签
        penColorDisp = new JTextField(3);
        penColorDisp.setBackground(penColor);
        penPanel.add(penColorBtn);
        penPanel.add(penColorDisp);
        // 添加画笔粗细标签、画笔粗细输入框
        lineStickEdit = new TextField("2.0",2);
        penPanel.add(new JLabel("粗细"));
        penPanel.add(lineStickEdit);

        /** 画布设置 */
        // 添加画布标题边框
        canvaPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.GREEN,5),"画布",TitledBorder.LEFT,TitledBorder.TOP,new Font("StSong",Font.PLAIN,18)));
        // 添加背景颜色选择按钮、背景颜色预览块
        backColorBtn = new JButton("颜色");
        backColorDisp = new JTextField(3);
        backColorDisp.setBackground(backColor);
        canvaPanel.add(backColorBtn);
        canvaPanel.add(backColorDisp);
        // 添加尺寸设置组件
        groundSizeXEdit = new JTextField(String.valueOf(width),2);
        groundSizeYEdit = new JTextField(String.valueOf(height),2);
        changeSizeBtn = new JButton("改变尺寸");
        canvaPanel.add(new JLabel("尺寸"));   // 尺寸（标签）
        canvaPanel.add(groundSizeXEdit);    // 长（输入框）
        canvaPanel.add(new JLabel("x")); 	// 乘（标签）
        canvaPanel.add(groundSizeYEdit);    // 宽（输入框）
        canvaPanel.add(new JLabel("像素"));   // 像素（标签）
        canvaPanel.add(changeSizeBtn);

        this.setListener(); // 为需要交互的组件设置监听
        this.add(penPanel);	// 添加画笔设置组件
        this.add(canvaPanel);	// 添加画布设置组件

        // 设置
        this.setMargin(new Insets(2,2,2,2));
        this.setOrientation(SwingConstants.HORIZONTAL);
        this.setVisible(true);
    }

    // 设置功能组件监听
    private void setListener(){
        // 监听画笔颜色按钮
        penColorBtn.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "颜色选择", penColorDisp.getBackground());
            if(listener != null) {
                listener.onPenColorChanged(color);
                penColorDisp.setBackground(color);
            }
        });

        // 监听画笔粗细输入框输入
        lineStickEdit.addTextListener(e -> {
            if(listener != null){
                try{
                    listener.onLineStrockChanged(Float.parseFloat(lineStickEdit.getText()));
                }catch(NumberFormatException nfe){ }
            }
        });

        // 监听画布颜色选择按钮
        backColorBtn.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "颜色选择", backColorDisp.getBackground());
            if(listener != null) {
                listener.onBackColorChanged(color);
                backColorDisp.setBackground(color);
            }
        });

        changeSizeBtn.addActionListener(e -> {
            try {
                Integer sizeX = Integer.valueOf(groundSizeXEdit.getText());
                Integer sizeY = Integer.valueOf(groundSizeYEdit.getText());
                if(listener != null && sizeX!= null && sizeY != null){
                    listener.onSizeChanged(sizeX, sizeY);
                }
            }catch (Exception ex){}

        });
    }

	// 设置自定义监听器
	public void setTopToolListener(TopToolListener listener){
		this.listener = listener;
	}

	// 自定义监听器
	public interface TopToolListener{
		void onPenColorChanged(Color color);
		void onBackColorChanged(Color color);
		void onLineStrockChanged(Float lineStroke);
		void onSizeChanged(Integer x, Integer y);
	}
}
