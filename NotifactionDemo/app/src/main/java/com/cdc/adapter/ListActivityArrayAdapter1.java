package com.cdc.adapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.cdc.notifactiondemo.R;

public class ListActivityArrayAdapter1 extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setArrayAdapter1();
        super.onCreate(savedInstanceState);
    }

    private void setArrayAdapter1(){
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        arrayAdapter.add("1");
        arrayAdapter.add("11");
        arrayAdapter.add("111");
        arrayAdapter.add("1111");
        arrayAdapter.add("11111");
        setListAdapter(arrayAdapter);
    }

    private void setArrayAdapter2(){
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, R.layout.list_layout_1,R.id.tv);
        arrayAdapter.add("1");
        arrayAdapter.add("11");
        arrayAdapter.add("111");
        arrayAdapter.add("1111");
        arrayAdapter.add("11111");
        setListAdapter(arrayAdapter);
    }

    private void setArrayAdapter3(){
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, R.layout.list_layout_1);
        arrayAdapter.add("1");
        arrayAdapter.add("11");
        arrayAdapter.add("111");
        arrayAdapter.add("1111");
        arrayAdapter.add("11111");
        setListAdapter(arrayAdapter);
    }
}
