# RichTextDemo

https://github.com/zzhoujay/RichText  
多看wiki https://github.com/zzhoujay/RichText/wiki

## 注意事项
1. 不支持css属性的解析
2. TextView的width属性不能为warp_content
3. 如果需要手动取消任务，可以手动调用clear方法，可以在Activity onDestory方法中调用手动取消任务回收内存
4. 目前只能自动通过src的后缀识别是否为gif图，可以通过设置ImageFixCallback对某个特点的图片设置为GIF ，例如：holder.setImageType(ImageHolder.GIF)
5. 在RecyclerView和ListView中使用Gif图目前存在bug,建议不要在ListView和RecyclerView中播放动图
6. 在RecyclerView中存在一些问题，特别是当每个item高度比较大时
7. Gif图片播放不支持硬件加速，若要使用Gif图片请先关闭TextView的硬件加速TextView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
8. 不支持视频
9. 可以添加链接和图片的点击和长按事件
10. 设置加载失败的错误图
```
// 设置加载失败的错误图   drawable要设置bound  否则显示不出来
.errorImage(new DrawableGetter() {
    @Override
    public Drawable getDrawable(ImageHolder holder, RichTextConfig config, TextView textView) {
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


```

## 在Activity里面通过代码添加菜单

```
 @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "RecyclerView");
        menu.add(0, 1, 1, "ListView");
        menu.add(0, 2, 2, "Gif");
        menu.add(0, 3, 3, "Test");
        menu.add(0, 4, 4, "MyTest");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
            startActivity(new Intent(this, RecyclerViewActivity.class));
        } else if (item.getItemId() == 1) {
            startActivity(new Intent(this, ListViewActivity.class));
        } else if (item.getItemId() == 2) {
            startActivity(new Intent(this, GifActivity.class));
        } else if (item.getItemId() == 3) {
            startActivity(new Intent(this, TestActivity.class));
        }else if(item.getItemId() == 4){
            startActivity(new Intent(this, MyTestActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


```


## ColorDrawable

```
ColorDrawable drawable = new ColorDrawable(Color.BLACK);
drawable.setBounds(0, 0, 100, 100);

显示一个黑色色块


```





