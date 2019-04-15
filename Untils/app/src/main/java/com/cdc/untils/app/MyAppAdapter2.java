package com.cdc.untils.app;

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
