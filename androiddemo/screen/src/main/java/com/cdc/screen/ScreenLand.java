package com.cdc.screen;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class ScreenLand {
    private static final float DEFAULT_WIDTH=640.0f;
    private static final float DEFAULT_WIDTH_SP=50.0f;
    private static final float DEFAULT_DENSITY=2.0f;
    public static void main(String[] args) {
        try {
            genBaseDimensSize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gen();
    }
    /**
     * 生成最基本的尺寸
     */
    private static void genBaseDimensSize() throws IOException {
        File file=new File("./app/src/main/res/values/dimens.xml");
        if(!file.exists()){
            file.createNewFile();
        }
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        out.write("\r\n");
        out.write("<resources>");
        out.write("\r\n");
        float ii=0f;
        for(int i=1;i<=DEFAULT_WIDTH;i++){
            out.write("<dimen name=\"dp"+i+"\">");
            out.write(i+"dp");
            out.write("</dimen>");
            out.write("\r\n");

            out.write("<dimen name=\"dp"+i+"_5"+"\">");
            out.write((i+0.5f)+"dp");
            out.write("</dimen>");
            out.write("\r\n");


        }
        for(int j=1;j<=DEFAULT_WIDTH_SP;j++){
            out.write("<dimen name=\"sp"+j+"\">");
            out.write(j+"sp");
            out.write("</dimen>");
            out.write("\r\n");


            out.write("<dimen name=\"sp"+j+"_5"+"\">");
            out.write((j+0.5f)+"sp");
            out.write("</dimen>");
            out.write("\r\n");


        }
        out.write("</resources>");
        out.close();
    }

    public static void gen() {
        /**
         * 密度因子  int density;
         */

        /**
         * 屏幕相对宽度
         */
        int width;
        /**
         * 1.根据UI画布大小比例进行换算，假设UI图分辨率为1334x750
         * 设备默认缩放因子密度为 density  = 320 / 160 , 即 density = 2
         * 宽度 width = 750
         * 可选项，根据你实际的UI设计图来定义
         */
        width = (int) (DEFAULT_WIDTH / DEFAULT_DENSITY);
        /**
         * 2.根据UI画布大小比例进行换算，假设UI图分辨率为1080x1920
         * 高分率缩放因子密度一般为 density = 80 / 160 , 即 density = 3
         * 宽度 width = 1080
         * 可选项，根据你实际的UI设计图来定义
         */
       /* density = 3;
        width = 1080 / density;*/
        /**
         * 执行生成适配的dimens.xml文件
         */
        gen(width);
    }

    /**
     * 生成对应的适配的dimens.xml文件
     * @param width
     */
    public static void gen(int width) {

        File file = new File("./app/src/main/res/values/dimens.xml");
        BufferedReader reader = null;
        StringBuilder w240 = new StringBuilder();
        StringBuilder w320 = new StringBuilder();
        StringBuilder w360 = new StringBuilder();
        StringBuilder w380 = new StringBuilder();
        StringBuilder w400 = new StringBuilder();
        StringBuilder w410 = new StringBuilder();
        StringBuilder w420 = new StringBuilder();
        StringBuilder w440 = new StringBuilder();
        StringBuilder w480 = new StringBuilder();
        StringBuilder w560 = new StringBuilder();
        StringBuilder w600 = new StringBuilder();
        StringBuilder w720 = new StringBuilder();
        StringBuilder w760 = new StringBuilder();
        StringBuilder w800 = new StringBuilder();
        StringBuilder w900 = new StringBuilder();
        StringBuilder w1080 = new StringBuilder();
        StringBuilder w1200 = new StringBuilder();
        StringBuilder w1440 = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 1;

            while ((tempString = reader.readLine()) != null) {

                if (tempString.contains("</dimen>")) {
                    //tempString = tempString.replaceAll(" ", "");
                    String start = tempString.substring(0, tempString.indexOf(">") + 1);
                    String end = tempString.substring(tempString.lastIndexOf("<") - 2);
                    float num = Float.valueOf(tempString.substring(tempString.indexOf(">") + 1, tempString.indexOf("</dimen>") - 2));



                  w240.append(start).append(Math.round(num * 240 / width)).append(end).append("\n");
                    w320.append(start).append(Math.round(num * 320 / width)).append(end).append("\n");
                    w360.append(start).append(Math.round(num * 360 / width)).append(end).append("\n");

                    w380.append(start).append(Math.round(num * 384 / width)).append(end).append("\n");
                    w400.append(start).append(Math.round(num * 400 / width)).append(end).append("\n");
                    w410.append(start).append(Math.round(num * 410 / width)).append(end).append("\n");
                    w420.append(start).append(Math.round(num * 420 / width)).append(end).append("\n");
                    w440.append(start).append(Math.round(num * 440 / width)).append(end).append("\n");

                    w480.append(start).append(Math.round(num * 480 / width)).append(end).append("\n");
                    w560.append(start).append(Math.round(num * 560 / width)).append(end).append("\n");

                    w600.append(start).append(Math.round(num * 600 / width)).append(end).append("\n");
                    w720.append(start).append(Math.round(num * 720 / width)).append(end).append("\n");

                    w760.append(start).append(Math.round(num * 760 / width)).append(end).append("\n");
                    w800.append(start).append(Math.round(num * 800 / width)).append(end).append("\n");
                    w900.append(start).append(Math.round(num * 900 / width)).append(end).append("\n");

                    w1080.append(start).append(Math.round(num * 1080 / width)).append(end).append("\n");
                    w1200.append(start).append(Math.round(num * 1200 / width)).append(end).append("\n");


                    w1440.append(start).append(Math.round(num * 1440 / width)).append(end).append("\n");




                } else {
                    w240.append(tempString).append("\n");
                    w320.append(tempString).append("\n");
                    w360.append(tempString).append("\n");
                    w380.append(tempString).append("\n");
                    w400.append(tempString).append("\n");
                    w410.append(tempString).append("\n");
                    w420.append(tempString).append("\n");
                    w440.append(tempString).append("\n");
                    w480.append(tempString).append("\n");
                    w560.append(tempString).append("\n");
                    w600.append(tempString).append("\n");
                    w720.append(tempString).append("\n");

                    w760.append(tempString).append("\n");
                    w800.append(tempString).append("\n");
                    w900.append(tempString).append("\n");

                    w1080.append(tempString).append("\n");
                    w1200.append(tempString).append("\n");
                    w1440.append(tempString).append("\n");
                }
                line++;
            }
            reader.close();
            String w240file = "./app/src/main/res/values-w240dp/dimens.xml";
            String w320file = "./app/src/main/res/values-w320dp/dimens.xml";
            String w360file = "./app/src/main/res/values-w360dp/dimens.xml";
            String w380file = "./app/src/main/res/values-w380dp/dimens.xml";
            String w400file = "./app/src/main/res/values-w400dp/dimens.xml";
            String w410file = "./app/src/main/res/values-w410dp/dimens.xml";
            String w420file = "./app/src/main/res/values-w420dp/dimens.xml";
            String w440file = "./app/src/main/res/values-w440dp/dimens.xml";
            String w480file = "./app/src/main/res/values-w480dp/dimens.xml";
            String w560file = "./app/src/main/res/values-w560dp/dimens.xml";
            String w600file = "./app/src/main/res/values-w600dp/dimens.xml";
            String w720file = "./app/src/main/res/values-w720dp/dimens.xml";

            String w760file = "./app/src/main/res/values-w760dp/dimens.xml";
            String w800file = "./app/src/main/res/values-w800dp/dimens.xml";
            String w900file = "./app/src/main/res/values-w900dp/dimens.xml";

            String w1080file = "./app/src/main/res/values-w1080dp/dimens.xml";
            String w1200file = "./app/src/main/res/values-w1200dp/dimens.xml";
            String w1440file = "./app/src/main/res/values-w1440dp/dimens.xml";
           writeFile(w240file, w240.toString());
            writeFile(w320file, w320.toString());
            writeFile(w360file, w360.toString());
            writeFile(w380file, w380.toString());
            writeFile(w400file, w410.toString());
            writeFile(w410file, w410.toString());
            writeFile(w420file, w410.toString());
            writeFile(w440file, w410.toString());
            writeFile(w480file, w480.toString());
            writeFile(w560file, w480.toString());
            writeFile(w600file, w600.toString());
            writeFile(w720file, w720.toString());

            writeFile(w760file, w760.toString());
            writeFile(w800file, w800.toString());
            writeFile(w900file, w900.toString());

            writeFile(w1080file, w1080.toString());
            writeFile(w1200file, w1200.toString());
            writeFile(w1440file, w1440.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private static void writeFile(String file, String text) throws IOException {
        createFile(file);
        PrintWriter out = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            out = new PrintWriter(new BufferedWriter(fileWriter));
            out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }

    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if (file.exists()) {
            //System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            //System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            //System.out.println("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {
                //   System.out.println("创建目标文件所在目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                // System.out.println("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                //  System.out.println("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }
    }
}
