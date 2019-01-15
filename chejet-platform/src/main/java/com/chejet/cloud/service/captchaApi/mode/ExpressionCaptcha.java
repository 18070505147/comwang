package com.chejet.cloud.service.captchaApi.mode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author Neo.fang
 * @creatTime 2018/10/11 0011
 */
public abstract class ExpressionCaptcha {

    private Random random = new Random();
    private String randNum = "0123456789";// 随机产生的字符串
    private String plusSign = "+";
    private String equalSign = "=";

    // 默认大小
    private int width = 80;// 图片宽
    private int height = 34;// 图片高

    private int lineSize = 40;// 干扰线数量
    private int stringNum = 4;// 随机产生字符数量

    private int fontSize = 18;

    private int tempX = 13;
    private int tempY = 20;

    protected abstract void setInCache(String captcha);

    /**
     * 生成随机图片
     *
     * @throws IOException
     */
    public BufferedImage generate(Integer x, Integer y) throws IOException {
        // 大小范围控制，为空或超出范围时采用默认
        if (x != null && y != null){
            boolean isControl = x > 0 && x < 200 && y > 0 && y < 100;
            if (isControl) {
                width = x;
                height = y;
                lineSize = width / 2;
                fontSize = height / 2 + 2;
                tempX = new Double(0.1625 * width).intValue();
                tempY = new Double(0.6 * height).intValue();
            }
        }


        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, fontSize));
        g.setColor(getRandColor(110, 133));
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g, width, height);
        }
        // 绘制随机字符
        Integer expressionNum = null;
        for (int i = 1; i <= stringNum; i++) {
            expressionNum = drowString(g, expressionNum, i);
        }
        setInCache(String.valueOf(expressionNum));

        g.dispose();
        // createImage(image, "D://1123.jpg");
        return image;
    }

    /*
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, fontSize);
    }

    /*
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /*
     * 绘制字符串
     */
    private Integer drowString(Graphics g, Integer expressionNum, int i) {

        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));

        String rand = "";
        if (1 == i){
            g.setFont(new Font("Fixedsys", Font.CENTER_BASELINE, 22));
            rand = getRandomNum(random.nextInt(randNum.length()));
            expressionNum = Integer.valueOf(rand);
        }else if (2 == i){
            g.setFont(new Font("Fixedsys", Font.ITALIC, fontSize));
            rand = plusSign;
        }else if (3 == i){
            g.setFont(new Font("Fixedsys", Font.CENTER_BASELINE, fontSize));
            rand = String.valueOf(getRandomNum(random.nextInt(randNum.length())));
            expressionNum = expressionNum + Integer.valueOf(rand);
        }else if (4 == i){
            g.setFont(new Font("Fixedsys", Font.CENTER_BASELINE, fontSize));
            rand = equalSign;
        }

        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, tempX * i, tempY);
        return expressionNum;
    }

    /*
     * 绘制干扰线
     */
    private void drowLine(Graphics g, Integer width, Integer height) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /*
     * 获取随机的字符
     */
    private String getRandomNum(int num) {
        return String.valueOf(randNum.charAt(num));
    }

    /**
     * 生成图片文件
     */
    private void createImage(BufferedImage image, String file){
        if (image != null){
            try {
                String formatName = file.substring(file.lastIndexOf(".") + 1);
                ImageIO.write(image, formatName, new File(file));

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
