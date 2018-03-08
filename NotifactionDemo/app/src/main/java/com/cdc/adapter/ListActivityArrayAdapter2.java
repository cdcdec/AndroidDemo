package com.cdc.adapter;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.cdc.notifactiondemo.R;

public class ListActivityArrayAdapter2 extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setArrayAdapter2();
        super.onCreate(savedInstanceState);
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

}
