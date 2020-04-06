package utils;

import java.awt.image.BufferedImage;

public class ImageUtil {

    // 图像复制
    public static BufferedImage imageCopy(BufferedImage image){
        BufferedImage copy =  new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
        copy.getGraphics().drawImage(image, 0, 0, image.getWidth(), image.getHeight(),null);
        return copy;
    }
}
