package com.cdc.notifaction;

import android.app.Notification;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cdc.notifactiondemo.R;

public class NotifactionResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=this.getIntent();
        Notification notifaction=(Notification)intent.getExtras().get("notifaction");

        int a=notifaction.number;
        int b=notifaction.flags;
        int c=notifaction.iconLevel;

        String text=notifaction.toString();
        setContentView(R.layout.activity_notifaction_result);


        TextView  tv=(TextView)findViewById(R.id.text);

        tv.setText(a+","+b+","+c+","+text);


    }
}
