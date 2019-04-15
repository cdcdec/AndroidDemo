package com.cdc.untils.device

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.util.Log
import android.view.Gravity
import com.allen.library.SuperTextView
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.RomUtils
import com.blankj.utilcode.util.SDCardUtils
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
        stv0.setCenterString(stringWrap("屏幕"))
        stv1.setLeftString("屏幕宽度:")
            .setRightString("${ScreenUtils.getScreenWidth()}px")
        stv2.setLeftString("屏幕高度:")
            .setRightString("${ScreenUtils.getScreenHeight()}px")

        stv3.setLeftString("屏幕密度:")
            .setRightString("${ScreenUtils.getScreenDensity()}")

        stv4.setLeftString("屏幕密度DPI:")
            .setRightString("${ScreenUtils.getScreenDensityDpi()}")

        stv5.setLeftString("是否是平板:")
            .setRightString("${ScreenUtils.isTablet()}")

        stvSd.setCenterString(stringWrap("SD卡"))


        stvSd1.setLeftString("SD卡是否可用:")
            .setRightString("${ SDCardUtils.isSDCardEnableByEnvironment()}")

        stvSd2.setLeftString("获取SD卡路径:")
            .setRightString("${ SDCardUtils.getSDCardPathByEnvironment()}")

        stvSd3.setLeftString("获取SD卡信息:")
        stvSd31.setCenterString(SDCardUtils.getSDCardInfo().toString())
        stvRom.setCenterString(stringWrap("Rom"))
        stvRomInfo.setCenterString(RomUtils.getRomInfo().toString())

        stvDevice0.setCenterString(stringWrap("设备"))

        stvDevice.setLeftString("设备是否rooted:")
            .setRightString("${DeviceUtils.isDeviceRooted()}")

        stvDevice1.setLeftString("ADB是否可用:")
            .setRightString("${DeviceUtils.isAdbEnabled()}")

        stvDevice2.setLeftString("系统版本号:")
            .setRightString("${DeviceUtils.getSDKVersionName()}")

        stvDevice3.setLeftString("系统版本码:")
            .setRightString("${DeviceUtils.getSDKVersionCode()}")

        stvDevice4.setLeftString("设备AndroidID:")
            .setRightString("${DeviceUtils.getAndroidID()}")

        stvDevice5.setLeftString("设备MAC地址:")
            .setRightString("${DeviceUtils.getMacAddress()}")

        stvDevice6.setLeftString("设备厂商:")
            .setRightString("${DeviceUtils.getManufacturer()}")

        stvDevice7.setLeftString("设备型号:")
            .setRightString("${DeviceUtils.getModel()}")

        stvDevice8.setLeftString("设备ABIs:")
            .setRightString("${DeviceUtils.getABIs().asList()}")

        stvDevice9.setLeftString("关机:")
     //       .setRightString("${DeviceUtils.shutdown()}")

        stvDevice10.setLeftString("重启:")
      //      .setRightString("${DeviceUtils.reboot()}")

        stvDevice11.setLeftString("重启到recovery:")
     //       .setRightString("${DeviceUtils.reboot2Recovery()}")


        stvDevice12.setLeftString("重启到bootloader:")
      //      .setRightString("${DeviceUtils.reboot2Bootloader()}")



    }

    private fun stringWrap(str:String):String{
        return "=========${str}相关========="
    }


}
