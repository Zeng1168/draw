package utils;

public enum DrawMathMode{
    LINE("直线", true),
    TRIANGLE("三角形", true),
    RECTANGEL("矩形", true),
    FILL_RECTANGEL("实心矩形", true),
    CIRCLE("圆形", true),
    ROSE("玫瑰", false),
    CONE("圆锥", false),
    SPHERE("球体", false),
    BOX("长方体", false);

    private String mode;
    private Boolean isPlanform; // 是否为平面图

    DrawMathMode(String mode, Boolean isPlanform) {
        this.mode = mode;
        this.isPlanform = isPlanform;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }


    // 根据字符串获取对应enum
    public static DrawMathMode getModeByStr(String str) {
        for (DrawMathMode dm : values()) {
            if (dm.getMode().equals(str)) {
                return dm;
            }
        }
        return null;
    }


}
