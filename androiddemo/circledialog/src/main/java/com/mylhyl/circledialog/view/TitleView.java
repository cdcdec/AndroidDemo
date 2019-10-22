package com.mylhyl.circledialog.view;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mylhyl.circledialog.BackgroundHelper;
import com.mylhyl.circledialog.Controller;
import com.mylhyl.circledialog.params.DialogParams;
import com.mylhyl.circledialog.params.SubTitleParams;
import com.mylhyl.circledialog.params.TitleParams;
import com.mylhyl.circledialog.view.listener.OnCreateTitleListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 对话框标题
 * Created by hupei on 2017/3/29.
 */
final class TitleView extends LinearLayout {
    private RelativeLayout mTitleLayout;
    private ImageView mTitleIcon;
    private TextView mTitleView;
    private TextView mSubTitleView;
    private DialogParams mDialogParams;
    private TitleParams mTitleParams;
    private SubTitleParams mSubTitleParams;
    private OnCreateTitleListener mOnCreateTitleListener;

    public TitleView(Context context, DialogParams dialogParams, TitleParams titleParams
            , SubTitleParams subTitleParams, OnCreateTitleListener createTitleListener) {
        super(context);
        this.mDialogParams = dialogParams;
        this.mTitleParams = titleParams;
        this.mSubTitleParams = subTitleParams;
        this.mOnCreateTitleListener = createTitleListener;
        init();
    }

    public void refreshText() {
        if (mTitleParams == null || mTitleView == null) return;
        mTitleView.setText(mTitleParams.text);
        if (mSubTitleView != null) {
            mSubTitleView.setText(mSubTitleParams.text);
        }
    }

    private void init() {
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        setOrientation(LinearLayout.VERTICAL);
        createTitleLayout();
        //标题图标
        createTitleIcon();
        //标题
        createTitle();
        //副标题
        createSubTitle();
        if (mOnCreateTitleListener != null) {
            mOnCreateTitleListener.onCreateTitle(mTitleIcon, mTitleView, mSubTitleView);
        }
    }

    private void createTitleLayout() {
        mTitleLayout = new RelativeLayout(getContext());
        mTitleLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        mTitleLayout.setGravity(mTitleParams.gravity);
        mTitleLayout.setPadding(50, 0, 50, 0);

        //如果标题没有背景色，则使用默认色
        int backgroundColor = mTitleParams.backgroundColor != 0
                ? mTitleParams.backgroundColor : mDialogParams.backgroundColor;
        BackgroundHelper.INSTANCE.handleTitleBackground(mTitleLayout, backgroundColor);
    }

    @NonNull
    private void createTitleIcon() {
        mTitleIcon = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParamsTitleIcon = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsTitleIcon.addRule(RelativeLayout.LEFT_OF, android.R.id.title);
        layoutParamsTitleIcon.addRule(RelativeLayout.CENTER_VERTICAL);
        mTitleIcon.setLayoutParams(layoutParamsTitleIcon);
        if (mTitleParams.icon != 0) {
            mTitleIcon.setImageResource(mTitleParams.icon);
            mTitleIcon.setVisibility(VISIBLE);
        } else {
            mTitleIcon.setVisibility(GONE);
        }
        mTitleLayout.addView(mTitleIcon);
    }

    @NonNull
    private void createTitle() {
        mTitleView = new TextView(getContext());
        mTitleView.setGravity(Gravity.CENTER);
        mTitleView.setId(android.R.id.title);
        RelativeLayout.LayoutParams layoutParamsTitle = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsTitle.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mTitleView.setLayoutParams(layoutParamsTitle);
        if (mTitleParams.height != 0) {
            mTitleView.setHeight(Controller.dp2px(getContext(), mTitleParams.height));
        }
        mTitleView.setTextColor(mTitleParams.textColor);
        mTitleView.setTextSize(mTitleParams.textSize);
        mTitleView.setText(mTitleParams.text);
        int[] padding = mTitleParams.padding;
        if (padding != null)
            mTitleView.setPadding(Controller.dp2px(getContext(), padding[0]), Controller.dp2px(getContext(), padding[1])
                    , Controller.dp2px(getContext(), padding[2]), Controller.dp2px(getContext(), padding[3]));
        mTitleView.setTypeface(mTitleView.getTypeface(), mTitleParams.styleText);
        mTitleLayout.addView(mTitleView);
        addView(mTitleLayout);
    }

    @Nullable
    private void createSubTitle() {
        if (mSubTitleParams != null) {
            mSubTitleView = new TextView(getContext());
            mSubTitleView.setGravity(Gravity.CENTER);
            setSubTitleBg(mSubTitleView, mSubTitleParams.backgroundColor, mDialogParams.backgroundColor);
            mSubTitleView.setGravity(mSubTitleParams.gravity);
            if (mSubTitleParams.height != 0) {
                mSubTitleView.setHeight(Controller.dp2px(getContext(), mSubTitleParams.height));
            }
            mSubTitleView.setTextColor(mSubTitleParams.textColor);
            mSubTitleView.setTextSize(mSubTitleParams.textSize);
            mSubTitleView.setText(mSubTitleParams.text);
            int[] padding = mSubTitleParams.padding;
            if (padding != null) {
                mSubTitleView.setPadding(Controller.dp2px(getContext(), padding[0])
                        , Controller.dp2px(getContext(), padding[1]), Controller.dp2px(getContext(), padding[2])
                        , Controller.dp2px(getContext(), padding[3]));
            }
            mSubTitleView.setTypeface(mSubTitleView.getTypeface(), mSubTitleParams.styleText);
            addView(mSubTitleView);
        }
    }

    private void setSubTitleBg(TextView subTitle, int tbg, int dbg) {
        //如果标题没有背景色，则使用默认色
        int bgColor = tbg != 0 ? tbg : dbg;
        subTitle.setBackgroundColor(bgColor);
    }
}
