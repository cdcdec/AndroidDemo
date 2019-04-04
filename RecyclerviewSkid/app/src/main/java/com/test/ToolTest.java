package com.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ToolTest {
    private static String startStr="<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    private static String start="<resources>";
    private static String end="</resources>";
    //在工程的java文件夹下建立ToolTest.java文件
    public static void main(String[] strings) {
        //以此文件夹下的dimens.xml文件内容为初始值参照
        File file = new File("./app/src/main/res/values-sw360dp/dimens.xml");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            System.out.print("=====================1=============");
            PrintWriter writer = new PrintWriter(file.getName(), "UTF-8");
            writer.println(startStr);
            writer.println(start);
            writer.println(end);
            writer.close();
            System.out.print("=====================2=============");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
