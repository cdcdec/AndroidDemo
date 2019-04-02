package zhou.demo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.RichTextConfig;
import com.zzhoujay.richtext.callback.DrawableGetter;
import com.zzhoujay.richtext.callback.OnImageClickListener;
import com.zzhoujay.richtext.callback.OnUrlClickListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTestActivity extends AppCompatActivity {

    private TextView tvHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_test);

        tvHtml=findViewById(R.id.tvHtml);

        // 设置为Html
        RichText.fromHtml(getStringFromAsset()).imageClick(new OnImageClickListener() {
            @Override
            public void imageClicked(List<String> imageUrls, int position) {
                Toast.makeText(MyTestActivity.this,imageUrls.get(position),Toast.LENGTH_SHORT).show();
            }
        })
                // 设置加载中显示的占位图
                .placeHolder(new DrawableGetter() {
                    @Override
                    public Drawable getDrawable(ImageHolder holder, RichTextConfig config, TextView textView) {
                        return getDrawableFromId(R.mipmap.ic_launcher);
                    }
                })
                // 设置加载失败的错误图
                .errorImage(new DrawableGetter() {
                    @Override
                    public Drawable getDrawable(ImageHolder holder, RichTextConfig config, TextView textView) {
                        Log.e("123","===error===");
                        ColorDrawable drawable = new ColorDrawable(Color.BLACK);
                        drawable.setBounds(0, 0, 100, 100);
                        return drawable;
                    }
                })
                //回调方法中返回true代表事件已消费   链接点击回调
                .urlClick(new OnUrlClickListener() {
                    @Override
                    public boolean urlClicked(String url) {
                        Toast.makeText(MyTestActivity.this,url,Toast.LENGTH_SHORT).show();
                        return true;
                    }
                })
                .into(tvHtml);
    }

    private String getStringFromAsset(){
        try {
            InputStream inputStream= this.getResources().getAssets().open("richText1.html");
            int length=inputStream.available();
            byte[] buffer=new byte[length];
            inputStream.read(buffer);
            return new String(buffer, 0, buffer.length, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    private Drawable getDrawableFromId(int id){
        return this.getResources().getDrawable(id);
    }
}
