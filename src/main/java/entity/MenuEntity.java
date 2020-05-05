package entity;


import java.util.List;

public class MenuEntity {
    private String name;
    private String iconPath;
    private List<MenuItemEntity> menuItemList;

    public MenuEntity() {
    }

    public MenuEntity(String name) {
        this.name = name;
    }

    public MenuEntity(String name, String iconPath) {
        this.name = name;
        this.iconPath = iconPath;
    }

    public MenuEntity(String name, List<MenuItemEntity> menuItemList) {
        this.name = name;
        this.menuItemList = menuItemList;
    }

    public MenuEntity(String name, String iconPath, List<MenuItemEntity> menuItemList) {
        this.name = name;
        this.iconPath = iconPath;
        this.menuItemList = menuItemList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public List<MenuItemEntity> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItemEntity> menuItemList) {
        this.menuItemList = menuItemList;
    }
}
