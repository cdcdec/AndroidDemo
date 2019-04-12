package com.cdc.okhttp3
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8")
    private val MEDIA_TYPE_PNG = MediaType.parse("image/png")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bt_send.setOnClickListener(this)
        bt_postsend.setOnClickListener(this)
        bt_sendfile.setOnClickListener(this)
        bt_downfile.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_send -> {
                getAsynHttp()
            }
            R.id.bt_postsend -> {

            }
            R.id.bt_sendfile -> {

            }
            R.id.bt_downfile -> {

            }
        }
    }

    /**
     * get异步请求
     */
    private fun getAsynHttp() {
        //Log Info,Warn,Error可以看到日志;Log Verbose Debug看不到日志
        val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.i("ppp",it)
        })
        logging.redactHeader("Server")
        //val logging = HttpLoggingInterceptor()  看不到日志
        logging.level = HttpLoggingInterceptor.Level.BODY
        var mOkHttpClient=OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()
        var requestBuilder = Request.Builder().url("https://api.github.com/users/cdcdec")
        var request = requestBuilder.build()
        var mcall = mOkHttpClient?.newCall(request)
        mcall?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread { Toast.makeText(applicationContext, "请求失败:${call.request().url()}", Toast.LENGTH_SHORT).show() }
            }
            override fun onResponse(call: Call, response: Response) {
                var str = ""
                if (null != response.cacheResponse()) {
                    str = response.cacheResponse().toString()+"==cache"
                   // Log.i("wangshu", "cache---$str")
                } else {
                    response.body()?.string()
                    str = response.networkResponse().toString()+"==network"
                    //Log.i("wangshu", "network---$str")
                }
                runOnUiThread { Toast.makeText(applicationContext, "请求成功:$str", Toast.LENGTH_SHORT).show() }
            }
        })
    }
}
