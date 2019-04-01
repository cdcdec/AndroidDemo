# RichTextDemo

https://github.com/zzhoujay/RichText  
多看wiki https://github.com/zzhoujay/RichText/wiki

注意事项
不支持css属性的解析
TextView的width属性不能为warp_content
如果需要手动取消任务，可以手动调用clear方法，可以在Activity onDestory方法中调用手动取消任务回收内存
目前只能自动通过src的后缀识别是否为gif图，可以通过设置ImageFixCallback对某个特点的图片设置为GIF ，例如：holder.setImageType(ImageHolder.GIF)
在RecyclerView和ListView中使用Gif图目前存在bug,建议不要在ListView和RecyclerView中播放动图
在RecyclerView中存在一些问题，特别是当每个item高度比较大时
Gif图片播放不支持硬件加速，若要使用Gif图片请先关闭TextView的硬件加速TextView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);



