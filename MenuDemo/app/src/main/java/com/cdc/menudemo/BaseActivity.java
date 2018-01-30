package com.cdc.menudemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
    //来自Activity的方法
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu_base, menu);

        return true;
    }
    //处理menu点击事件  来自Activity的方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_new:
                Toast.makeText(this,"新建",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.open:
                Toast.makeText(this,"打开",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onGroupItemClick(MenuItem item){
        Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
    }

}
