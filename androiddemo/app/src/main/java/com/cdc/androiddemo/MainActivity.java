package com.cdc.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cdc.permission.EasyPermission;
import com.cdc.permission.GrantResult;
import com.cdc.permission.NextAction;
import com.cdc.permission.NextActionType;
import com.cdc.permission.Permission;
import com.cdc.permission.PermissionRequestListener;
import com.cdc.permission.RequestPermissionRationalListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn1;
    private Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.btn1:
                EasyPermission.with(this)
                        .addPermissions(Permission.Group.LOCATION)      //申请定位权限组
                        .addPermissions(Permission.CALL_PHONE)          //申请打电话权限
                        .addRequestPermissionRationaleHandler(Permission.ACCESS_FINE_LOCATION, new RequestPermissionRationalListener() {
                            @Override
                            public void onRequestPermissionRational(String permission, boolean requestPermissionRationaleResult, final NextAction nextAction) {
                                //这里处理具体逻辑，如弹窗提示用户等,但是在处理完自定义逻辑后必须调用nextAction的next方法
                                Toast.makeText(MainActivity.this, "获取定位权限", Toast.LENGTH_LONG).show();
                                nextAction.next(NextActionType.NEXT);
                            }
                        })
                        .addRequestPermissionRationaleHandler(Permission.CALL_PHONE, new RequestPermissionRationalListener() {
                            @Override
                            public void onRequestPermissionRational(String permission, boolean requestPermissionRationaleResult, final NextAction nextAction) {
                                //这里处理具体逻辑，如弹窗提示用户等,但是在处理完自定义逻辑后必须调用nextAction的next方法
                                 Toast.makeText(MainActivity.this, "CALL_PHONE", Toast.LENGTH_LONG).show();
                                                              nextAction.next(NextActionType.NEXT);
                            }
                        })
                        .request(new PermissionRequestListener() {
                            @Override
                            public void onGrant(Map<String, GrantResult> result) {
                                //权限申请返回
                                Log.e("123",result.size()+"");
                            }

                            @Override
                            public void onCancel(String stopPermission) {
                                //在addRequestPermissionRationaleHandler的处理函数里面调用了NextAction.next(NextActionType.STOP,就会中断申请过程，直接回调到这里来
                            }
                        });
                break;

            case R.id.btn2:
                break;
        }
    }
}
