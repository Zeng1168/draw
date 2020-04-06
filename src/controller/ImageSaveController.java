package controller;

import controller.drawMainController.DrawMainController;
import entity.Image;
import mapper.ImageMapper;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import view.ImageSaveView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class ImageSaveController implements ImageSaveView.ImageSaveListener {
    private ImageSaveView imageSaveView;

    public ImageSaveController() {
        imageSaveView = new ImageSaveView();
        imageSaveView.setImageSaveListener(this);
    }


    @Override
    public void onQuery() {
        ImageMapper imageMapper = new ImageMapper();
        List<Image> images = imageMapper.queryAll();
        imageSaveView.setData(images);
    }

    @Override
    public void onEdit(String image) {
        BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        try {
            byte[] bytes1 = decoder.decodeBuffer(image);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bfimage = ImageIO.read(bais);

            new DrawMainController(bfimage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDelete(Integer id) {
        ImageMapper imageMapper = new ImageMapper();
        if(imageMapper.deteleImage(id) > 0){
            onQuery();
        }
    }
}
