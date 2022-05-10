package com.janson.design.decorator.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/10 5:06 下午
 */
public class InputTest {
    public static void main(String[] args) {
        int c;
        try {
            InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("/Users/shanjian/IdeaProjects/study/springboot-demo/src/main/java/com/janson/design/decorator/io/test.txt")));
            while ((c = in.read()) > 0) {
                System.out.println((char) c);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
