package com.cdc.notifactiondemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.cdc.notifaction.NotifactionTool;

public class MainActivity extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setArrayAdapter3();
        super.onCreate(savedInstanceState);
    }
    private void setArrayAdapter3(){
        ArrayAdapter<CharSequence> arrayAdapter= ArrayAdapter.createFromResource(this,R.array.btns,R.layout.layout_tv);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ListAdapter listAdapter=getListAdapter();
        String str=listAdapter.getItem(position).toString();
        if(str.contains("ArrayAdapter")){
            Intent intent=new Intent();
            intent.setAction(str);
            startActivity(intent);
        }else if(str.equalsIgnoreCase("Notifaction1")){
            NotifactionTool.createSimpleNotifaction(this);
        }else if(str.equalsIgnoreCase("Notifaction2")){
        NotifactionTool.createNotifaction2(this,"notifaction2");
        }else if(str.equalsIgnoreCase("Notifaction3")){
            NotifactionTool.createNotifaction3(this,"notifaction3");
        }else if(str.equalsIgnoreCase("普通通知")){
            NotifactionTool.simpleNotify(this);
        }else if(str.equalsIgnoreCase("BigTextStyle")){
            NotifactionTool.bigTextStyle(this);
        }else if(str.equalsIgnoreCase("InboxStyle")){
            NotifactionTool.inBoxStyle(this);
        }else if(str.equalsIgnoreCase("BigPictureStyle")){
            NotifactionTool.bigPictureStyle(this);
        }
    }
}
