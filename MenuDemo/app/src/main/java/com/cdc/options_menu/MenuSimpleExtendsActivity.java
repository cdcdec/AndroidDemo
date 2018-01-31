package com.cdc.options_menu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cdc.menudemo.R;

public class MenuSimpleExtendsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_menu_extends);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.removeItem(R.id.group_item1);
        menu.add("extends_menu").setIcon(R.drawable.baidu).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }
}
