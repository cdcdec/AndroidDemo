package com.kotlinmvp.app.common
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView(savedInstanceState)
    }

    /**
     * 获取RootLayout布局Id
     */
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    /**
     * 初始化 View 控件
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 显示Toast
     * @param resId 文本资源Id
     */
    fun showToast(resId: Int) {
        showToast(getString(resId))
    }

    /**
     * 显示Toast
     * @param msg 文本
     */
    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * 跳转
     * @param cls 类
     */
    fun forward(cls: Class<*>) {
        closeKeyBoard()
        forward(Intent(this, cls))
    }

    /**
     * 跳转
     * @param intent
     */
    fun forward(intent: Intent) {
        closeKeyBoard()
        startActivity(intent)
    }

    /**
     * 跳转并关闭当前页面
     * @param intent
     */
    fun forwardAndFinish(cls: Class<*>) {
        forward(cls)
        finish()
    }

    /**
     * 跳转并关闭当前页面
     */
    fun forwardAndFinish(intent: Intent) {
        forward(intent)
        finish()
    }

    /**
     * 回退
     */
    fun backward() {
        closeKeyBoard()
        finish()
    }
}
