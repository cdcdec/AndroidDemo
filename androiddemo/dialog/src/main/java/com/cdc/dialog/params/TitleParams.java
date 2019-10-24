package com.cdc.dialog.params;

import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Gravity;

import com.cdc.dialog.util.DialogDefaultValue;

public class TitleParams implements Parcelable {
    /**
     * 标题
     */
    public String text;
    /**
     * 内间距 [left, top, right, bottom]
     */
    public int[] padding = DialogDefaultValue.TITLE_PADDING;
    /**
     * 标题高度
     */
    public int height = 0;
    /**
     * 标题字体大小
     */
    public int textSize = DialogDefaultValue.TITLE_TEXT_SIZE;
    /**
     * 标题字体颜色
     */
    public int textColor = DialogDefaultValue.TITLE;
    /**
     * 标题背景颜色
     */
    public int backgroundColor;
    public int gravity = Gravity.CENTER;
    /**
     * 字样式
     * {@linkplain Typeface#NORMAL NORMAL}
     * {@linkplain Typeface#BOLD BOLD}
     * {@linkplain Typeface#ITALIC ITALIC}
     * {@linkplain Typeface#BOLD_ITALIC BOLD_ITALIC}
     */
    public int styleText = Typeface.NORMAL;
    public int icon;

    public TitleParams(){

    }

    protected TitleParams(Parcel in) {
        text = in.readString();
        padding = in.createIntArray();
        height = in.readInt();
        textSize = in.readInt();
        textColor = in.readInt();
        backgroundColor = in.readInt();
        gravity = in.readInt();
        styleText = in.readInt();
        icon = in.readInt();
    }

    public static final Creator<TitleParams> CREATOR = new Creator<TitleParams>() {
        @Override
        public TitleParams createFromParcel(Parcel in) {
            return new TitleParams(in);
        }

        @Override
        public TitleParams[] newArray(int size) {
            return new TitleParams[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeIntArray(padding);
        dest.writeInt(height);
        dest.writeInt(textSize);
        dest.writeInt(textColor);
        dest.writeInt(backgroundColor);
        dest.writeInt(gravity);
        dest.writeInt(styleText);
        dest.writeInt(icon);
    }
}
