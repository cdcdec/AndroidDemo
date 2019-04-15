package com.cdc.untils.app
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allen.library.SuperTextView
import com.blankj.utilcode.util.AppUtils
import com.cdc.untils.R
import java.util.ArrayList
class MyAppAdapter(recyclerView: RecyclerView) : RecyclerView.Adapter<MyAppAdapter.RecyclerHolder>() {
    private val mContext: Context = recyclerView.context
    private val dataList = ArrayList<AppUtils.AppInfo>()
    fun setData(dataList: List<AppUtils.AppInfo>?) {
        if (null != dataList) {
            this.dataList.clear()
            this.dataList.addAll(dataList)
            notifyDataSetChanged()
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_my_app, viewGroup, false)
        return RecyclerHolder(view)
    }
    override fun onBindViewHolder(recyclerHolder: RecyclerHolder, i: Int) {
        val appInfo = dataList[i]
        recyclerHolder.textView?.setLeftString(appInfo.name)!!.setLeftIcon(appInfo.icon)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
    inner class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: SuperTextView?=null
        init {
            textView = itemView.findViewById<View>(R.id.tv1) as SuperTextView
        }
    }
}
