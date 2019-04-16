package com.cdc.untils.app
import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allen.library.SuperTextView
import com.blankj.utilcode.util.AppUtils
import com.cdc.untils.R
import java.util.ArrayList
class MyAppAdapter(recyclerView: RecyclerView) : RecyclerView.Adapter<MyAppAdapter.RecyclerHolder>() {
/**
 *
 * 点击监听回调接口
 * */
    interface onRecyclerItemClickerListener{
        fun onRecyclerItemClick(view: View, data: Any, position: Int)
    }

    private var mListener: onRecyclerItemClickerListener? = null

    /**
     * 增加点击监听
     */
    fun setItemListener(mListener: onRecyclerItemClickerListener) {
        this.mListener = mListener
    }

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
        recyclerHolder.tv1?.setLeftString(appInfo.name)!!.setLeftIcon(appInfo.icon).setRightString(appInfo.versionName)

        recyclerHolder.tv2
            ?.setRightString(appInfo.packageName)

        recyclerHolder.tv3
            ?.setRightString(appInfo.packagePath)

        recyclerHolder.tv4
            ?.setRightString(appInfo.isSystem.toString())

        recyclerHolder.tv5
            ?.setRightString(AppUtils.isAppDebug(appInfo.packageName).toString())

        recyclerHolder.tv6
            ?.setRightString(AppUtils.getAppSignatureMD5(appInfo.packageName).toString())

        recyclerHolder.tv7
            ?.setRightString(AppUtils.getAppSignatureSHA1(appInfo.packageName).toString())

        recyclerHolder.tv8
            ?.setRightString(AppUtils.getAppSignatureSHA256(appInfo.packageName).toString())

        recyclerHolder.reCard?.setOnClickListener {
            mListener?.onRecyclerItemClick(it,appInfo,i)
        }
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
    inner class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv1: SuperTextView?=null
        var tv2: SuperTextView?=null
        var tv3: SuperTextView?=null
        var tv4: SuperTextView?=null
        var tv5: SuperTextView?=null
        var tv6: SuperTextView?=null
        var tv7: SuperTextView?=null
        var tv8: SuperTextView?=null
        var reCard: CardView?=null
        init {
            reCard=itemView.findViewById<View>(R.id.reCard) as CardView
            tv1 = itemView.findViewById<View>(R.id.tv1) as SuperTextView
            tv2 = itemView.findViewById<View>(R.id.tv2) as SuperTextView
            tv3 = itemView.findViewById<View>(R.id.tv3) as SuperTextView
            tv4 = itemView.findViewById<View>(R.id.tv4) as SuperTextView
            tv5 = itemView.findViewById<View>(R.id.tv5) as SuperTextView
            tv6 = itemView.findViewById<View>(R.id.tv6) as SuperTextView
            tv7 = itemView.findViewById<View>(R.id.tv7) as SuperTextView
            tv8 = itemView.findViewById<View>(R.id.tv8) as SuperTextView
        }
    }
}
