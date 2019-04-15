package com.cdc.untils.device

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import com.blankj.utilcode.util.ScreenUtils
import com.cdc.untils.R
import kotlinx.android.synthetic.main.activity_my_device.*

class MyDeviceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_device)

        initView()
    }

    private fun initView(){
        stv1.setLeftString("屏幕宽度:")
            .setLeftTextGravity(Gravity.CENTER)
            .setRightString("${ScreenUtils.getScreenWidth()}px")
    }
}
