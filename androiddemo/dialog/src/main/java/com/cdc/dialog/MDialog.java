package com.cdc.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cdc.dialog.base.BaseDialog;
import com.cdc.dialog.base.OnDialogInternalListener;
import com.cdc.dialog.params.ButtonParams;
import com.cdc.dialog.params.CloseParams;
import com.cdc.dialog.params.ConfigButton;
import com.cdc.dialog.params.ConfigDialog;
import com.cdc.dialog.params.ConfigInput;
import com.cdc.dialog.params.ConfigProgress;
import com.cdc.dialog.params.ConfigSubTitle;
import com.cdc.dialog.params.ConfigText;
import com.cdc.dialog.params.ConfigTitle;
import com.cdc.dialog.params.DialogParams;
import com.cdc.dialog.params.InputParams;
import com.cdc.dialog.params.OnCreateBodyViewListener;
import com.cdc.dialog.params.OnCreateButtonListener;
import com.cdc.dialog.params.OnCreateInputListener;
import com.cdc.dialog.params.OnCreateProgressListener;
import com.cdc.dialog.params.OnCreateTextListener;
import com.cdc.dialog.params.OnCreateTitleListener;
import com.cdc.dialog.params.OnInputClickListener;
import com.cdc.dialog.params.OnInputCounterChangeListener;
import com.cdc.dialog.params.ProgressParams;
import com.cdc.dialog.params.SubTitleParams;
import com.cdc.dialog.params.TextParams;
import com.cdc.dialog.params.TitleParams;
import com.cdc.dialog.util.DialogDefaultValue;
public class MDialog extends BaseDialog implements DialogInterface.OnShowListener, OnDialogInternalListener {
    private static final String SAVED_PARAMS = "dialog:params";
    private DialogParams mParams;
    private MDialog mDialog;

    private MDialog() {

    }

    public static MDialog newDialog(DialogParams params) {
        MDialog circleDialog = new MDialog();
        circleDialog.mParams = params;
        Bundle bundle = new Bundle();
        bundle.putParcelable(SAVED_PARAMS, params);
        circleDialog.setArguments(bundle);
        return circleDialog;
    }

    void show(FragmentManager manager) {
        final FragmentTransaction transaction = manager.beginTransaction();
        if (isAdded()) {
            transaction.remove(this).commit();
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(this, "MDialog");
        transaction.commitAllowingStateLoss();
        //mDialog.show(manager, "MDialog");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mParams = savedInstanceState.getParcelable(SAVED_PARAMS);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_PARAMS, mParams);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mParams != null && mParams.dismissListener != null) {
            mParams.dismissListener.onDismiss(dialog);
        }
        if (mParams != null && mParams.cancelListener != null) {
            mParams.cancelListener.onCancel(dialog);
        }
        mParams = null;
    }


    @Override
    public View createView(Context context, LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.layout_base, container);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        DialogParams dialogParams = mParams;
        setGravity(dialogParams.gravity);
        setCanceledOnTouchOutside(dialogParams.canceledOnTouchOutside);
        setCanceledBack(dialogParams.cancelable);
        setWidth(dialogParams.width);
        setMaxHeight(dialogParams.maxHeight);
        int[] padding = dialogParams.mPadding;
        if (padding != null) {
            setPadding(padding[0], padding[1], padding[2], padding[3]);
        }
        setAnimations(dialogParams.animStyle);
        setDimEnabled(dialogParams.isDimEnabled);
        setRadius(dialogParams.radius);
        setAlpha(dialogParams.alpha);
        setX(dialogParams.xOff);
        setY(dialogParams.yOff);
        if (mParams != null && mParams.inputParams != null && mParams.inputParams.showSoftKeyboard) {
            setSoftInputMode();
        }
        setSystemUiVisibility(dialogParams.systemUiVisibility);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        final FragmentTransaction transaction = manager.beginTransaction();
        if (isAdded()) {
            transaction.remove(this).commit();
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(this, tag);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setOnShowListener(this);
        }
    }

    @Override
    public void onShow(DialogInterface dialog) {
        if (mParams != null && mParams.showListener != null) {
            mParams.showListener.onShow(dialog);
        }
    }


    @Override
    public void dialogAtLocation(int x, int y) {
        Dialog dialog = getDialog();
        if (dialog == null) return;

        setX(x);
        setY(y);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.x = x;
        wlp.y = y;
        window.setAttributes(wlp);
    }

    @Override
    public void dialogDismiss() {
        dismissAllowingStateLoss();
    }

    @Override
    public int[] getScreenSize() {
        return getSystemBarConfig().getScreenSize();
    }

    @Override
    public int getStatusBarHeight() {
        return getSystemBarConfig().getStatusBarHeight();
    }



    public static class Builder {
        private MDialog mDialog;
        private DialogParams dialogParams;
        public Builder(){
            dialogParams=new DialogParams();
            mDialog=MDialog.newDialog(dialogParams);
        }

        /**
         * 设置对话框位置
         *
         * @param gravity 位置
         * @return this Builder
         */
        public Builder setGravity(int gravity) {
            dialogParams.gravity = gravity;
            return this;
        }

        /**
         * 设置对话框点击外部关闭
         *
         * @param cancel true允许
         * @return this Builder
         */
        public Builder setCanceledOnTouchOutside(boolean cancel) {
            dialogParams.canceledOnTouchOutside = cancel;
            return this;
        }

        /**
         * 设置对话框返回键关闭
         *
         * @param cancel true允许
         * @return this Builder
         */
        public Builder setCancelable(boolean cancel) {
            dialogParams.cancelable = cancel;
            return this;
        }

        /**
         * 设置对话框宽度
         *
         * @param width 0.0 - 1.0
         * @return this Builder
         */
        public Builder setWidth(@FloatRange(from = 0.0, to = 1.0) float width) {
            dialogParams.width = width;
            return this;
        }

        /**
         * 设置对话框最大高度
         *
         * @param maxHeight 0.0 - 1.0
         * @return this Builder
         */
        public Builder setMaxHeight(@FloatRange(from = 0.0, to = 1.0) float maxHeight) {
            dialogParams.maxHeight = maxHeight;
            return this;
        }

        /**
         * 设置对话框圆角
         *
         * @param radius 半径
         * @return this Builder
         */
        public Builder setRadius(int radius) {
            dialogParams.radius = radius;
            return this;
        }

        /**
         * 设置对话框y 坐标偏移
         *
         * @param yOff y 坐标偏移量，默认-1
         * @return this Builder
         */
        public Builder setYoff(int yOff) {
            dialogParams.yOff = yOff;
            return this;
        }

        public Builder bottomFull() {
            dialogParams.gravity = Gravity.BOTTOM;
            dialogParams.radius = 0;
            dialogParams.width = 1f;
            dialogParams.yOff = 0;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener listener) {
            dialogParams.dismissListener = listener;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener listener) {
            dialogParams.cancelListener = listener;
            return this;
        }

        public Builder setOnShowListener(DialogInterface.OnShowListener listener) {
            dialogParams.showListener = listener;
            return this;
        }

        public Builder configDialog(@NonNull ConfigDialog configDialog) {
            configDialog.onConfig(dialogParams);
            return this;
        }

        public Builder setTitle(@NonNull String text) {
            newTitleParams();
            dialogParams.titleParams.text = text;
            return this;
        }

        public Builder setTitleIcon(@DrawableRes int icon) {
            newTitleParams();
            dialogParams.titleParams.icon = icon;
            return this;
        }

        public Builder setTitleColor(@ColorInt int color) {
            newTitleParams();
            dialogParams.titleParams.textColor = color;
            return this;
        }

        public Builder configTitle(@NonNull ConfigTitle configTitle) {
            newTitleParams();
            configTitle.onConfig(dialogParams.titleParams);
            return this;
        }

        public Builder setOnCreateTitleListener(@NonNull OnCreateTitleListener listener) {
            dialogParams.createTitleListener = listener;
            return this;
        }

        public Builder setSubTitle(@NonNull String text) {
            newSubTitleParams();
            dialogParams.subTitleParams.text = text;
            return this;
        }

        public Builder setSubTitleColor(@ColorInt int color) {
            newSubTitleParams();
            dialogParams.subTitleParams.textColor = color;
            return this;
        }

        public Builder configSubTitle(@NonNull ConfigSubTitle configSubTitle) {
            newSubTitleParams();
            configSubTitle.onConfig(dialogParams.subTitleParams);
            return this;
        }

        public Builder setText(@NonNull String text) {
            newTextParams();
            dialogParams.textParams.text = text;
            return this;
        }

        public Builder setTextColor(@ColorInt int color) {
            newTextParams();
            dialogParams.textParams.textColor = color;
            return this;
        }

        public Builder configText(@NonNull ConfigText configText) {
            newTextParams();
            configText.onConfig(dialogParams.textParams);
            return this;
        }

        public Builder setOnCreateTextListener(OnCreateTextListener listener) {
            dialogParams.createTextListener = listener;
            return this;
        }
        /**
         * 设置进度条文本
         *
         * @param text 进度条文本，style = 水平样式时，支持String.format() 例如：已经下载%s
         * @return this Builder
         */
        public Builder setProgressText(@NonNull String text) {
            newProgressParams();
            dialogParams.progressParams.text = text;
            return this;
        }

        /**
         * 设置进度条样式
         *
         * @param style {@link ProgressParams#STYLE_HORIZONTAL 水平样式} or
         *              {@link ProgressParams#STYLE_SPINNER}
         * @return this Builder
         */
        public Builder setProgressStyle(int style) {
            newProgressParams();
            dialogParams.progressParams.style = style;
            return this;
        }

        public Builder setProgress(int max, int progress) {
            newProgressParams();
            ProgressParams progressParams = dialogParams.progressParams;
            progressParams.max = max;
            progressParams.progress = progress;
            return this;
        }

        public Builder setProgressDrawable(@DrawableRes int progressDrawableId) {
            newProgressParams();
            dialogParams.progressParams.progressDrawableId = progressDrawableId;
            return this;
        }

        public Builder setProgressHeight(int height) {
            newProgressParams();
            dialogParams.progressParams.progressHeight = height;
            return this;
        }

        public Builder configProgress(@NonNull ConfigProgress configProgress) {
            newProgressParams();
            configProgress.onConfig(dialogParams.progressParams);
            return this;
        }

        /**
         * 设置自定义等待框视图
         *
         * @param bodyViewId resLayoutId
         * @param listener   listener
         * @return Builder
         */
        public Builder setBodyView(@LayoutRes int bodyViewId, OnCreateBodyViewListener listener) {
            dialogParams.bodyViewId = bodyViewId;
            dialogParams.createBodyViewListener = listener;
            return this;
        }

        /**
         * 设置自定义等待框视图
         *
         * @param bodyView View
         * @param listener listener
         * @return Builder
         * @since 4.0.2
         */
        public Builder setBodyView(View bodyView, OnCreateBodyViewListener listener) {
            dialogParams.bodyView = bodyView;
            dialogParams.createBodyViewListener = listener;
            return this;
        }

        public Builder setOnCreateProgressListener(OnCreateProgressListener listener) {
            dialogParams.createProgressListener = listener;
            return this;
        }

        public Builder setInputText(@NonNull String text) {
            newInputParams();
            dialogParams.inputParams.text = text;
            return this;
        }

        /**
         * 是否自动显示键盘，默认不显示
         *
         * @param show true=显示；false=隐藏
         * @return Builder
         */
        public Builder setInputShowKeyboard(boolean show) {
            newInputParams();
            dialogParams.inputParams.showSoftKeyboard = show;
            return this;
        }

        public Builder setInputText(@NonNull String text, @NonNull String hint) {
            newInputParams();
            dialogParams.inputParams.text = text;
            dialogParams.inputParams.hintText = hint;
            return this;
        }

        public Builder setInputHint(@NonNull String hint) {
            newInputParams();
            dialogParams.inputParams.hintText = hint;
            return this;
        }

        public Builder setInputHeight(int height) {
            newInputParams();
            dialogParams.inputParams.inputHeight = height;
            return this;
        }

        /**
         * 输入框最大字符数量
         *
         * @param maxLen 字符数量
         * @return Builder
         */
        public Builder setInputCounter(int maxLen) {
            newInputParams();
            dialogParams.inputParams.maxLen = maxLen;
            return this;
        }

        /**
         * 输入框最大字符数量颜色
         *
         * @param color 色值
         * @return Builder
         */
        public Builder setInputCounterColor(@ColorInt int color) {
            newInputParams();
            dialogParams.inputParams.counterColor = color;
            return this;
        }

        /**
         * 输入框最大字符数量
         *
         * @param maxLen   字符数量
         * @param listener 字符计数器改变事件
         * @return Builder
         */
        public Builder setInputCounter(int maxLen, OnInputCounterChangeListener listener) {
            newInputParams();
            dialogParams.inputParams.maxLen = maxLen;
            dialogParams.inputCounterChangeListener = listener;
            return this;
        }

        /**
         * 是否禁止输入表情，默认开启
         *
         * @param disable true=禁止；false=开启
         * @return this Builder
         */
        public Builder setInputEmoji(boolean disable) {
            newInputParams();
            dialogParams.inputParams.isEmojiInput = disable;
            return this;
        }

        public Builder configInput(@NonNull ConfigInput configInput) {
            newInputParams();
            configInput.onConfig(dialogParams.inputParams);
            return this;
        }

        /**
         * 输入框的确定按钮
         *
         * @param text     按钮文本
         * @param listener 事件
         * @return this Builder
         */
        public Builder setPositiveInput(@NonNull String text, OnInputClickListener listener) {
            newPositiveParams();
            ButtonParams params = dialogParams.positiveParams;
            params.text = text;
            dialogParams.inputListener = listener;
            return this;
        }

        public Builder setOnCreateInputListener(OnCreateInputListener listener) {
            dialogParams.createInputListener = listener;
            return this;
        }

        /**
         * 确定按钮
         *
         * @param text     按钮文本
         * @param listener 事件
         * @return this Builder
         */
        public Builder setPositive(@NonNull String text, View.OnClickListener listener) {
            newPositiveParams();
            ButtonParams params = dialogParams.positiveParams;
            params.text = text;
            dialogParams.clickPositiveListener = listener;
            return this;
        }

        /**
         * 配置确定按钮
         *
         * @param configButton configButton
         * @return this Builder
         */
        public Builder configPositive(@NonNull ConfigButton configButton) {
            newPositiveParams();
            configButton.onConfig(dialogParams.positiveParams);
            return this;
        }

        /**
         * 取消按钮
         *
         * @param text     按钮文本
         * @param listener 事件
         * @return this Builder
         */
        public Builder setNegative(@NonNull String text, View.OnClickListener listener) {
            newNegativeParams();
            ButtonParams params = dialogParams.negativeParams;
            params.text = text;
            dialogParams.clickNegativeListener = listener;
            return this;
        }

        /**
         * 配置取消按钮
         *
         * @param configButton configButton
         * @return this Builder
         */
        public Builder configNegative(@NonNull ConfigButton configButton) {
            newNegativeParams();
            configButton.onConfig(dialogParams.negativeParams);
            return this;
        }

        /**
         * 中间按钮
         *
         * @param text     按钮文本
         * @param listener 事件
         * @return this Builder
         */
        public Builder setNeutral(@NonNull String text, View.OnClickListener listener) {
            newNeutralParams();
            ButtonParams params = dialogParams.neutralParams;
            params.text = text;
            dialogParams.clickNeutralListener = listener;
            return this;
        }

        /**
         * 配置中间按钮
         *
         * @param configButton configButton
         * @return this Builder
         */
        public Builder configNeutral(@NonNull ConfigButton configButton) {
            newNeutralParams();
            configButton.onConfig(dialogParams.neutralParams);
            return this;
        }

        public Builder setOnCreateButtonListener(OnCreateButtonListener listener) {
            dialogParams.createButtonListener = listener;
            return this;
        }

        public Builder setCloseResId(@DrawableRes int closeResId) {
            setCloseResId(closeResId, 0);
            return this;
        }

        /**
         * 设置关闭按钮图标资源文件id和大小
         *
         * @param closeResId 资源文件resId
         * @param closeSize  大小 dp
         * @return Builder
         */
        public Builder setCloseResId(@DrawableRes int closeResId, int closeSize) {
            newCloseParams();
            dialogParams.closeParams.closeResId = closeResId;
            dialogParams.closeParams.closeSize = closeSize;
            return this;
        }

        /**
         * 设置关闭图标内间距
         *
         * @param closePadding left, top, right, bottom
         * @return Builder
         */
        public Builder setClosePadding(int[] closePadding) {
            newCloseParams();
            dialogParams.closeParams.closePadding = closePadding;
            return this;
        }

        public Builder setCloseGravity(@CloseParams.CloseGravity int closeGravity) {
            newCloseParams();
            dialogParams.closeParams.closeGravity = closeGravity;
            return this;
        }

        /**
         * 连接线的宽高度，只有大于0才显示，默认是0
         *
         * @param width  dp
         * @param height dp
         * @return Builder
         */
        public Builder setCloseConnector(int width, int height) {
            newCloseParams();
            dialogParams.closeParams.connectorWidth = width;
            dialogParams.closeParams.connectorHeight = height;
            return this;
        }

        /**
         * 连接线的宽高度，只有大于0才显示，默认是0
         *
         * @param width  width
         * @param height height
         * @param color  线的颜色值 rgb
         * @return Builder
         */
        public Builder setCloseConnector(int width, int height, int color) {
            newCloseParams();
            dialogParams.closeParams.connectorWidth = width;
            dialogParams.closeParams.connectorHeight = height;
            dialogParams.closeParams.connectorColor = color;
            return this;
        }




        public MDialog show(FragmentManager manager) {
            MDialog dialogFragment = new MDialog();
            mDialog.show(manager);
            return dialogFragment;
        }

       /* public MDialog create() {
            if (mDialog == null) {
                mDialog = new MDialog();
            }
            return MDialog.newDialog(dialogParams);
        }*/



        private void newTitleParams() {
            if (dialogParams.titleParams == null)
                dialogParams.titleParams = new TitleParams();
        }

        private void newSubTitleParams() {
            if (dialogParams.subTitleParams == null)
                dialogParams.subTitleParams = new SubTitleParams();
        }

        private void newTextParams() {
            if (dialogParams.textParams == null) {
                dialogParams.textParams = new TextParams();
            }
        }



        private void newProgressParams() {
            if (dialogParams.progressParams == null) {
                dialogParams.progressParams = new ProgressParams();
            }
        }

        private void newInputParams() {
            if (dialogParams.inputParams == null) {
                dialogParams.inputParams = new InputParams();
            }
        }

        private void newPositiveParams() {
            if (dialogParams.positiveParams == null)
                dialogParams.positiveParams = new ButtonParams();
        }



        private void newNegativeParams() {
            if (dialogParams.negativeParams == null) {
                dialogParams.negativeParams = new ButtonParams();
                dialogParams.negativeParams.textColor = DialogDefaultValue.FOOTER_BUTTON_TEXT_NEGATIVE;
            }
        }

        private void newNeutralParams() {
            if (dialogParams.neutralParams == null)
                dialogParams.neutralParams = new ButtonParams();
        }

        private void newCloseParams() {
            if (dialogParams.closeParams == null) {
                dialogParams.closeParams = new CloseParams();
            }
        }

    }




}
