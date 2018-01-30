package com.cdc.menudemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_menu_base).setOnClickListener(this);
        findViewById(R.id.btn_menu_adavance1).setOnClickListener(this);
    }
    //来自Activity的方法
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        return true;
    }
    //处理menu点击事件  来自Activity的方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     return super.onOptionsItemSelected(item);
    }
    //在Activity生命周期中发生的事件修改选项菜单
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final Menu menuTemp=menu;
        MenuItem menuItem=menu.findItem(R.id.zhifubaoId);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                boolean bl=false;
                if(!bl){
                    menuTemp.removeItem(R.id.app_store_iphone_id);
                    menuTemp.add("不理不理").setIcon(R.drawable.bilibili).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                    bl=true;
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.btn_menu_base:
                Intent intent=new Intent();
                intent.setClass(this,MenuSimpleExtendsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_menu_adavance1:
                Intent intentAdavance=new Intent();
                intentAdavance.setClass(this,MenuAdavance1Activity.class);
                startActivity(intentAdavance);
                break;
        }
    }
}

