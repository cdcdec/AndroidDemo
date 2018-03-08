package com.cdc.notifactiondemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

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
        Intent intent=new Intent();
        intent.setAction(str);
        startActivity(intent);
    }
}
