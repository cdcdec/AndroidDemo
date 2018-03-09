package com.cdc.adapter;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cdc.notifactiondemo.R;

public class ListActivityArrayAdapter3 extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setArrayAdapter1();
        super.onCreate(savedInstanceState);
    }

    private void setArrayAdapter1(){
        ArrayAdapter<People> arrayAdapter=new ArrayAdapter<People>(this, R.layout.layout_tv,DataTool.listPeoples);
        setListAdapter(arrayAdapter);
    }

    private void setArrayAdapter2(){
        ArrayAdapter<People> arrayAdapter=new ArrayAdapter<People>(this, R.layout.list_layout_1,R.id.tv,DataTool.listPeoples);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ListAdapter listAdapter=getListAdapter();
        People people =(People) listAdapter.getItem(position);
        Toast.makeText(this,people.getPosition(),Toast.LENGTH_SHORT).show();
    }
}
