package com.janson.jvm.nativ;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Description: 模拟一个Native Memory泄漏的场景
 * https://www.jb51.net/article/189865.htm
 * @Author: Janson
 * @Date: 2024/9/29 11:33
 **/
public class NativeMemoryLeakDemo {

    public static void main(String[] args) throws IOException, FontFormatException {
        while (true) {
            test();
        }
    }

    private static void test() throws IOException, FontFormatException {
        Resource resource = new ClassPathResource("仿宋_GB2312.ttf");
        Font rawFont = Font.createFont(Font.TRUETYPE_FONT, resource.getFile());
        Font usedFont = rawFont.deriveFont(Font.PLAIN, 30);
        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.setFont(usedFont);
        g2.drawString("hello world", 16, 35);
//        System.out.println("success");
    }

}