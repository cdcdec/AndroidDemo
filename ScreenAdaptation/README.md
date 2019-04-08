# ScreenAdaptation
[屏幕适配](https://github.com/JessYanCoding/AndroidAutoSize)

## 使用方法

1. 在Application里面初始化
```
AutoSizeConfig.getInstance()
//可以自定义一些参数 详细看源码注释  及demo


```
2. 在AndroidManifest.xml配置
```
//单位是dp
 <!-- 如果您项目中的所有页面都只需要以高或宽中的一个作为基准进行适配的话, 那就只需要填写高或宽中的一个设计图尺寸即可 -->
<meta-data
    android:name="design_width_in_dp"
    android:value="360"/>
<meta-data
    android:name="design_height_in_dp"
    android:value="640"/>

```

3. 可以改变单个页面的设计图尺寸
```
如果某些页面不想使用 AndroidAutoSize初始化时设置的默认适配参数,请让该页面实现此接口
实现此接口CustomAdapt即可自定义用于适配的一些参数, 从而影响最终的适配效果

```

