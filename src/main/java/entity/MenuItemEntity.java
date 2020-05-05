package entity;

public class MenuItemEntity{
    private String name;
    private String iconPath;

    public MenuItemEntity() {
    }

    public MenuItemEntity(String name) {
        this.name = name;
    }

    public MenuItemEntity(String name, String iconPath) {
        this.name = name;
        this.iconPath = iconPath;
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
}
