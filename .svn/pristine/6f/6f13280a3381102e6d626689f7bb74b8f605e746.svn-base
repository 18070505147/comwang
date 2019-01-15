package com.chejet.cloud.util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author Neo.fang
 * @creatTime 2018/12/22 0022
 */
public class ImageUtil {

    /*
     * 获得图片的格式，例如：JPEG、GIF等
     */
    public static String getImageFormat(Object obj) {
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(obj);
            Iterator<ImageReader> iterator = ImageIO.getImageReaders(iis);
            while (iterator.hasNext()) {
                ImageReader reader = (ImageReader) iterator.next();
                return reader.getFormatName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        File img = new File("C:\\Users\\Public\\Videos\\Sample Videos\\28u888piCPFD.mp4");
        System.out.println(getImageFormat(img));//JPEG
    }
}
