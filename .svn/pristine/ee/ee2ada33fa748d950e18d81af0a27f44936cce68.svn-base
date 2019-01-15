package com.chejet.cloud.service.captchaApi.mode;

import com.google.code.kaptcha.util.Configurable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Neo.fang
 * @creatTime 2018/10/23 0023
 */
public class MyNoise extends Configurable implements com.google.code.kaptcha.NoiseProducer {


    @Override
    public void makeNoise(BufferedImage image, float factorOne, float factorTwo, float factorThree, float factorFour) {

        Color color = this.getConfig().getNoiseColor();
        int width = image.getWidth();
        int height = image.getHeight();
        Point2D[] pts = null;
        Random rand = new Random();
        CubicCurve2D cc = new CubicCurve2D.Float((float)width * factorOne, (float)height * rand.nextFloat(), (float)width * factorTwo, (float)height * rand.nextFloat(), (float)width * factorThree, (float)height * rand.nextFloat(), (float)width * factorFour, (float)height * rand.nextFloat());
        PathIterator pi = cc.getPathIterator((AffineTransform)null, 2.0D);
        Point2D[] tmp = new Point2D[200];
        int i = 0;

        while(!pi.isDone()) {
            float[] coords = new float[6];
            switch(pi.currentSegment(coords)) {
                case 0:
                case 1:
                    tmp[i] = new Point2D.Float(coords[0], coords[1]);
                default:
                    ++i;
                    pi.next();
            }
        }

        pts = new Point2D[i];
        System.arraycopy(tmp, 0, pts, 0, i);
        Graphics2D graph = (Graphics2D)image.getGraphics();
        graph.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        graph.setColor(color);

        for(i = 0; i < pts.length - 1; ++i) {
            if (i < 3) {
                graph.setStroke(new BasicStroke(0.9F * (float)(4 - i)));
            }

            graph.drawLine((int)pts[i].getX(), (int)pts[i].getY(), (int)pts[i + 1].getX(), (int)pts[i + 1].getY());
        }

        // 添加噪点
        Random random = new Random();
        float yawpRate = 0.01f;// 噪声率
        int area = (int) (yawpRate * width * height);
        for (int a = 0; a < area; a++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);

            Color color1 = new Color(image.getRGB(x, y));
            int red = color1.getRed();
            int green = color1.getGreen();
            int blue = color1.getBlue();
            int rGray = red*(1/3);
            int gGray = green*(2/3);
            int bGray = blue*(1/10);
            Color grayScale = new Color(rGray, gGray, bGray);

            image.setRGB(x, y, grayScale.getRGB());
        }

        graph.dispose();
    }
}
