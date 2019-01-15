package com.chejet.cloud.service.captchaApi.mode;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

/**
 * @author Neo.fang
 * @creatTime 2018/10/12 0012
 */
public abstract class GoogleKaptcha {

    // 默认大小
    private String width = "140";// 图片宽
    private String height = "60";// 图片高

    protected abstract void setInCache(String captcha);

    /**
     * 生成随机图片
     *
     * @throws IOException
     */
    public BufferedImage generate(Integer x, Integer y) throws IOException {

        if (x != null && y != null){
            boolean isControl = x > 0 && x < 200 && y > 0 && y < 100;
            if (isControl) {
                width = String.valueOf(x);
                height = String.valueOf(y);
            }
        }

        DefaultKaptcha captchaProducer = new DefaultKaptcha();

        Properties properties = new Properties();
        // 验证码大小
        properties.setProperty("kaptcha.image.width", width);
        properties.setProperty("kaptcha.image.height", height);
        // 无干扰,可以自定义干扰
        properties.setProperty("kaptcha.noise.impl", "com.chejet.cloud.service.captchaApi.mode.MyNoise");
        properties.setProperty("kaptcha.noise.color", getSpecificRandColor());

        // 验证码内容
        properties.setProperty("kaptcha.textproducer.char.string", "023456789ABCDEFGHJKLMNPQRSTUVWXYZ");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        // 字体间距
        properties.setProperty("kaptcha.textproducer.char.space", "6");
        // 字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", getSpecificRandColor());

        // 是否有边框
        properties.setProperty("kaptcha.border", "no");
        // 图片样式：默认值水纹 水纹com.google.code.kaptcha.impl.WaterRipple;鱼眼com.google.code.kaptcha.impl.FishEyeGimpy;阴影com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");

        // 背景颜色渐变，开始颜色，默认值lightGray, 背景颜色渐变， 结束颜色，默认值white
        properties.setProperty("kaptcha.background.clear.from", "244,251,244");
        properties.setProperty("kaptcha.background.clear.to", "244,251,244");

        captchaProducer.setConfig(new Config(properties));

        String capText = captchaProducer.createText();

        // store the text
        setInCache(capText);

        // create the image with the text
        BufferedImage image = captchaProducer.createImage(capText);

        return image;
    }


    // 获取随机颜色
    /*
     * 获得颜色
     */
    private static String getRandColor() {
        Random random = new Random();
        int fc = 0;
        int bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        String rgb = r+","+g+","+b;
        return rgb;
    }

    /*
     * 获得颜色
     */
    private static String getSpecificRandColor() {
        Random random = new Random();
        String rgb[] = {
                "0,0,139",
                "139,0,0",
                "28,28,28",
                "0,0,205",
                "0,0,238",
                "39,64,139",
                "139,34,82",
                "139,0,0",
                "238,0,0",
                "205,0,0",
                "0,100,0",
                "0,0,205",
                "0,0,128",
                "25,25,112",
                "0,0,0",
        };
        return rgb[random.nextInt(rgb.length)];
    }
}
