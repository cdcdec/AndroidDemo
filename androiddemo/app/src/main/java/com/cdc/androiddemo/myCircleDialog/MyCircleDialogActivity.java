package com.cdc.androiddemo.myCircleDialog;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.cdc.androiddemo.R;
import com.cdc.androiddemo.circleDialog.CircleDialogActivity;
import com.cdc.dialog.MDialog;
import com.cdc.shape.CircleDrawable;
import com.mylhyl.circledialog.CircleDialog;

public class MyCircleDialogActivity extends AppCompatActivity implements View.OnClickListener{
   private Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_dialog_my);
        initView();
    }

    private void initView(){
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.btn1:
                configBtn1();
                break;
            case R.id.btn2:
                configBtn2();
                break;
            case R.id.btn3:

                break;
        }
    }

    private void  configBtn1(){
        new CircleDialog.Builder()
                .setTitle("标题")
                .setWidth(0.95f)
                .setRadius(12)
                .setText("提示框")
                .setPositive("确定", null)
                .setOnShowListener(dialog ->
                        Toast.makeText(this, "显示了！", Toast.LENGTH_SHORT).show())
                .setOnCancelListener(dialog ->
                        Toast.makeText(this, "取消了！", Toast.LENGTH_SHORT).show())
                .show(getSupportFragmentManager());
    }


    private void  configBtn2(){
        btn2.setBackground(new CircleDrawable(Color.parseColor("#00ff00"), 20,10,new RectF(10f, 30f, 110f,100f)));
    }
}
