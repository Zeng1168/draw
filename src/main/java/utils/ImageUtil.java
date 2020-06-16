package utils;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtil {

    /**
     * 图像复制
     *
     * @param image
     *      要复制的iamge对象
     * @return
     *      复制的新image对象
     */
    public static BufferedImage imageCopy(BufferedImage image){
        BufferedImage copy =  new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB); // 创建一个新的BufferedImage

        copy.getGraphics().drawImage(image, 0, 0, image.getWidth(), image.getHeight(),null);    // 将目标image复制到新创建的image上

        return copy;
    }

    /**
     * 将透明前景图片绘制到背景图片上
     * 组合成一张新的图片
     *
     * @param backImage
     *      背景图片
     * @param foreImage
     *      前景图片
     * @return
     *      组合的图片
     */
    public static BufferedImage imageCombine(BufferedImage backImage, BufferedImage foreImage){
        BufferedImage combineImage = imageCopy(backImage);  // 复制背景图，用于绘制
        Graphics2D g2 = combineImage.createGraphics();  // 获取Graphics2D

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1.0f)); // 设置为透明覆盖
        g2.drawImage(foreImage, 0, 0, backImage.getWidth(), backImage.getHeight(),null);    // 绘制
        g2.dispose();   // 释放Graphics2D

        return combineImage;
    }

    /**
     * 创建空白的image图像对象
     *
     * @param width
     *      图像宽度
     * @param height
     *      图像高度
     * @return
     *      创建的图像
     */
    public static BufferedImage createBlankImage(int width, int height){
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);    // 创建空白图像
        Graphics g = buffImg.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,buffImg.getWidth(), buffImg.getHeight());
        return buffImg;
    }

    /**
     * 创建空白且透明的image图像对象
     *
     * @param width
     *      图像宽度
     * @param height
     *      图像高度
     * @return
     *      创建的图像
     */
    public static BufferedImage createBlankLacencyImage(int width, int height){
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);    // 创建空白图像
        Graphics2D g2 = buffImg.createGraphics();   // 获取Graphics2D

        buffImg = g2.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);   // 设置透明
        g2.dispose();

        return buffImg;
    }

    /**
     * 消除绘图锯齿
     */
    public static void cleanTooth(Graphics2D g2){

        //消除文字锯齿
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //消除画图锯齿
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
}
