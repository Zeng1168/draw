package view;

import controller.DataQueryController;
import entity.DataBrief;
import utils.DateUtil;
import utils.DrawMathMode;
import utils.SwingUtills;
import view.drawMath.DrawMathView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DataQueryView extends JFrame implements ActionListener {
    private Listener listener;
    private DrawMathMode drawMathMode;  // 绘图模式，未空时代表平面绘图模式，不为空代表数学绘图模式

    private JPanel contentPane;
    private JTable table;
    private String[] columnCount= {"id","名称", "时间"};
    private List<DataBrief> list;

    public DataQueryView(DrawMathMode drawMathMode){
        this.drawMathMode =  drawMathMode;
        this.initComponent();   // 初始化组件

        // 绑定Controller
        DataQueryController dataQueryController = new DataQueryController(this);
        this.listener = dataQueryController;

        // 设置窗体
        SwingUtills.setMiddleBounds(this, 800, 500);
        this.setVisible(true);
    }

    private void initComponent(){
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
    }

    public void setData(List<DataBrief> list){
        if(list == null) {
            JOptionPane.showMessageDialog(null, "服务器繁忙");
            return;
        }
        this.list = list;

        Object[][] data = new Object[list.size()][];
        for(int i=0; i<list.size(); i++){
            Object[] row = new Object[3];
            row[0] = list.get(i).getId() + "";
            row[1] = list.get(i).getName() + "";
            row[2] = DateUtil.dateTimeFormate(list.get(i).getTime());
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
                    listener.onEdit(list.get(num).getId());
                } break;
                case "删除": {
                    listener.onDelete(list.get(num).getId());
                }
            }
        }
    }

    public DrawMathMode getDrawMathMode() {
        return drawMathMode;
    }

    // 自定义监听器
    public interface Listener{
        void onQuery();
        void onEdit(Integer id);
        void onDelete(Integer id);
    }
}
