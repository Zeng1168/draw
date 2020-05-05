package view.drawMainView;

import entity.MenuEntity;
import entity.MenuItemEntity;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 顶部菜单栏
 */
public class TopMenuBar extends JMenuBar implements ActionListener {
	TopMenuListener listener;	// 自定义监听器

	// 菜单栏数据
	private List<MenuEntity> initMenuData(){
		List<MenuEntity> menuList = new ArrayList<>();

		// 临时变量
		MenuEntity menuEntity = null;
		List<MenuItemEntity> menuItemEntityList = null;


		// 添加一个菜单
		menuItemEntityList = new ArrayList<>();
		menuItemEntityList.add(new MenuItemEntity("修改密码"));
		menuEntity = new MenuEntity("用户", "src/main/resources/img_source/user1.png", menuItemEntityList);
		menuList.add(menuEntity);

		// 添加一个菜单
		menuItemEntityList = new ArrayList<>();
		menuItemEntityList.add(new MenuItemEntity("打开本地文件"));
		menuItemEntityList.add(new MenuItemEntity("打开数据库文件"));
		menuItemEntityList.add(new MenuItemEntity("新建"));
		menuItemEntityList.add(new MenuItemEntity("保存到数据库"));
		menuItemEntityList.add(new MenuItemEntity("保存到文件"));
		menuEntity = new MenuEntity("文件",  menuItemEntityList);
		menuList.add(menuEntity);

		// 添加一个菜单
		menuItemEntityList = new ArrayList<>();
		menuItemEntityList.add(new MenuItemEntity("撤销"));
		menuItemEntityList.add(new MenuItemEntity("重做"));
		menuItemEntityList.add(new MenuItemEntity("清空"));
		menuEntity = new MenuEntity("编辑",  menuItemEntityList);
		menuList.add(menuEntity);

		// 添加一个菜单
		menuItemEntityList = new ArrayList<>();
		menuEntity = new MenuEntity("选项",  menuItemEntityList);
		menuList.add(menuEntity);

		// 添加一个菜单
		menuItemEntityList = new ArrayList<>();
		menuEntity = new MenuEntity("帮助",  menuItemEntityList);
		menuList.add(menuEntity);

		// 添加一个菜单
		menuItemEntityList = new ArrayList<>();
		menuItemEntityList.add(new MenuItemEntity("平面绘图模式"));
		menuItemEntityList.add(new MenuItemEntity("数学绘图模式"));
		menuEntity = new MenuEntity("",  menuItemEntityList);
		menuList.add(menuEntity);

		return menuList;
	}

	public TopMenuBar() {
		List<MenuEntity> menuList = initMenuData();

		for(MenuEntity menuEntity : menuList){	// 遍历生成菜单
			JMenu menu=new JMenu(menuEntity.getName());
			if(menuEntity.getIconPath() != null){	// 设置菜单图标
				ImageIcon icon=new ImageIcon(menuEntity.getIconPath());
				menu.setIcon(icon);
			}
			for(MenuItemEntity menuItemEntity : menuEntity.getMenuItemList()){	// 遍历生成菜单项
				JMenuItem menuItem=new JMenuItem(menuItemEntity.getName());
				if(menuItemEntity.getIconPath() != null){	// 设置菜单项图标
					ImageIcon icon=new ImageIcon(menuItemEntity.getIconPath());
					menuItem.setIcon(icon);
				}

				// 设置菜单项监听
				menuItem.setActionCommand(menuEntity.getName() + "-" + menuItemEntity.getName());	// 监听命令：菜单名-菜单项名
				menuItem.addActionListener(this);

				menu.add(menuItem);
			}
			this.add(menu);
		}
	}

	// 设置自定义监听器
	public void setTopMenuListener(TopMenuListener listener){
		this.listener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(listener != null){
			listener.onMenuItemClick(e.getActionCommand());
		}
	}

// 自定义监听器
	public interface TopMenuListener{
		void onMenuItemClick(String command);
	}
}
