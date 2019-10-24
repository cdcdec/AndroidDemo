package com.cdc.dialog.params;

import android.content.DialogInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;

import com.cdc.dialog.util.DialogDefaultValue;

public class DialogParams implements Parcelable{
    /**
     * 对话框的位置
     */
    public int gravity = Gravity.NO_GRAVITY;
    /**
     * 是否触摸外部关闭
     */
    public boolean canceledOnTouchOutside = true;
    /**
     * 返回键是否关闭
     */
    public boolean cancelable = true;
    /**
     * 对话框透明度，范围：0-1；1不透明
     */
    public float alpha = DialogDefaultValue.DIALOG_ALPHA;

    /**
     * 对话框宽度，范围：0-1；1整屏宽
     */
    public float width = DialogDefaultValue.DIALOG_WIDTH;
    /**
     * 对话框与屏幕边距 px
     */
    public int[] mPadding;
    /**
     * 对话框弹出动画,StyleRes
     */
    public int animStyle;
    /**
     * 对话框刷新动画，AnimRes
     */
    public int refreshAnimation;
    /**
     * 对话框背景是否昏暗，默认true
     */
    public boolean isDimEnabled = true;
    /**
     * 对话框的背景色
     */
    public int backgroundColor = DialogDefaultValue.DIALOG_BACKGROUND;
    /**
     * 对话框的圆角半径
     */
    public int radius = DialogDefaultValue.DIALOG_RADIUS;
    /**
     * 对话框 x 坐标偏移 px
     */
    public int xOff;
    /**
     * 对话框 y 坐标偏移 px
     */
    public int yOff = -1;
    /**
     * 按下颜色值
     */
    public int backgroundColorPress = DialogDefaultValue.DIALOG_BACKGROUND_PRESS;
    public float maxHeight;//最大高度
    public int systemUiVisibility;
    /**
     * 延迟弹出
     */
    public int delayShow;


    /**
     * 确定按钮点击事件
     */
    public View.OnClickListener clickPositiveListener;
    /**
     * 中间按钮点击事件
     */
    public View.OnClickListener clickNeutralListener;
    /**
     * 取消按钮点击事件
     */
    public View.OnClickListener clickNegativeListener;
    /**
     * 输入框确定事件
     */
    public OnInputClickListener inputListener;

    /**
     * dialog 关闭事件
     */
    public DialogInterface.OnDismissListener dismissListener;
    /**
     * dialog 取消事件
     */
    public DialogInterface.OnCancelListener cancelListener;
    /**
     * dialog 显示事件
     */
    public DialogInterface.OnShowListener showListener;

    public DialogParams dialogParams;
    public TitleParams titleParams;
    public SubTitleParams subTitleParams;
    public TextParams textParams;
    public ButtonParams negativeParams;
    public ButtonParams positiveParams;
    public ProgressParams progressParams;
    public InputParams inputParams;
    public ButtonParams neutralParams;
    public int bodyViewId;
    public View bodyView;

    public OnCreateBodyViewListener createBodyViewListener;
    public OnCreateProgressListener createProgressListener;
    public OnCreateTitleListener createTitleListener;
    public OnCreateTextListener createTextListener;
    public OnCreateInputListener createInputListener;
    public OnCreateButtonListener createButtonListener;
    public OnInputCounterChangeListener inputCounterChangeListener;
    public boolean itemListViewType;//true=ListView; false=RecyclerView
    public CloseParams closeParams;

    public DialogParams() {
    }
    protected DialogParams(Parcel in) {
        gravity = in.readInt();
        canceledOnTouchOutside = in.readByte() != 0;
        cancelable = in.readByte() != 0;
        alpha = in.readFloat();
        width = in.readFloat();
        mPadding = in.createIntArray();
        animStyle = in.readInt();
        refreshAnimation = in.readInt();
        isDimEnabled = in.readByte() != 0;
        backgroundColor = in.readInt();
        radius = in.readInt();
        xOff = in.readInt();
        yOff = in.readInt();
        backgroundColorPress = in.readInt();
        maxHeight = in.readFloat();
        systemUiVisibility = in.readInt();
        delayShow = in.readInt();
        dialogParams = in.readParcelable(DialogParams.class.getClassLoader());
        titleParams = in.readParcelable(TitleParams.class.getClassLoader());
        subTitleParams = in.readParcelable(SubTitleParams.class.getClassLoader());
        textParams = in.readParcelable(TextParams.class.getClassLoader());
        negativeParams = in.readParcelable(ButtonParams.class.getClassLoader());
        positiveParams = in.readParcelable(ButtonParams.class.getClassLoader());
        progressParams = in.readParcelable(ProgressParams.class.getClassLoader());
        inputParams = in.readParcelable(InputParams.class.getClassLoader());
        neutralParams = in.readParcelable(ButtonParams.class.getClassLoader());
        bodyViewId = in.readInt();
        itemListViewType = in.readByte() != 0;
        closeParams = in.readParcelable(CloseParams.class.getClassLoader());
    }

    public static final Creator<DialogParams> CREATOR = new Creator<DialogParams>() {
        @Override
        public DialogParams createFromParcel(Parcel in) {
            return new DialogParams(in);
        }

        @Override
        public DialogParams[] newArray(int size) {
            return new DialogParams[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(gravity);
        dest.writeByte((byte) (canceledOnTouchOutside ? 1 : 0));
        dest.writeByte((byte) (cancelable ? 1 : 0));
        dest.writeFloat(alpha);
        dest.writeFloat(width);
        dest.writeIntArray(mPadding);
        dest.writeInt(animStyle);
        dest.writeInt(refreshAnimation);
        dest.writeByte((byte) (isDimEnabled ? 1 : 0));
        dest.writeInt(backgroundColor);
        dest.writeInt(radius);
        dest.writeInt(xOff);
        dest.writeInt(yOff);
        dest.writeInt(backgroundColorPress);
        dest.writeFloat(maxHeight);
        dest.writeInt(systemUiVisibility);
        dest.writeInt(delayShow);
        dest.writeParcelable(dialogParams, flags);
        dest.writeParcelable(titleParams, flags);
        dest.writeParcelable(subTitleParams, flags);
        dest.writeParcelable(textParams, flags);
        dest.writeParcelable(negativeParams, flags);
        dest.writeParcelable(positiveParams, flags);
        dest.writeParcelable(progressParams, flags);
        dest.writeParcelable(inputParams, flags);
        dest.writeParcelable(neutralParams, flags);
        dest.writeInt(bodyViewId);
        dest.writeByte((byte) (itemListViewType ? 1 : 0));
        dest.writeParcelable(closeParams, flags);
    }
}
