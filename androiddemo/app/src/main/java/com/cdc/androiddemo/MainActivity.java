package com.cdc.androiddemo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cdc.androiddemo.alertView.AlertViewActivity;
import com.cdc.androiddemo.circleDialog.CircleDialogActivity;
import com.cdc.androiddemo.myCircleDialog.MyCircleDialogActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void goToPage(Class activity) {
        Intent intent = new Intent();
        intent.setClass(this, activity);
        startActivity(intent);
    }


    private void showTip(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void alertView(View view) {
        goToPage(AlertViewActivity.class);
    }

    public void circleDialog(View view) {
        goToPage(CircleDialogActivity.class);
    }


    public void myCircleDialog(View view) {
        goToPage(MyCircleDialogActivity.class);
    }
}
