## Utils

1. 使用(https://github.com/Blankj/AndroidUtilCode)[https://github.com/Blankj/AndroidUtilCode]获取设备的相关信息

2. 例如分辨率 版本号 厂商 mac地址等等

3. 获取设备上安装的app的信息



### RecyclerView  adapter的写法

1. kotlin
```
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


```

2. java
```
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.allen.library.SuperTextView;
import com.blankj.utilcode.util.AppUtils;
import com.cdc.untils.R;

import java.util.ArrayList;
import java.util.List;

public class MyAppAdapter2  extends RecyclerView.Adapter<MyAppAdapter2.RecyclerHolder> {
    private Context mContext;
    private List<AppUtils.AppInfo> dataList = new ArrayList<>();
    public MyAppAdapter2(RecyclerView recyclerView) {
        this.mContext = recyclerView.getContext();
    }
    public void setData(List<AppUtils.AppInfo> dataList) {
        if (null != dataList) {
            this.dataList.clear();
            this.dataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }
    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_my_app, viewGroup, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder recyclerHolder, int i) {
        AppUtils.AppInfo appInfo=dataList.get(i);
        recyclerHolder.textView.setLeftString(dataList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        SuperTextView textView;

        private RecyclerHolder(View itemView) {
            super(itemView);
            textView = (SuperTextView) itemView.findViewById(R.id.tv1);
        }

    }
}


```

3. 在RecyclerView里面调用

```
    var adapter=MyAppAdapter(reApp)
    adapter.setData(AppUtils.getAppsInfo())
    val layoutManager = LinearLayoutManager(this)
     //设置布局管理器
    reApp.layoutManager = layoutManager
    //设置为垂直布局，这也是默认的
    layoutManager.orientation = OrientationHelper.VERTICAL
    reApp.adapter=adapter

    adapter.setItemListener(object:MyAppAdapter.onRecyclerItemClickerListener{
        override fun onRecyclerItemClick(view: View, data: Any, position: Int) {
            var appInfo:AppUtils.AppInfo=data as AppUtils.AppInfo
            AppUtils.launchApp(appInfo.packageName)
        }
    })

```


