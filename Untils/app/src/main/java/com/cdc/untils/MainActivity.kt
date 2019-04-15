package com.cdc.untils

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils
import com.cdc.untils.device.MyDeviceActivity
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()




    }

    private fun initView(){
        btn1.setOnClickListener{
            ActivityUtils.startActivity(MyDeviceActivity::class.java)
        }
    }
}
