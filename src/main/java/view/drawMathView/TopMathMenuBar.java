package view.drawMathView;

import javax.swing.*;

/**
 * 顶部菜单栏
 */
public class TopMathMenuBar extends JMenuBar{
	TopMathMenuListener listener;	// 自定义监听器

	JMenu tFile=new JMenu("文件");
	//JMenu tEdit=new JMenu("编辑");
	JMenu tOption=new JMenu("选项");
	JMenu tHelp=new JMenu("帮助");
	JMenu tUser=new JMenu("用户");

	// 文件菜单
	JMenuItem fOpen=new JMenuItem("打开本地文件");
	JMenuItem fOpenDB=new JMenuItem("打开数据库文件");
	JMenuItem fNew=new JMenuItem("新建");
	JMenuItem fSaveDB=new JMenuItem("保存到数据库");
	JMenuItem fSave=new JMenuItem("保存到文件");


	public TopMathMenuBar() {
		this.add(tFile);
		//this.add(tEdit);
		this.add(tOption);
		this.add(tHelp);
		this.add(tUser);

		tFile.add(fOpenDB);
		tFile.add(fOpen);
		tFile.add(fNew);
		tFile.add(fSaveDB);
		tFile.add(fSave);



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


	}

	// 设置自定义监听器
	public void setTopMathMenuListener(TopMathMenuListener listener){
		this.listener = listener;
	}

	// 自定义监听器
	public interface TopMathMenuListener{
		void onOpenDB();
		void onCreateNew();
		void onSaveDB();
		void onSaveFile();

	}


}
