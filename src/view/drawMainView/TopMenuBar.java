package view.drawMainView;

import javax.swing.*;

/**
 * 顶部菜单栏
 */
public class TopMenuBar extends JMenuBar{
	TopMenuListener listener;	// 自定义监听器

	JMenu tFile=new JMenu("文件");
	JMenu tEdit=new JMenu("编辑");
	JMenu tOption=new JMenu("选项");
	JMenu tHelp=new JMenu("帮助");

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


	public TopMenuBar() {
		this.add(tFile);
		this.add(tEdit);
		this.add(tOption);
		this.add(tHelp);

		tFile.add(fOpenDB);
		tFile.add(fOpen);
		tFile.add(fNew);
		tFile.add(fSaveDB);
		tFile.add(fSave);

		tEdit.add(eCancle);
		tEdit.add(eRedo);
		tEdit.add(eClearAll);

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
	}


}
