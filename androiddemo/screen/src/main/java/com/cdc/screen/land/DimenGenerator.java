package com.cdc.screen.land;

import java.io.File;

public class DimenGenerator {
    /**
     * 设计稿尺寸(将自己设计师的设计稿的宽度填入)
     */
    private static final int DESIGN_WIDTH = 640;



    public static void main(String[] args) {
        DimenTypes[] values = DimenTypes.values();
        for (DimenTypes value : values) {
            File file = new File("");
            MakeUtils.makeAll(DESIGN_WIDTH, value, file.getAbsolutePath());
        }

    }
}
