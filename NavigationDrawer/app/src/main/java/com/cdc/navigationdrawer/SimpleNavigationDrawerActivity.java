package com.cdc.navigationdrawer;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class SimpleNavigationDrawerActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;

    private NavigationView mNavigationViewRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_navigation_drawer);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer);

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setItemIconTintList(null);//设置菜单图标恢复本来的颜色
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.subitem_01) {
                    Toast.makeText(SimpleNavigationDrawerActivity.this, "sub item 01", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.subitem_04) {
                    Toast.makeText(SimpleNavigationDrawerActivity.this, "sub item 04", Toast.LENGTH_SHORT).show();

                }
                mDrawer.closeDrawers();
                return true;
            }
        });

        mNavigationViewRight= (NavigationView) findViewById(R.id.navigation_viewRight);
        mNavigationViewRight.setItemIconTintList(null);//设置菜单图标恢复本来的颜色
        mNavigationViewRight.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.subitem_01) {
                    Toast.makeText(SimpleNavigationDrawerActivity.this, "sub item 01", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.subitem_04) {
                    Toast.makeText(SimpleNavigationDrawerActivity.this, "sub item 04", Toast.LENGTH_SHORT).show();

                }
                mDrawer.closeDrawers();
                return true;
            }
        });

    }
}
