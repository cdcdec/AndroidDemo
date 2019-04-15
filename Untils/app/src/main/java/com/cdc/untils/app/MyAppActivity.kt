package com.cdc.untils.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.blankj.utilcode.util.AppUtils
import com.cdc.untils.R
import kotlinx.android.synthetic.main.activity_my_app.*
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.LinearLayoutManager



class MyAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_app)
        var adapter=MyAppAdapter(reApp)
        adapter.setData(AppUtils.getAppsInfo())
        val layoutManager = LinearLayoutManager(this)
        //设置布局管理器
        reApp.layoutManager = layoutManager
        //设置为垂直布局，这也是默认的
        layoutManager.orientation = OrientationHelper.VERTICAL
        reApp.adapter=adapter
    }
}
