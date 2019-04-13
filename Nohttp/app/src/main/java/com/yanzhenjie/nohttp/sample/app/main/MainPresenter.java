package com.yanzhenjie.nohttp.sample.app.main;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.yanzhenjie.nohttp.sample.R;
import com.yanzhenjie.nohttp.sample.app.BaseActivity;
import com.yanzhenjie.nohttp.sample.app.body.BodyPresenter;
import com.yanzhenjie.nohttp.sample.app.download.DownloadPresenter;
import com.yanzhenjie.nohttp.sample.app.form.FormPresenter;
import com.yanzhenjie.nohttp.sample.app.normal.NormalPresenter;
import com.yanzhenjie.nohttp.sample.entity.News;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by YanZhenjie on 2018/2/25.
 */
public class MainPresenter
  extends BaseActivity
  implements Contract.MainPresenter {

    private Contract.MainView mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = new MainView(this, this);

        requestDataList();
    }

    private void requestDataList() {
        List<News> dataList = new ArrayList<>();
        String[] titleArray = getResources().getStringArray(R.array.function_item_title);
        String[] contentArray = getResources().getStringArray(R.array.function_item_content);

        for (int i = 0; i < titleArray.length; i++) {
            News item = new News();
            item.setTitle(titleArray[i]);
            item.setContent(contentArray[i]);
            dataList.add(item);
        }

        mView.setDataList(dataList);
    }

    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0: {
                Intent intent = new Intent(this, NormalPresenter.class);
                startActivity(intent);
                break;
            }
            case 1: {
                Intent intent = new Intent(this, FormPresenter.class);
                startActivity(intent);
                break;
            }
            case 2: {
                Intent intent = new Intent(this, BodyPresenter.class);
                startActivity(intent);
                break;
            }
            case 3: {
                Intent intent = new Intent(this, DownloadPresenter.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}