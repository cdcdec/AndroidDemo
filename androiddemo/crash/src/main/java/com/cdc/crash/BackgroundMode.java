package com.cdc.crash;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class BackgroundMode{
    public static final int BACKGROUND_MODE_SILENT = 0;
    public static final int BACKGROUND_MODE_SHOW_CUSTOM = 1;
    public static final int BACKGROUND_MODE_CRASH = 2;

    @IntDef({BACKGROUND_MODE_CRASH, BACKGROUND_MODE_SHOW_CUSTOM, BACKGROUND_MODE_SILENT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BackGroundMode{
        //I hate empty blocks
    }



}
