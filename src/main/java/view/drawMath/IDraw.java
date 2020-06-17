package view.drawMath;

public interface IDraw {
    void clean();   // 清空
    void saveToDataBase();  // 存到数据库
    void openDataBase();    // 打开数据库文件
    void saveToFile();    // 打开数据库文件
}
