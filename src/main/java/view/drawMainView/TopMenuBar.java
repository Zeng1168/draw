package view.drawMainView;

import javax.swing.*;
import java.awt.*;

/**
 * 顶部菜单栏
 */
public class TopMenuBar extends JMenuBar{
	TopMenuListener listener;	// 自定义监听器

	JMenu tFile=new JMenu("文件");
	JMenu tEdit=new JMenu("编辑");
	JMenu tOption=new JMenu("选项");
	JMenu tHelp=new JMenu("帮助");
	JMenu tUser=new JMenu("用户");
	JMenu tchange=new JMenu("画板模式");



	// 文件菜单
	JMenuItem fOpen=new JMenuItem("打开本地文件");
	JMenuItem fOpenDB=new JMenuItem("打开数据库文件");
	JMenuItem fNew=new JMenuItem("新建");
	JMenuItem fSaveDB=new JMenuItem("保存到数据库");
	JMenuItem fSave=new JMenuItem("保存到文件");

	// 编辑菜单
	JMenuItem eCancle = new JMenuItem("撤销");
	JMenuItem eRedo = new JMenuItem("重做");
	JMenuItem eClearAll=new JMenuItem("清空");

	//用户菜单
	JMenuItem uModify=new JMenuItem("修改信息");

	//画板模式
	JMenuItem change=new JMenuItem("平面绘图模式");
	JMenuItem change2=new JMenuItem("数学绘图模式");
	public TopMenuBar() {
		this.add(tFile);
		this.add(tEdit);
		this.add(tOption);
		this.add(tHelp);
		this.add(tUser,0);
		this.add(tchange);

		tFile.add(fOpenDB);
		tFile.add(fOpen);
		tFile.add(fNew);
		tFile.add(fSaveDB);
		tFile.add(fSave);

		tEdit.add(eCancle);
		tEdit.add(eRedo);
		tEdit.add(eClearAll);

		tUser.add(uModify);

		tchange.add(change);
		tchange.add(change2);
		ImageIcon icon=new ImageIcon("src/main/resources/img_source/user1.png");
		tUser.setIcon(icon);
		this.setListener();
		this.setVisible(true);
	}

	// 设置监听
	public void setListener() {
		fOpenDB.addActionListener(e -> {
			if(listener != null){
				listener.onOpenDB();
			}
		});

		fNew.addActionListener(e -> {
			if(listener != null){
				listener.onCreateNew();
			}
		});

		fSaveDB.addActionListener(e -> {
			if(listener != null){
				listener.onSaveDB();
			}
		});

		fSave.addActionListener(e -> {
			if(listener != null){
				listener.onSaveFile();
			}
		});

		eCancle.addActionListener(e -> {
			if(listener != null){
				listener.onCancleEdit();
			}
		});

		eRedo.addActionListener(e -> {
			if(listener != null){
				listener.onRedoEdit();
			}
		});
		eClearAll.addActionListener(e -> {
			if(listener != null){
				listener.onClearEdit();
			}
		});

		uModify.addActionListener(e -> {
			if(listener!=null){
				listener.onModify();
			}
		});
		change.addActionListener(e -> {
			if(listener!=null){
				listener.onchange();
			}
		});
		change2.addActionListener(e -> {
			if(listener!=null){
				listener.onchange2();
			}
		});
	}

	// 设置自定义监听器
	public void setTopMenuListener(TopMenuListener listener){
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
		void onModify();
		void onchange();
		void onchange2();
	}
}
