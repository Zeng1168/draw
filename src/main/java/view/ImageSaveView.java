package view;

import entity.Image;
import utils.DateUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ImageSaveView extends JFrame implements ActionListener {
    ImageSaveListener listener;

    private JPanel contentPane;
    private JTable table;
    private String[] columnCount= {"id","用户", "时间"};
    private List<Image> list;

    public ImageSaveView(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 764, 469);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(29, 58, 692, 332);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JButton button = new JButton("查询");
        button.setActionCommand("查询");
        button.addActionListener(this);
        button.setBounds(58, 22, 93, 23);
        contentPane.add(button);

        //全屏
//		setExtendedState(JFrame.MAXIMIZED_BOTH);

        JButton button_2 = new JButton("编辑");
        button_2.setActionCommand("编辑");
        button_2.addActionListener(this);
        button_2.setBounds(357, 22, 93, 23);
        contentPane.add(button_2);

        JButton button_3 = new JButton("删除");
        button_3.setActionCommand("删除");
        button_3.addActionListener(this);
        button_3.setBounds(539, 22, 93, 23);
        contentPane.add(button_3);
        this.setVisible(true);
    }

    public void setData(List<Image> images){
        if(images == null) {
            JOptionPane.showMessageDialog(null, "服务器繁忙");
            return;
        }
        this.list = images;

        Object[][] data = new Object[images.size()][];
        for(int i=0; i<images.size(); i++){
            Object[] row = new Object[3];
            row[0] = images.get(i).getId() + "";
            row[1] = images.get(i).getUserId() + "";
            row[2] = DateUtil.dateTimeFormate(images.get(i).getTime());
            data[i] = row;
        }
        table.setModel(new DefaultTableModel(data, columnCount));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(listener != null){
            int num = table.getSelectedRow();
            switch (e.getActionCommand()){
                case "查询" : {
                    listener.onQuery();
                } break;
                case "编辑" : {
                    listener.onEdit(list.get(num).getImage());
                } break;
                case "删除": {
                    listener.onDelete(list.get(num).getId());
                }
            }
        }
    }


    // 设置监听
    public void setImageSaveListener(ImageSaveListener listener){
        this.listener = listener;
    }

    // 自定义监听器
    public interface ImageSaveListener{
        void onQuery();
        void onEdit(String image);
        void onDelete(Integer id);
    }
}
