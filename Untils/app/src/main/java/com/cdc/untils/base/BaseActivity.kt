package com.cdc.untils.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.cdc.untils.R

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

    }
}
