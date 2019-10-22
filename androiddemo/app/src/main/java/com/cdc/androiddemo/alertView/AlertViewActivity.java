package com.cdc.androiddemo.alertView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cdc.alertview.AlertView;
import com.cdc.alertview.OnDismissListener;
import com.cdc.alertview.OnItemClickListener;
import com.cdc.androiddemo.R;

public class AlertViewActivity extends AppCompatActivity implements OnItemClickListener,OnDismissListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_view);
    }


    public void alertShow1(View view) {
        AlertView mAlertView=new AlertView("标题", "内容", "取消", new String[]{"确定"}, null, AlertViewActivity.this, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                showTip(position+"");

            }
        }).setCancelable(true).setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                showTip("消失了");
            }
        }).setCancelable(true);
        mAlertView.show();
    }

    //无标题,有内容时,内容距离顶部的距离太小,且无法调整。
    public void alertShow1_1(View view) {
        AlertView mAlertView=new AlertView(null, "内容", "取消", new String[]{"确定"}, null, AlertViewActivity.this, AlertView.Style.Alert, this).setCancelable(true).setOnDismissListener(this).setCancelable(true);
        mAlertView.show();
    }

    //有标题,无内容时,标题距离顶部的距离无法调整,字体大小及颜色无法调整。
    public void alertShow1_2(View view) {
        AlertView mAlertView=new AlertView("标题", null, "取消", new String[]{"确定"}, null, AlertViewActivity.this, AlertView.Style.Alert, this).setCancelable(true).setOnDismissListener(this).setCancelable(true);
        mAlertView.show();
    }

    public void alertShow1_3(View view) {
        AlertView mAlertView=new AlertView("标题", null, "取消", null, null, AlertViewActivity.this, AlertView.Style.Alert, this).setCancelable(true).setOnDismissListener(this).setCancelable(true);
        mAlertView.show();
    }


    /**
     * 横向做多能添加两个按钮
     * @param view
     */
    public void alertShow1_4(View view) {
        AlertView mAlertView=new AlertView("标题", null, "取消",  new String[]{"确定","不确定","取消"},null, AlertViewActivity.this, AlertView.Style.Alert, this).setCancelable(true).setOnDismissListener(this).setCancelable(true);
        mAlertView.show();
    }


    private void showTip(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    public void alertShow2(View view) {
        new AlertView("标题", "内容", null, new String[]{"确定"}, null, this, AlertView.Style.Alert, null).show();
    }


    public void alertShow3(View view) {
        new AlertView(null, null, null, new String[]{"高亮按钮1", "高亮按钮2", "高亮按钮3"},
                new String[]{"其他按钮1", "其他按钮2", "其他按钮3", "其他按钮4", "其他按钮5", "其他按钮6",
                        "其他按钮7", "其他按钮8", "其他按钮9", "其他按钮10", "其他按钮11", "其他按钮12"},
                this, AlertView.Style.Alert, null).show();
    }

    public void alertShow4(View view) {
        new AlertView("标题", null, "取消", new String[]{"高亮按钮1"}, new String[]{"其他按钮1", "其他按钮2", "其他按钮3"}, this, AlertView.Style.ActionSheet, this).show();
    }







    @Override
    public void onDismiss(Object o) {

    }

    @Override
    public void onItemClick(Object o, int position) {

    }

    public void alertShow5(View view) {
        new AlertView("标题", "内容", "取消", null, null, this, AlertView.Style.ActionSheet, this).setCancelable(true).show();
    }

    public void alertShow6(View view) {
        new AlertView("上传头像", null, "取消", null,
                new String[]{"拍照", "从相册中选择"},
                this, AlertView.Style.ActionSheet, this).show();
    }

    EditText etName;
    AlertView  mAlertViewExt;
    public void alertShow7(View view) {
        InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
          mAlertViewExt = new AlertView("提示", "请完善你的个人资料！", "取消", null, new String[]{"完成"}, this, AlertView.Style.Alert, new OnItemClickListener() {
              @Override
              public void onItemClick(Object o, int position) {
                  //关闭软键盘
                  imm.hideSoftInputFromWindow(etName.getWindowToken(),0);
                  //恢复位置
                  mAlertViewExt.setMarginBottom(0);
              }
          }).setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                //关闭软键盘
                imm.hideSoftInputFromWindow(etName.getWindowToken(),0);
                //恢复位置
                mAlertViewExt.setMarginBottom(0);
            }
        });
        ViewGroup extView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.alertext_form,null);
         etName = (EditText) extView.findViewById(R.id.etName);
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                //输入框出来则往上移动
                boolean isOpen=imm.isActive();
                mAlertViewExt.setMarginBottom(isOpen&&focus ? 180 :0);
                System.out.println(isOpen);
            }
        });
        mAlertViewExt.addExtView(extView);

        mAlertViewExt.show();
    }



}
